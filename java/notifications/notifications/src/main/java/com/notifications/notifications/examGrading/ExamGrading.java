package com.notifications.notifications.examGrading;

import com.notifications.notifications.examSubmissionEvent.EvenType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ExamGrading {
    private String studentId;
    private String examName;
    private Date timestamp;
    private String url;
    private EvenType eventType;
    private StatusOfGaring statusOfGaring;

}
