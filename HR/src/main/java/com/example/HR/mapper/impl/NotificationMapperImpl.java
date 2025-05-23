package com.example.HR.mapper.impl;

import com.example.HR.dto.notification.NotificationResponse;
import com.example.HR.entity.models.Notification;
import com.example.HR.mapper.FileMapper;
import com.example.HR.mapper.NotificationMapper;
import com.example.HR.repository.FileRepository;
import jakarta.persistence.Column;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class NotificationMapperImpl implements NotificationMapper {
    private final FileMapper fileMapper;
    private final FileRepository fileRepository;
    public NotificationMapperImpl(FileMapper fileMapper, FileRepository fileRepository) {
        this.fileMapper = fileMapper;
        this.fileRepository = fileRepository;
    }
    @Override
    public NotificationResponse toDto(Notification notification) {
        if(notification == null) {
            return null;
        }
        NotificationResponse response = new NotificationResponse();
        response.setId(notification.getId());
        response.setUserId(
                notification.getUser()!=null?
                notification.getUser().getId():null
        );
        response.setTitle(notification.getTitle());
        response.setImageId(notification.getImageId());
        response.setArrivedDate(notification.getArrivedDate());
        response.setContent(notification.getContent());
        return response;

    }

    @Override
    public List<NotificationResponse> toDtos(List<Notification> responseList) {
        List<NotificationResponse> responses = new ArrayList<>();
        for(Notification not:responseList){
            responses.add(toDto(not));
        }
        return responses;
    }
}
