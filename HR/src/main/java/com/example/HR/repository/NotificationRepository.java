package com.example.HR.repository;

import com.example.HR.entity.models.Notification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;


public interface NotificationRepository extends JpaRepository<Notification, Long> {
    @Query("SELECT COUNT(n) FROM Notification n WHERE n.user.id = :userId AND n.isRead = false")
    Integer countUnreadNotifications(@Param("userId") Long userId);
    List<Notification> findByUser_Id(Long userId);
}
