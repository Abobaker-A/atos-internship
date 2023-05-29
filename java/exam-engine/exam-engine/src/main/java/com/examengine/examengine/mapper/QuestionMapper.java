package com.examengine.examengine.mapper;


import com.examengine.examengine.questiondetails.Question;
import com.examengine.examengine.questiondetails.QuestionDto;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class QuestionMapper {

    public Question fromDtoToEntity(QuestionDto questionDto) {
        Question question = new Question();
        question.setQuestionId(questionDto.getQuestionId());
        question.setSelectedAnswerId(questionDto.getSelectedAnswerId());
        question.setDisplayTime(questionDto.getDisplayTime());
        question.setAnswerTime(questionDto.getAnswerTime());
        return question;
    }

    public QuestionDto fromEntityToDto(Question question) {
        QuestionDto questionDto = new QuestionDto();
        questionDto.setQuestionId(question.getQuestionId());
        questionDto.setSelectedAnswerId(question.getSelectedAnswerId());
        questionDto.setDisplayTime(question.getDisplayTime());
        questionDto.setAnswerTime(question.getAnswerTime());
        return questionDto;
    }



}




