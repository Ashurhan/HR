package com.example.HR.service;

import com.example.HR.entity.models.Notification;

import java.util.List;

public interface NotificationService {
    Notification createNotificationStorage(Notification notificationStorage);

    void sendNotificationToAllUsers(String content);

    List<Notification> getNotifs(Long userID);

    Integer countUnreadNotificationsForUser(Long userId);
}
