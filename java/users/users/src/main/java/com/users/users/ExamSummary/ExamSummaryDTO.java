package com.users.users.ExamSummary;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ExamSummaryDTO {
    private String id;
    private String userId;
    private String examInstanceId;
    private int score;
    private ExamStatus examStatus;
    private String examName;

}
