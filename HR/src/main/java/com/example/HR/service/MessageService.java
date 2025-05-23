package com.example.HR.service;

import com.example.HR.entity.models.Chat;
import com.example.HR.entity.models.Message;
import com.example.HR.entity.models.User;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface MessageService {
    Message sendMessage(Chat chat, User sender, String content, List<Long> attachmentIds);
    List<Message> getChatMessages(Chat chat);
    void markMessagesAsRead(Chat chat, User user);
    void deleteMessage(Long messageId);
    int getUnreadMessageCount(Chat chat, User user);
    Message getMessage(Long messageId);
}


