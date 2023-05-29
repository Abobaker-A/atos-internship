package com.examengine.examengine.examInstance;

import com.examengine.examengine.GeneratedLink.GeneratedLinkDto;
import com.examengine.examengine.client.QestionBankApis;
import com.examengine.examengine.client.UserApis;
import com.examengine.examengine.examAssignmentEvents.ExamAssignment;
import com.examengine.examengine.examAssignmentEvents.ExamAssignmentService;
import com.examengine.examengine.examDefinition.AssignExamToStudentDto;
import com.examengine.examengine.examDefinition.ExamDefinition;
import com.examengine.examengine.examDefinition.ExamDefinitionRepository;
import com.examengine.examengine.examGrading.ExamGrading;
import com.examengine.examengine.examGrading.ExamGradingDto;
import com.examengine.examengine.examGrading.ExamGradingService;
import com.examengine.examengine.examSubmission.ExamSubmissionEvent;
import com.examengine.examengine.examSubmission.ExamSubmissionEventService;
import com.examengine.examengine.exception.BadRequestException;
import com.examengine.examengine.exception.NotFoundException;
import com.examengine.examengine.mapper.ExamDefinitionMapper;
import com.examengine.examengine.mapper.ExamInstanceMapper;
import com.examengine.examengine.mapper.GeneratedLinkMapper;
import com.examengine.examengine.mapper.QuestionMapper;
import com.examengine.examengine.questiondetails.Question;
import com.examengine.examengine.questiondetails.QuestionDto;
import com.examengine.examengine.questiondetails.QuestionsView;
import com.examengine.examengine.uriVlaues.UriVlaue;
import com.examengine.examengine.user.User;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Service
public class ExamInstanceService {
    @Autowired
    private ExamInstanceRepository examInstanceRepository;
    @Autowired
    private QestionBankApis qestionBankApis;
    @Autowired
    private UserApis userApis;
    @Autowired
    private ExamDefinitionRepository examDefinitionRepository;
    @Autowired
    private ExamInstanceMapper examInstanceMapper;
    @Autowired
    private GeneratedLinkMapper generatedLinkMapper;
    @Autowired
    private QuestionMapper questionMapper;
    @Autowired
    private ExamDefinitionMapper examDefinitionMapper;
    @Autowired
    private UriVlaue uriVlaue;
    @Autowired
    private ExamAssignmentService examAssignmentService;
    @Autowired
    private ExamSubmissionEventService examSubmissionEventService;
    @Autowired
    private ExamGradingService examGradingService;



    private ExamDefinition getExamDefinitionById(String examDefinitionId){
        return  examDefinitionRepository.findById(examDefinitionId)
                .orElseThrow(() -> new NotFoundException("Exam definition not found with id: " + examDefinitionId));

    }


    public ExamInstanceDto assignExamToStudent(AssignExamToStudentDto assignExamToStudentDto) throws JsonProcessingException {


        ExamDefinition examDefinition = getExamDefinitionById(assignExamToStudentDto.getExamdefinitionid());

       User user = userApis.getUserByEmail(assignExamToStudentDto.getUseremail());

        ExamInstance examInstance = new ExamInstance();
        examInstance.setExamDefinitionId(assignExamToStudentDto.getExamdefinitionid());
        examInstance.setCreatedBy(assignExamToStudentDto.getCreatedby());
        examInstance.setTakenBy(user.getId());

        examInstance.setStatusOfExam(StatusOfExam.ASSIGNED);
        ExamInstance savedExamInstance = examInstanceRepository.save(examInstance);
        GeneratedLinkDto generatedLinkDto = generateExamLink(assignExamToStudentDto,savedExamInstance);
        savedExamInstance.setGeneratedLink(generatedLinkMapper.fromDtoToEntity(generatedLinkDto));
        savedExamInstance = examInstanceRepository.save(examInstance);

        sendExamAssignmentNotification(savedExamInstance,examDefinition);

        return examInstanceMapper.fromExamInstanceEntityToDto(savedExamInstance);
    }

