package com.example.HR.controller;

import com.example.HR.entity.models.Notification;
import com.example.HR.service.NotificationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/notifications")
@CrossOrigin(origins = "*", maxAge = 3600)
@Tag(name = "Notification Management", description = "APIs for managing notifications")
public class NotificationController {

    private final NotificationService notificationService;

    public NotificationController(NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    @PostMapping
    @Operation(summary = "Create a new notification", description = "Creates a new notification in the system")
    public ResponseEntity<Notification> createNotification(@RequestBody Notification notification) {
        return ResponseEntity.ok(notificationService.createNotificationStorage(notification));
    }

    @PostMapping("/broadcast")
    @Operation(summary = "Send notification to all users", description = "Broadcasts a notification to all users in the system")
    public ResponseEntity<?> sendNotificationToAllUsers(@RequestBody String content) {
        notificationService.sendNotificationToAllUsers(content);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/user/{userId}")
    @Operation(summary = "Get user notifications", description = "Retrieves all notifications for a specific user")
    public ResponseEntity<List<Notification>> getUserNotifications(@PathVariable Long userId) {
        return ResponseEntity.ok(notificationService.getNotifs(userId));
    }

    @GetMapping("/user/{userId}/unread/count")
    @Operation(summary = "Get unread notifications count", description = "Retrieves the count of unread notifications for a user")
    public ResponseEntity<Integer> getUnreadNotificationsCount(@PathVariable Long userId) {
        return ResponseEntity.ok(notificationService.countUnreadNotificationsForUser(userId));
    }
} 