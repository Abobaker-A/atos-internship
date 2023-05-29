package com.examengine.examengine.mapper;


import com.examengine.examengine.examAssignmentEvents.EventType;
import com.examengine.examengine.examAssignmentEvents.ExamAssignment;
import com.examengine.examengine.examDefinition.ExamDefinition;
import com.examengine.examengine.examDefinition.ExamDefinitionDto;
import com.examengine.examengine.examGrading.ExamGrading;
import com.examengine.examengine.examInstance.ExamInstance;
import com.examengine.examengine.examInstance.ExamInstanceDto;
import com.examengine.examengine.examSubmission.ExamSubmissionEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
@Component
public class ExamInstanceMapper {
    @Autowired
    private QuestionMapper questionMapper;
    @Autowired
    private GeneratedLinkMapper generatedLinkMapper;
    public ExamInstance fromExamInstanceDtoToEntity(ExamInstanceDto examInstanceDto) {
        ExamInstance examInstance = new ExamInstance();
        examInstance.setExamDefinitionId(examInstanceDto.getExamDefinitionId());
        examInstance.setStartTime(examInstanceDto.getStartTime());
        examInstance.setEndTime(examInstanceDto.getEndTime());
        examInstance.setScore(examInstanceDto.getScore());
        examInstance.setCompletionTime(examInstanceDto.getCompletionTime());
        examInstance.setGeneratedLink(generatedLinkMapper.fromDtoToEntity(examInstanceDto.getGeneratedLink()));
        examInstance.setCreatedBy(examInstanceDto.getCreatedBy());
        examInstance.setDuration(examInstance.getDuration());
        examInstance.setTakenBy(examInstanceDto.getTakenBy());
        examInstance.setQuestions(examInstanceDto.getQuestions().stream()
                .map(questionDto -> questionMapper.fromDtoToEntity(questionDto))
                .collect(Collectors.toList()));
        return examInstance;
    }

    public ExamInstanceDto fromExamInstanceEntityToDto(ExamInstance examInstance) {
        ExamInstanceDto examInstanceDto = new ExamInstanceDto();
        examInstanceDto.setExamDefinitionId(examInstance.getExamDefinitionId());
        examInstanceDto.setId(examInstance.getId());
        examInstanceDto.setStatusOfExam(examInstance.getStatusOfExam());
        examInstanceDto.setStartTime(examInstance.getStartTime());
        examInstanceDto.setEndTime(examInstance.getEndTime());
        examInstanceDto.setCompletionTime(examInstance.getCompletionTime());
        examInstanceDto.setScore(examInstance.getScore());
        examInstanceDto.setDuration(examInstance.getDuration());
        examInstanceDto.setGeneratedLink(generatedLinkMapper.fromEntityToDto(examInstance.getGeneratedLink()));
        examInstanceDto.setCreatedBy(examInstance.getCreatedBy());
        examInstanceDto.setTakenBy(examInstance.getTakenBy());
        return examInstanceDto;
    }

    public List<ExamInstanceDto> fromExamInstanceEntityToDtos(List<ExamInstance> examInstance){
        List<ExamInstanceDto> examInstanceDtos =new ArrayList<>();
        for(ExamInstance examInstance1:examInstance){
            examInstanceDtos.add(fromExamInstanceEntityToDto(examInstance1));
        }
        return examInstanceDtos;
    }
    public ExamAssignment fromExamInstanceEntityToExamAssignment(ExamInstance examInstance, String examName){
        ExamAssignment examAssignment = new ExamAssignment();
        examAssignment.setUrl(examInstance.getGeneratedLink().getUrl());
        examAssignment.setTimestamp(new Date());
        examAssignment.setStudentId(examInstance.getTakenBy());
        examAssignment.setExamName(examName);
        examAssignment.setEventType(EventType.EXAM_ASSIGNMENT);
        return examAssignment;
    }
    public ExamSubmissionEvent fromExamInstanceEntityToExamSubmission(ExamInstance examInstance , String examName){
        ExamSubmissionEvent examSubmissionEvent = new ExamSubmissionEvent();
        examSubmissionEvent.setTeacherId("Test123");
        examSubmissionEvent.setStudentId(examInstance.getTakenBy());
        examSubmissionEvent.setExamName(examName);
        examSubmissionEvent.setTimestamp(new Date());
        examSubmissionEvent.setEventType(EventType.EXAM_SUBMISSION);
        return examSubmissionEvent;
    }
    public ExamGrading fromExamInstanceEntityToExamGrading(ExamInstance examInstance , String examName){
        ExamGrading examGrading = new ExamGrading();
        examGrading.setStudentId(examInstance.getTakenBy());
        examGrading.setExamName(examName);
        examGrading.setEventType(EventType.EXAM_GRADING);
        examGrading.setTimestamp(new Date());
        return examGrading;
    }

    public ExamGrading fromExamInstanceEntityToExamGrading(ExamInstanceDto savedExamInstance, String examName) {
        ExamGrading examGrading = new ExamGrading();
        examGrading.setStudentId(savedExamInstance.getTakenBy());
        examGrading.setExamName(examName);
        examGrading.setEventType(EventType.EXAM_GRADING);
        examGrading.setTimestamp(new Date());
        return examGrading;

    }
}
