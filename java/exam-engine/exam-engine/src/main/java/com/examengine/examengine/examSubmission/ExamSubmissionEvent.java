package com.examengine.examengine.examSubmission;

import com.examengine.examengine.examAssignmentEvents.EventType;
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
    private EventType eventType;
}