    private void sendExamAssignmentNotification(ExamInstance savedExamInstance, ExamDefinition examDefinition) throws JsonProcessingException {
        ExamAssignment examAssignment = examInstanceMapper.fromExamInstanceEntityToExamAssignment(savedExamInstance,examDefinition.getName());
        examAssignmentService.sendExamAssignment("assignedExam", examAssignment);
    }

    private GeneratedLinkDto generateExamLink(AssignExamToStudentDto assignExamToStudentDto,ExamInstance examInstance) {
        String url = uriVlaue.getFrontEndBaseUrl()+uriVlaue.getTakeExamEndPoint()+ examInstance.getId();
        //TODO:when use Security we add token with request and set Token and add Link ID

        GeneratedLinkDto generatedLink = new GeneratedLinkDto();
        generatedLink.setUrl(url);
        generatedLink.setScheduledTimeFrom(assignExamToStudentDto.getScheduledTimeFrom());
        generatedLink.setScheduledTimeTo(assignExamToStudentDto.getScheduledTimeTo());
        return generatedLink;
    }


    public ExamInstanceDto takeExam(String examInstanceId) {
        try {
            ExamInstance examInstance = examInstanceRepository.findById(examInstanceId)
                    .orElseThrow(() -> new NotFoundException("Exam instance not found with id: " + examInstanceId));
            ExamDefinition examDefinition = getExamDefinitionById(examInstance.getExamDefinitionId());

            checkIfExamInstanceIsAvailable(examInstance);
            checkIfExamInstanceHasExpired(examInstance);

            List<QuestionsView> questionsViews = qestionBankApis.getQuestionsView(examDefinition);

            setStartAndEndTimesOfExam(examInstance, examDefinition);

            ExamInstance savedExamInstance = saveExamInstance(examInstance);

            ExamInstanceDto examInstanceDto = examInstanceMapper.fromExamInstanceEntityToDto(savedExamInstance);
            examInstanceDto.setQuestionsViews(questionsViews);
            examInstanceDto.setExamDefinitionName(examDefinition.getName());
            return examInstanceDto;
        } catch (IllegalArgumentException ex) {
            throw new BadRequestException("Invalid ID: " + examInstanceId);
        }
    }

    private void checkIfExamInstanceIsAvailable(ExamInstance examInstance) {
        if (!examInstance.getStatusOfExam().equals(StatusOfExam.ASSIGNED)) {
            throw new NotFoundException("Exam instance is not available for taking");
        }
    }

    private void checkIfExamInstanceHasExpired(ExamInstance examInstance) {
        Date currentDate = new Date();
        Date scheduledTimeTo = examInstance.getGeneratedLink().getScheduledTimeTo();
        if (currentDate.after(scheduledTimeTo)) {
            examInstance.setStatusOfExam(StatusOfExam.ABSENT);
            examInstanceRepository.save(examInstance);
            throw new NotFoundException("Exam instance has already expired");
        }
    }

    private void setStartAndEndTimesOfExam(ExamInstance examInstance, ExamDefinition examDefinition) {
        Date currentDate = new Date();
        Date scheduledTimeTo = examInstance.getGeneratedLink().getScheduledTimeTo();
        examInstance.setDuration(examDefinition.getDuration());
        examInstance.setStatusOfExam(StatusOfExam.IN_PROGRESS);
        examInstance.setStartTime(currentDate);

        long remainingDuration = scheduledTimeTo.getTime() - currentDate.getTime();
        int remainingMinutes = (int) TimeUnit.MILLISECONDS.toMinutes(remainingDuration);
        examDefinition.setDuration(Math.min(remainingMinutes, examDefinition.getDuration()));
        examInstance.setEndTime(new Date(currentDate.getTime() + TimeUnit.MINUTES.toMillis(examDefinition.getDuration())));
    }

    private ExamInstance saveExamInstance(ExamInstance examInstance) {
        return examInstanceRepository.save(examInstance);
    }




