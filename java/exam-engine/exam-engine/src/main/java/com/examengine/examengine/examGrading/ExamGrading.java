package com.examengine.examengine.examGrading;

import com.examengine.examengine.examAssignmentEvents.EventType;
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
    private EventType eventType;
    private StatusOfGaring statusOfGaring;

}
