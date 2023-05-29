package com.users.users.mapper;

import com.users.users.ExamGrading.ExamGradingDto;
import com.users.users.ExamSummary.ExamSummary;
import com.users.users.ExamSummary.ExamSummaryDTO;
import org.springframework.stereotype.Component;

@Component
public class ExamSummaryMapper {
    public ExamSummaryDTO mapToExamSummaryDTO(ExamSummary examSummary) {
        ExamSummaryDTO examSummaryDTO = new ExamSummaryDTO();
        examSummaryDTO.setId(examSummary.getId());
        examSummaryDTO.setUserId(examSummary.getUserId());
        examSummaryDTO.setExamInstanceId(examSummary.getExamInstanceId());
        examSummaryDTO.setScore(examSummary.getScore());
        examSummaryDTO.setExamStatus(examSummary.getExamStatus());
        examSummaryDTO.setExamName(examSummary.getExamName());
        return examSummaryDTO;
    }
    public ExamSummary mapToExamSummaryEntity(ExamSummaryDTO examSummaryDTO) {
        ExamSummary examSummary = new ExamSummary();
        examSummary.setId(examSummaryDTO.getId());
        examSummary.setUserId(examSummaryDTO.getUserId());
        examSummary.setExamName(examSummary.getExamName());
        examSummary.setExamInstanceId(examSummaryDTO.getExamInstanceId());
        examSummary.setScore(examSummaryDTO.getScore());
        examSummary.setExamStatus(examSummaryDTO.getExamStatus());
        return examSummary;
    }
    public ExamSummary createExamSummary(ExamGradingDto examGradingDto) {
        ExamSummary examSummary = new ExamSummary();
        examSummary.setScore(examGradingDto.getScore());
        examSummary.setUserId(examGradingDto.getExamGrading().getStudentId());
        examSummary.setExamInstanceId(examGradingDto.getExamInstanceId());
        examSummary.setExamName(examGradingDto.getExamGrading().getExamName());
        return examSummary;
    }

}
