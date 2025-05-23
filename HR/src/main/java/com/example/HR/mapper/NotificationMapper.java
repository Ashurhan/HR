package com.example.HR.mapper;

import com.example.HR.dto.notification.NotificationResponse;
import com.example.HR.entity.models.Notification;
import org.springframework.stereotype.Component;

import java.util.List;


public interface NotificationMapper {
    NotificationResponse toDto(Notification notification);

    List<NotificationResponse> toDtos(List<Notification> responseList);
}
