package com.notifications.notifications.customNotification;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CustomNotificationDto {
    private String id;
    private String userId;
    private Date timestamp;
    private String url;
    private String message;
    private boolean isRead = false;
}
