package com.example.HR.repository;

import com.example.HR.entity.models.Message;
import com.example.HR.entity.models.MessageInfo;
import com.example.HR.entity.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MessageInfoRepository extends JpaRepository<MessageInfo, Long> {
    List<MessageInfo> findByUserAndIsReadFalse(User user);
    Optional<MessageInfo> findByMessageAndUser(Message message, User user);
    void deleteByMessage(Message message);
    void deleteByUser(User user);
} 