package com.example.HR.service.impl;

import com.example.HR.entity.models.*;
import com.example.HR.entity.enums.MessageType;
import com.example.HR.repository.MessageInfoRepository;
import com.example.HR.repository.MessageRepository;
import com.example.HR.repository.UserMessagesRepository;
import com.example.HR.service.MessageService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MessageServiceImpl implements MessageService {
    private final MessageRepository messageRepository;
    private final MessageInfoRepository messageInfoRepository;
    private final UserMessagesRepository userMessagesRepository;

    @Override
    @Transactional
    public Message sendMessage(Chat chat, User sender, String content, List<Long> attachmentIds) {
        Message message = new Message();
        message.setChat(chat);
        message.setSender(sender);
        message.setContent(content);
        message.setAttachmentIds(attachmentIds);
        message.setType(attachmentIds != null && !attachmentIds.isEmpty() ? MessageType.FILE : MessageType.TEXT);
        
        chat.updateLastMessageTime();
        message = messageRepository.save(message);

        User recipient = chat.getUser1().equals(sender) ? chat.getUser2() : chat.getUser1();
        
        // Create MessageInfo for sender
        createMessageInfo(message, sender, true);
        
        // Create MessageInfo for recipient
        createMessageInfo(message, recipient, false);

        return message;
    }

    private void createMessageInfo(Message message, User user, boolean isSender) {
        MessageInfo messageInfo = new MessageInfo();
        messageInfo.setMessage(message);
        messageInfo.setUser(user);
        messageInfo.setRead(isSender);
        if (isSender) {
            messageInfo.setReadAt(LocalDateTime.now());
        }
        messageInfoRepository.save(messageInfo);

        // Update UserMessages
        UserMessages userMessages = userMessagesRepository.findByUser(user)
                .orElseGet(() -> {
                    UserMessages newUserMessages = new UserMessages();
                    newUserMessages.setUser(user);
                    return userMessagesRepository.save(newUserMessages);
                });

        if (!isSender) {
            userMessages.incrementUnreadCount();
        }
        userMessages.updateLastMessageTime();
        userMessagesRepository.save(userMessages);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Message> getChatMessages(Chat chat) {
        return messageRepository.findByChatOrderBySentAtAsc(chat);
    }

    @Override
    @Transactional
    public void markMessagesAsRead(Chat chat, User user) {
        List<Message> messages = messageRepository.findByChatOrderBySentAtAsc(chat);
        for (Message message : messages) {
            MessageInfo messageInfo = messageInfoRepository.findByMessageAndUser(message, user)
                    .orElseThrow(() -> new EntityNotFoundException("MessageInfo not found"));
            
            if (!messageInfo.isRead()) {
                messageInfo.setRead(true);
                messageInfo.setReadAt(LocalDateTime.now());
                messageInfoRepository.save(messageInfo);

                UserMessages userMessages = userMessagesRepository.findByUser(user)
                        .orElseThrow(() -> new EntityNotFoundException("UserMessages not found"));
                userMessages.decrementUnreadCount();
                userMessagesRepository.save(userMessages);
            }
        }
    }

    @Override
    @Transactional
    public void deleteMessage(Long messageId) {
        Message message = messageRepository.findById(messageId)
                .orElseThrow(() -> new EntityNotFoundException("Message not found"));
        
        List<MessageInfo> messageInfos = message.getMessageInfos();
        for (MessageInfo messageInfo : messageInfos) {
            messageInfo.setDeleted(true);
            messageInfo.setDeletedAt(LocalDateTime.now());
            messageInfoRepository.save(messageInfo);
        }
    }

    @Override
    @Transactional(readOnly = true)
    public int getUnreadMessageCount(Chat chat, User user) {
        UserMessages userMessages = userMessagesRepository.findByUser(user)
                .orElseThrow(() -> new EntityNotFoundException("UserMessages not found"));
        return userMessages.getUnreadCount();
    }

    @Override
    @Transactional(readOnly = true)
    public Message getMessage(Long messageId) {
        return messageRepository.findById(messageId)
                .orElseThrow(() -> new EntityNotFoundException("Message not found"));
    }
}
