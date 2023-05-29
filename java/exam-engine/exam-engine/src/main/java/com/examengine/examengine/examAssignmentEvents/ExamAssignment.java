package com.examengine.examengine.examAssignmentEvents;

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
    private EventType eventType;


}
