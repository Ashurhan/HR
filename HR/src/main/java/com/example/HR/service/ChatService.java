package com.example.HR.service;

import com.example.HR.entity.models.Chat;
import com.example.HR.entity.models.User;

import java.util.List;

public interface ChatService {
    Chat createChat(User user1, User user2);
    Chat getChat(Long chatId);
    List<Chat> getUserChats(User user);
    void deleteChat(Long chatId);
    boolean existsChat(User user1, User user2);
} 