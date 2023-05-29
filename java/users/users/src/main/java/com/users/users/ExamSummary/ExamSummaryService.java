package com.users.users.ExamSummary;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.users.users.ExamGrading.ExamGradingDto;
import com.users.users.mapper.ExamSummaryMapper;
import com.users.users.users.User;
import com.users.users.users.UserDto;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.common.errors.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ExamSummaryService {
    @Autowired
    private  ExamSummaryRepository examSummaryRepository;
    @Autowired
    private  ExamSummaryMapper examSummaryMapper;



    public Page<ExamSummaryDTO> getExamSummariesByUserId(String userId, int page, int size) {
        Pageable paging = PageRequest.of(page, size);
        Page<ExamSummary> examSummaries = examSummaryRepository.findByUserId(userId, paging);
        List<ExamSummaryDTO> examSummaryDTOs = examSummaries.getContent().stream()
                .map(examSummaryMapper::mapToExamSummaryDTO)
                .collect(Collectors.toList());
        return new PageImpl<>(examSummaryDTOs, examSummaries.getPageable(), examSummaries.getTotalElements());
    }


    @KafkaListener(topics = "examGradingToUser", groupId = "user-group2")
    public void consumeExamGrading(ConsumerRecord<String,String> record ) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        ExamGradingDto examGradingDto = objectMapper.readValue(record.value(), ExamGradingDto.class);
        ExamSummary examSummary = createExamSummary(examGradingDto);
        ExamStatus examStatus = determineExamStatus(examGradingDto);
        examSummary.setExamStatus(examStatus);
        saveExamSummary(examSummary);
    }

    private ExamSummary createExamSummary(ExamGradingDto examGradingDto) {
        ExamSummary examSummary = new ExamSummary();
        examSummary.setScore(examGradingDto.getScore());
        examSummary.setUserId(examGradingDto.getExamGrading().getStudentId());
        examSummary.setExamInstanceId(examGradingDto.getExamInstanceId());
        examSummary.setExamName(examGradingDto.getExamGrading().getExamName());
        return examSummary;
    }

    private ExamStatus determineExamStatus(ExamGradingDto examGradingDto) {
        double scorePercentage = (examGradingDto.getScore() / (double) examGradingDto.getTotalScore()) * 100;
        return scorePercentage >= examGradingDto.getPassingScore() ? ExamStatus.PASSED : ExamStatus.FAILED;
    }



    private void saveExamSummary(ExamSummary examSummary) {
        examSummaryRepository.insert(examSummary);
    }

    public ExamSummaryDTO getExamSummaryByExamInstanceId(String examInstanceId) {
        Optional<ExamSummary> examSummary = examSummaryRepository.findByExamInstanceId(examInstanceId);
        System.out.println(examSummary.toString());
        return examSummary.map(summary -> examSummaryMapper.mapToExamSummaryDTO(summary)).orElse(null);
    }
}
