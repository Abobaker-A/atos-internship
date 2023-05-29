package com.notifications.notifications.examSubmissionEvent;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ExamSubmissionEvent {
    private String teacherId;
    private String studentId;
    private String examName;
    private String url;
    private Date timestamp;
    private EvenType eventType;
}
