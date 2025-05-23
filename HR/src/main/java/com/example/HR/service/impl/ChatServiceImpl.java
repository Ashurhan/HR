package com.example.HR.service.impl;

import com.example.HR.entity.models.Chat;
import com.example.HR.entity.models.User;
import com.example.HR.repository.ChatRepository;
import com.example.HR.service.ChatService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ChatServiceImpl implements ChatService {
    private final ChatRepository chatRepository;

    @Override
    @Transactional
    public Chat createChat(User user1, User user2) {
        if (chatRepository.existsByUser1AndUser2(user1, user2)) {
            throw new IllegalStateException("Chat already exists between these users");
        }
        Chat chat = new Chat();
        chat.setUser1(user1);
        chat.setUser2(user2);
        return chatRepository.save(chat);
    }

    @Override
    @Transactional(readOnly = true)
    public Chat getChat(Long chatId) {
        return chatRepository.findById(chatId)
                .orElseThrow(() -> new EntityNotFoundException("Chat not found with id: " + chatId));
    }

    @Override
    @Transactional(readOnly = true)
    public List<Chat> getUserChats(User user) {
        return chatRepository.findByUser1OrUser2OrderByLastMessageAtDesc(user, user);
    }

    @Override
    @Transactional
    public void deleteChat(Long chatId) {
        chatRepository.deleteById(chatId);
    }

    @Override
    @Transactional(readOnly = true)
    public boolean existsChat(User user1, User user2) {
        return chatRepository.existsByUser1AndUser2(user1, user2);
    }
} 