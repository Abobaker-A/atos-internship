package com.AtosExam.questions.questionController;


import com.AtosExam.questions.answers.Answers;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class QuestionsDto {
    private String id ;
    private String name ;
    private int levelId ;
    private int categoryId ;
    private int mark ;
    private int expectedTime ;
    private List<String> correctAnswerIds ;
    private String createdBy ;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    private Date createdAt ;
    private List<Answers> answers ;

}
