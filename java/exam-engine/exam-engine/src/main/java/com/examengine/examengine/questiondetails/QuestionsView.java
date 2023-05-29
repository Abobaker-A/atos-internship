package com.examengine.examengine.questiondetails;


import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.Date;
import java.util.List;

@Data
@Document(collection = "questions_view")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class QuestionsView {

    @Id
    private String id ;
    private String name ;
    @Field("level_id")
    private int levelId ;
    @Field("category_id")
    private int categoryId ;
    private int mark ;
    @Field("expected_time")
    private int expectedTime ;
    @Field("correct_answer_ids")
    private String[] correctAnswerIds ;
    @Field("created_by")
    private String createdBy ;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    @Field("created_at")
    private Date createdAt ;
    @Field("answers")
    private List<Answers> answers;
}