package com.example.HR.repository;

import com.example.HR.entity.models.Chat;
import com.example.HR.entity.models.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MessageRepository extends JpaRepository<Message, Long> {
    List<Message> findByChatOrderBySentAtAsc(Chat chat);
    void deleteByChat(Chat chat);
}
