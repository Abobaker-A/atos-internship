package com.AtosExam.questions.questionController;

        import com.AtosExam.questions.answers.Answers;
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
@Document(collection = "questions")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Questions {
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
    @Field("correct_answer_id")
    private List<String> correctAnswerIds ;
    @Field("created_by")
    private String createdBy ;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    @Field("created_at")
    private Date createdAt ;
    private List<Answers> answers;


    public Questions(String name, int levelId, int typeId, int mark, int expectedTime, List<String> correctAnswer, String createdBy, List<Answers> answers) {
        this.name = name;
        this.levelId = levelId;
        this.categoryId = typeId;
        this.mark = mark;
        this.expectedTime = expectedTime;
        this.correctAnswerIds = correctAnswer;
        this.createdBy = createdBy;
        this.createdAt = new Date();
        this.answers=answers;

    }
}
