package com.notifications.notifications.mapper;

import com.notifications.notifications.customNotification.CustomNotification;
import com.notifications.notifications.customNotification.CustomNotificationDto;
import org.springframework.stereotype.Component;

@Component
public class NotificationMapper {
    public CustomNotificationDto mapToNotificationDTO(CustomNotification customNotification) {
        CustomNotificationDto customNotificationDto = new CustomNotificationDto();
        customNotificationDto.setId(customNotification.getId());
        customNotificationDto.setUserId(customNotification.getUserId());
        customNotificationDto.setTimestamp(customNotification.getTimestamp());
        customNotificationDto.setUrl(customNotification.getUrl());
        customNotificationDto.setMessage(customNotification.getMessage());
        customNotificationDto.setRead(customNotification.isRead());
        return customNotificationDto;
    }
    public CustomNotification mapToNotificationEntity(CustomNotificationDto customNotificationDto) {
        CustomNotification customNotification = new CustomNotification();
        customNotification.setId(customNotificationDto.getId());
        customNotification.setUserId(customNotificationDto.getUserId());
        customNotification.setTimestamp(customNotificationDto.getTimestamp());
        customNotification.setUrl(customNotificationDto.getUrl());
        customNotification.setMessage(customNotificationDto.getMessage());
        customNotification.setRead(customNotificationDto.isRead());
        return customNotification;
    }
}
