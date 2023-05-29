package com.examengine.examengine.examSubmission;

import com.examengine.examengine.examAssignmentEvents.ExamAssignment;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class ExamSubmissionEventService {
    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    public void sendExamSubmission(String topic, ExamSubmissionEvent examSubmissionEvent) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        kafkaTemplate.send(topic,objectMapper.writeValueAsString(examSubmissionEvent) );
    }
}
