package com.examengine.examengine.examGrading;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;


@Service
public class ExamGradingService {
    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    public void sendExamGradingNotification(String topic, ExamGrading examGrading) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        kafkaTemplate.send(topic, objectMapper.writeValueAsString(examGrading) );
    }

    public void sendExamGradingToUser(String topic, ExamGradingDto examGradingDto) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();

        kafkaTemplate.send(topic,objectMapper.writeValueAsString(examGradingDto));
    }
}
