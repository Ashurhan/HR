package com.example.HR.service.impl;

import com.example.HR.entity.enums.UserRole;
import com.example.HR.entity.models.Notification;
import com.example.HR.entity.models.User;
import com.example.HR.exception.NotFoundException;
import com.example.HR.mapper.NotificationMapper;
import com.example.HR.repository.ApplicantRepository;
import com.example.HR.repository.NotificationRepository;
import com.example.HR.repository.UserRepository;
import com.example.HR.service.NotificationService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NotificationsServiceImpl implements NotificationService {

    private final NotificationRepository notificationRepository;
    private final ApplicantRepository applicantRepository;
    private final UserRepository userRepository;
    private final NotificationMapper notificationMapper;

    public NotificationsServiceImpl(NotificationRepository notificationRepository, ApplicantRepository applicantRepository, UserRepository userRepository, NotificationMapper notificationMapper) {
        this.notificationRepository = notificationRepository;
        this.applicantRepository = applicantRepository;
        this.userRepository = userRepository;
        this.notificationMapper = notificationMapper;
    }

    @Override
    public Notification createNotificationStorage(Notification notificationStorage) {
        notificationStorage.setRead(false);
        return notificationRepository.save(notificationStorage);
    }

    @Override
    public void sendNotificationToAllUsers(String content) {
        List<User> users= userRepository.findEmployersAndApplicants(
                List.of(UserRole.EMPLOYER,UserRole.APPLICANT)
        );
        for(User user:users) {
            notificationMapper.toDtos(user.getNotification());
        }
    }

    @Override
    public List<Notification> getNotifs(Long userID) {
        List<Notification> notifications= notificationRepository.findByUser_Id(userID);
        return notifications;
    }

    @Override
    public Integer countUnreadNotificationsForUser(Long userId) {
        return notificationRepository.countUnreadNotifications(userId);
    }
}
