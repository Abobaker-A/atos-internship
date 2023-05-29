package com.AtosExam.questions.answers;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor
@Document(collection = "answers")
public class Answers {

    @Id
    private String id ;
    private String name ;
    @Field("question_id")
    private ObjectId questionId;
    @Field("is_true")
    private Boolean isTrue;

    public Answers(String name, Boolean description) {
        this.name = name;
        this.isTrue = description;
    }


}
