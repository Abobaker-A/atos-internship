package com.notifications.notifications.customNotification;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "notifications")
public class customNotificationController {
    @Autowired
    private CustomNotificationService customNotificationService;

    @GetMapping("/{userId}")
    public ResponseEntity<List<CustomNotificationDto>> getUserNotifications(@PathVariable String userId) {
        System.out.println("hahahhahahah");

        List<CustomNotificationDto> notifications = customNotificationService.getUserNotifications(userId);
        return ResponseEntity.ok(notifications);
    }
    @PutMapping("/read")
    public ResponseEntity<Void> markNotificationAsRead(@RequestBody String notificationId) {
        customNotificationService.markNotificationAsRead(notificationId);
        return ResponseEntity.ok().build();
    }

}
