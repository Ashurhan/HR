package com.example.HR.controller;

import com.example.HR.entity.models.Chat;
import com.example.HR.entity.models.Message;
import com.example.HR.entity.models.User;
import com.example.HR.service.ChatService;
import com.example.HR.service.MessageService;
import com.example.HR.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/messages")
@CrossOrigin(origins = "*", maxAge = 3600)
public class MessageController {

    private final MessageService messageService;
    private final ChatService chatService;
    private final UserService userService;

    public MessageController(MessageService messageService, ChatService chatService, UserService userService) {
        this.messageService = messageService;
        this.chatService = chatService;
        this.userService = userService;
    }

    @PostMapping("/chat/{chatId}/sender/{senderId}")
    public ResponseEntity<Message> sendMessage(
            @PathVariable Long chatId,
            @PathVariable Long senderId,
            @RequestBody String content,
            @RequestParam(required = false) List<Long> attachmentIds) {
        Chat chat = chatService.getChat(chatId);
        User sender = userService.getUserById(senderId);
        return ResponseEntity.ok(messageService.sendMessage(chat, sender, content, attachmentIds));
    }

    @GetMapping("/chat/{chatId}")
    public ResponseEntity<List<Message>> getChatMessages(@PathVariable Long chatId) {
        Chat chat = chatService.getChat(chatId);
        return ResponseEntity.ok(messageService.getChatMessages(chat));
    }


    @PostMapping("/chat/{chatId}/read/user/{userId}")
    public ResponseEntity<?> markMessagesAsRead(
            @PathVariable Long chatId,
            @PathVariable Long userId) {
        Chat chat = chatService.getChat(chatId);
        User user = userService.getUserById(userId);
        messageService.markMessagesAsRead(chat, user);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{messageId}")
    public ResponseEntity<?> deleteMessage(@PathVariable Long messageId) {
        messageService.deleteMessage(messageId);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/chat/{chatId}/unread/user/{userId}")
    public ResponseEntity<Integer> getUnreadMessageCount(
            @PathVariable Long chatId,
            @PathVariable Long userId) {
        Chat chat = chatService.getChat(chatId);
        User user = userService.getUserById(userId);
        return ResponseEntity.ok(messageService.getUnreadMessageCount(chat, user));
    }

    @GetMapping("/{messageId}")
    public ResponseEntity<Message> getMessage(@PathVariable Long messageId) {
        return ResponseEntity.ok(messageService.getMessage(messageId));
    }
}