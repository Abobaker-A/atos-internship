package com.examengine.examengine.examInstance;

import com.examengine.examengine.GeneratedLink.GeneratedLinkDto;
import com.examengine.examengine.examDefinition.ExamDefinitionDto;
import com.examengine.examengine.questiondetails.QuestionDto;
import com.examengine.examengine.questiondetails.QuestionsView;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ExamInstanceDto {
    private String id;
    private String examDefinitionId;
    private String examDefinitionName;
    private Date startTime;
    private Date endTime;
    private Date completionTime;
    private GeneratedLinkDto generatedLink;
    private String createdBy;
    private String takenBy;
    private int score;
    private int duration;
    private int totalScore;
    private double passingScore;
    private StatusOfExam statusOfExam;
    private List<QuestionDto> questions;
    private List<QuestionsView> questionsViews;

}
