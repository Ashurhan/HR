package com.example.HR.repository;

import com.example.HR.entity.models.User;
import com.example.HR.entity.models.UserMessages;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserMessagesRepository extends JpaRepository<UserMessages, Long> {
    Optional<UserMessages> findByUser(User user);
    void deleteByUser(User user);
} 