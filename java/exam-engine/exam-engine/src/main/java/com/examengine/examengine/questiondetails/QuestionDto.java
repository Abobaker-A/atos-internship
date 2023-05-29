package com.examengine.examengine.questiondetails;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class QuestionDto {
    private String questionId;
    private List<String> selectedAnswerId;
    private Date displayTime;
    private Date answerTime;
}
