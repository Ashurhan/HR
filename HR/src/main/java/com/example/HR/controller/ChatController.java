package com.example.HR.controller;

import com.example.HR.entity.models.Chat;
import com.example.HR.entity.models.User;
import com.example.HR.service.ChatService;
import com.example.HR.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/chats")
@CrossOrigin(origins = "*", maxAge = 3600)
public class ChatController {

    private final ChatService chatService;
    private final UserService userService;

    public ChatController(ChatService chatService, UserService userService) {
        this.chatService = chatService;
        this.userService = userService;
    }

    @PostMapping("/users/{user1Id}/{user2Id}")
    public ResponseEntity<Chat> createChat(
            @PathVariable Long user1Id,
            @PathVariable Long user2Id) {
        User user1 = userService.getUserById(user1Id);
        User user2 = userService.getUserById(user2Id);
        return ResponseEntity.ok(chatService.createChat(user1, user2));
    }

    @GetMapping("/{chatId}")
    public ResponseEntity<Chat> getChat(@PathVariable Long chatId) {
        return ResponseEntity.ok(chatService.getChat(chatId));
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Chat>> getUserChats(@PathVariable Long userId) {
        User user = userService.getUserById(userId);
        return ResponseEntity.ok(chatService.getUserChats(user));
    }

    @DeleteMapping("/{chatId}")
    public ResponseEntity<?> deleteChat(@PathVariable Long chatId) {
        chatService.deleteChat(chatId);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/exists/{user1Id}/{user2Id}")
    public ResponseEntity<Boolean> existsChat(
            @PathVariable Long user1Id,
            @PathVariable Long user2Id) {
        User user1 = userService.getUserById(user1Id);
        User user2 = userService.getUserById(user2Id);
        return ResponseEntity.ok(chatService.existsChat(user1, user2));
    }
} 