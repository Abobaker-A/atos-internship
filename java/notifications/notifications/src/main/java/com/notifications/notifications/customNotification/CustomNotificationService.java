package com.notifications.notifications.customNotification;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.notifications.notifications.examAssignment.ExamAssignment;
import com.notifications.notifications.examGrading.ExamGrading;
import com.notifications.notifications.examSubmissionEvent.ExamSubmissionEvent;
import com.notifications.notifications.mapper.NotificationMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CustomNotificationService {
    @Autowired
    private CustomNotificationRepository customNotificationRepository;

    @Autowired
    private NotificationMapper notificationMapper;

    public List<CustomNotificationDto> getUserNotifications(String userId) {
        List<CustomNotification> notifications = customNotificationRepository.findByUserId(userId);
        return notifications.stream()
                .map(notificationMapper::mapToNotificationDTO)
                .collect(Collectors.toList());
    }
    @KafkaListener(topics = "assignedExam", groupId = "notification_id")
    public void consumeAssignedExam(String examAssignmentString) throws JsonProcessingException {
        ExamAssignment examAssignment = convertJsonToExamAssignment(examAssignmentString);
        CustomNotificationDto customNotificationDto = createCustomNotificationDto(examAssignment, "You have been assigned to " + examAssignment.getExamName() + "Exam");
        saveNotifications(notificationMapper.mapToNotificationEntity(customNotificationDto));
    }

    @KafkaListener(topics = "examSubmission", groupId = "notification_id")
    public void consumeExamSubmission(String examSubmissionEventString) throws JsonProcessingException {
        ExamSubmissionEvent examSubmissionEvent = convertJsonToExamSubmissionEvent(examSubmissionEventString);
        CustomNotificationDto customNotificationDto = createCustomNotificationDto(examSubmissionEvent, "Your " + examSubmissionEvent.getExamName() + "Exam is Submitted");
        saveNotifications(notificationMapper.mapToNotificationEntity(customNotificationDto));
    }

    @KafkaListener(topics = "examGrading", groupId = "notification_id")
    public void consumeExamGrading(String examGradingString) throws JsonProcessingException {
        ExamGrading examGrading = convertJsonToExamGrading(examGradingString);
        CustomNotificationDto customNotificationDto = createCustomNotificationDto(examGrading, "Your Exam " + examGrading.getExamName() + " Graded");
        saveNotifications(notificationMapper.mapToNotificationEntity(customNotificationDto));
    }

    private ExamAssignment convertJsonToExamAssignment(String examAssignmentString) throws JsonProcessingException {
        return new ObjectMapper().readValue(examAssignmentString, ExamAssignment.class);
    }

    private ExamSubmissionEvent convertJsonToExamSubmissionEvent(String examSubmissionEventString) throws JsonProcessingException {
        return new ObjectMapper().readValue(examSubmissionEventString, ExamSubmissionEvent.class);
    }

    private ExamGrading convertJsonToExamGrading(String examGradingString) throws JsonProcessingException {
        return new ObjectMapper().readValue(examGradingString, ExamGrading.class);
    }

    private CustomNotificationDto createCustomNotificationDto(Object event, String message) {
        CustomNotificationDto customNotificationDto = new CustomNotificationDto();
        customNotificationDto.setUserId(getUserId(event));
        customNotificationDto.setMessage(message);
        customNotificationDto.setUrl(getUrl(event));
        customNotificationDto.setTimestamp(new Date());
        return customNotificationDto;
    }

    private String getUserId(Object event) {
        if (event instanceof ExamAssignment) {
            return ((ExamAssignment) event).getStudentId();
        } else if (event instanceof ExamSubmissionEvent) {
            return ((ExamSubmissionEvent) event).getStudentId();
        } else if (event instanceof ExamGrading) {
            return ((ExamGrading) event).getStudentId();
        } else {
            throw new IllegalArgumentException("Event type not recognized: " + event.getClass().getSimpleName());
        }
    }

    private String getUrl(Object event) {
        if (event instanceof ExamAssignment) {
            return ((ExamAssignment) event).getUrl();
        } else if (event instanceof ExamSubmissionEvent) {
            return ((ExamSubmissionEvent) event).getUrl();
        } else if (event instanceof ExamGrading) {
            return ((ExamGrading) event).getUrl();
        } else {
            throw new IllegalArgumentException("Event type not recognized: " + event.getClass().getSimpleName());
        }
    }

    private void saveNotifications(CustomNotification customNotification) {
        customNotificationRepository.insert(customNotification);
    }


    public void markNotificationAsRead(String notificationId) {
        Optional<CustomNotification> notification = customNotificationRepository.findById(notificationId);
        if (notification.isPresent()) {
            notification.get().setRead(true);
            customNotificationRepository.save(notification.get());
        } else{
            return;
        }
    }

}
