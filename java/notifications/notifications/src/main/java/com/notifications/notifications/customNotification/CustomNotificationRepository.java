package com.notifications.notifications.customNotification;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomNotificationRepository extends MongoRepository<CustomNotification,String> {
    List<CustomNotification> findByUserId(String userId);
}
