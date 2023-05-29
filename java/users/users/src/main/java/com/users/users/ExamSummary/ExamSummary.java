package com.users.users.ExamSummary;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "exam_summary")
public class ExamSummary {
    @Id
    private String id;
    @Field(value = "user_id")
    private String userId;
    @Field(value = "exam_name")
    private String examName;
    @Field(value = "exam_instance_id")
    private String examInstanceId;
    private int score;
    @Field(value = "exam_status")
    private ExamStatus  examStatus;

}
