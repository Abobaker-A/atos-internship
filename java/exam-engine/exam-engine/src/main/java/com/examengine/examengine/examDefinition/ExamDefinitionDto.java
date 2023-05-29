package com.examengine.examengine.examDefinition;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ExamDefinitionDto {
    private String id;
    private String name;
    private int duration;
    private double passingScore;
    private List<String> questionIds;
}
