package com.AtosExam.questions.questionController;

import com.AtosExam.questions.answers.AnswerRepo;
import com.AtosExam.questions.answers.Answers;
import com.AtosExam.questions.answers.AnswersService;
import com.AtosExam.questions.questionsView.QuestionsViewRepo;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;

import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class QuestionServices {
    @Autowired
    private QuestionsRepo questionsRepo ;
    @Autowired
    private AnswerRepo answerRepo ;
    @Autowired
    private AnswersService answersService;
    @Autowired
    private MongoTemplate mongoTemplate;
    @Autowired
    private QuestionsViewRepo questionsViewRepo;
    @Autowired
    private QuestionDtoMapper questionDtoMapper;


    public QuestionsDto addQuestions(QuestionsDto questionsDto){
        List<Answers> addedAsnwers=null;
        QuestionsDto questionsDtoAdded = new QuestionsDto();
        List<Answers> answers = questionsDto.getAnswers();
        Questions questionAdded = questionsRepo.insert(questionDtoMapper.toQuestionsEntity(questionsDto));
        if (answers!=null && !answers.isEmpty()){
            answers.forEach(answer -> answer.setQuestionId(new ObjectId(questionAdded.getId())));
           addedAsnwers = answerRepo.insert(answers);
        }
        questionsDtoAdded = questionDtoMapper.QuestionstoDto(questionAdded);
        questionsDtoAdded.setAnswers(addedAsnwers);
        return questionsDtoAdded;
    }





    public void deleteAnswer(String id){
        answersService.deleteAnswersByQuestionId(id);

        questionsRepo.deleteById(id);
    }










}
