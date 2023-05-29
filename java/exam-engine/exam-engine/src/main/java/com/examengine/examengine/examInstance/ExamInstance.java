package com.examengine.examengine.examInstance;

import com.examengine.examengine.GeneratedLink.GeneratedLink;
import com.examengine.examengine.questiondetails.Question;
import com.examengine.examengine.questiondetails.QuestionsView;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Document(collection ="exam_instance" )
public class ExamInstance {
    @Id
    private String id;
    @Field(value = "exam_definition_id")
    private String examDefinitionId;
    @Field(value = "start_time")
    private Date startTime;
    @Field(value = "end_time")
    private Date endTime;
    @Field(value = "completion_time")
    private Date completionTime;
    @Field(value = "generated_link")
    private GeneratedLink generatedLink;
    @Field(value = "created_by")
    private String createdBy;
    private int score;
    private int duration;
    @Field(value = "taken_by")
    private String takenBy;
    private StatusOfExam statusOfExam;
    private List<Question> questions;
}