    public ExamInstanceDto submitExam(String examInstanceId, List<QuestionDto> questionsDto) throws JsonProcessingException {
        ExamInstance examInstance = examInstanceRepository.findById(examInstanceId)
                .orElseThrow(() -> new NotFoundException("Exam instance not found"));
        examInstance.setStatusOfExam(StatusOfExam.COMPLETED);
        examInstance.setCompletionTime(new Date());

        int score = 0;
        int totalScore = 0;
        List<Question> questions = new ArrayList<>();
        for (QuestionDto questionDto : questionsDto) {
            QuestionsView questionView = qestionBankApis.getQuestionById(questionDto.getQuestionId());
            int questionScore = 0;
            if (questionDto.getSelectedAnswerId().equals(Arrays.asList(questionView.getCorrectAnswerIds()))) {
                questionScore = questionView.getMark();
                score += questionView.getMark();
            }
            totalScore+=questionView.getMark();
            questions.add(questionMapper.fromDtoToEntity(questionDto));
        }

        examInstance.setQuestions(questions);
        examInstance.setScore(score);
        ExamInstance savedExamInstance = examInstanceRepository.save(examInstance);
        ExamInstanceDto examInstanceDto =examInstanceMapper.fromExamInstanceEntityToDto(savedExamInstance);
        examInstanceDto.setTotalScore(totalScore);
        ExamDefinition examDefinition =getExamDefinitionById(examInstanceDto.getExamDefinitionId());
        examInstanceDto.setPassingScore(examDefinition.getPassingScore());

        sendExamSubmissionNotification(savedExamInstance,examDefinition.getName());
        sendExamGradingNotification(savedExamInstance,examDefinition.getName());
        sendExamGradingToUser( examInstanceDto,examDefinition.getName()  );


        return examInstanceDto;
    }


    private void sendExamGradingNotification(ExamInstance savedExamInstance, String examName) throws JsonProcessingException {
        ExamGrading examGrading = examInstanceMapper.fromExamInstanceEntityToExamGrading(savedExamInstance,examName);
        examGrading.setUrl(uriVlaue.getFrontEndBaseUrl()+"/examsummarydetails/"+savedExamInstance.getId());
        examGradingService.sendExamGradingNotification("examGrading", examGrading);
    }
    private void sendExamGradingToUser(ExamInstanceDto savedExamInstance, String examName) throws JsonProcessingException {

        ExamGrading examGrading = examInstanceMapper.fromExamInstanceEntityToExamGrading(savedExamInstance,examName);
        examGrading.setUrl(uriVlaue.getFrontEndBaseUrl()+"/examsummarydetails/"+savedExamInstance.getId());

        ExamGradingDto examGradingDto =new ExamGradingDto();
        examGradingDto.setExamInstanceId(savedExamInstance.getId());
        examGradingDto.setExamGrading(examGrading);
        examGradingDto.setScore(savedExamInstance.getScore());
        examGradingDto.setTotalScore(savedExamInstance.getTotalScore());
        examGradingDto.setPassingScore(savedExamInstance.getPassingScore());

        examGradingService.sendExamGradingToUser("examGradingToUser", examGradingDto);
    }
    private void sendExamSubmissionNotification(ExamInstance savedExamInstance , String examName) throws JsonProcessingException {
        ExamSubmissionEvent examSubmissionEvent = examInstanceMapper.fromExamInstanceEntityToExamSubmission(savedExamInstance,examName);
        examSubmissionEvent.setUrl(uriVlaue.getFrontEndBaseUrl()+"/examsummarydetails/"+savedExamInstance.getId());

        examSubmissionEventService.sendExamSubmission("examSubmission", examSubmissionEvent);
    }

    public List<ExamInstanceDto> getAllExamInstances() {
       return  examInstanceMapper.fromExamInstanceEntityToDtos(examInstanceRepository.findAll());
    }

    @Scheduled(fixedDelay = 60000)
    public void updateExamStatus() {
        List<ExamInstance> examInstances = examInstanceRepository.findAll();
        for (ExamInstance examInstance : examInstances) {
            if (!examInstance.getStatusOfExam().equals(StatusOfExam.ABSENT) && new Date().after(examInstance.getGeneratedLink().getScheduledTimeTo())) {
                examInstance.setStatusOfExam(StatusOfExam.ABSENT);
                examInstanceRepository.save(examInstance);
            }
        }
    }
}
