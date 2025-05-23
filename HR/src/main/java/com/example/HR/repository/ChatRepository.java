package com.example.HR.repository;

import com.example.HR.entity.models.Chat;
import com.example.HR.entity.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ChatRepository extends JpaRepository<Chat, Long> {
    List<Chat> findByUser1OrUser2OrderByLastMessageAtDesc(User user1, User user2);
    Optional<Chat> findByUser1AndUser2(User user1, User user2);
    boolean existsByUser1AndUser2(User user1, User user2);
} 