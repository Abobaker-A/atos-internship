package com.examengine.examengine.examDefinition;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.List;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "exam_definition")
public class ExamDefinition {
    @Id
    private String id;
    private String name;
    private int duration;

    @Field(value = "passing_score")
    private double passingScore;
    @Field(value = "question_ids")
    private List<String> questionIds;
}
