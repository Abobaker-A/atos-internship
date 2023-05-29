package com.notifications.notifications.customNotification;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "notifications")
public class CustomNotification {
    @Id
    private String id;
    @Field(value = "user_id")
    private String userId;
    @Field(value = "time_stamp")
    private Date timestamp;
    private String url;
    private String message;
    @Field(value = "is_read")
    private boolean isRead = false;


}
