package com.notifications.notifications.examAssignment;

import com.notifications.notifications.examSubmissionEvent.EvenType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ExamAssignment {
    private String studentId;
    private String examName;
    private Date timestamp;
    private String url;
    private EvenType eventType;
}
