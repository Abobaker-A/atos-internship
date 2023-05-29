package com.examengine.examengine.examAssignmentEvents;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class ExamAssignmentService {

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;
    public void sendExamAssignment(String topic, ExamAssignment examAssignment) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        kafkaTemplate.send(topic, objectMapper.writeValueAsString(examAssignment));
    }
}
