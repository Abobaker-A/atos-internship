package com.examengine.examengine.questiondetails;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@NoArgsConstructor
@Data
@AllArgsConstructor
public class Question {
    private String questionId;
    private List<String> selectedAnswerId;
    private Date displayTime;
    private Date answerTime;
}
