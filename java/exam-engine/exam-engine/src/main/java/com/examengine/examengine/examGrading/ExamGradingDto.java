package com.examengine.examengine.examGrading;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ExamGradingDto {
    private ExamGrading examGrading;
    private int Score;
    private String examInstanceId;
    private Double passingScore;
    private int totalScore;
}
