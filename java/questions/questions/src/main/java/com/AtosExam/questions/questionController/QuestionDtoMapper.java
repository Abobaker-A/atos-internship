package com.AtosExam.questions.questionController;

import com.AtosExam.questions.questionsView.QuestionsView;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Date;

@Component
public class QuestionDtoMapper {

    public  QuestionsDto QuestionstoDto(Questions questions){
        return QuestionsDto.builder()
                .id(questions.getId())
                .name(questions.getName())
                .levelId(questions.getLevelId())
                .categoryId(questions.getCategoryId())
                .mark(questions.getMark())
                .expectedTime(questions.getExpectedTime())
                .correctAnswerIds(questions.getCorrectAnswerIds())
                .createdBy(questions.getCreatedBy())
                .createdAt(questions.getCreatedAt())
                .build();

    }

    public  Questions toQuestionsEntity (QuestionsDto questionsDto){
        Questions question = new Questions();
        question.setMark(questionsDto.getMark());
        question.setName(questionsDto.getName());
        question.setCreatedAt(new Date());
        question.setCreatedBy(questionsDto.getCreatedBy());
        question.setExpectedTime(questionsDto.getExpectedTime());
        question.setLevelId(questionsDto.getLevelId());
        question.setCategoryId(questionsDto.getCategoryId());

        return question;
    }






}
