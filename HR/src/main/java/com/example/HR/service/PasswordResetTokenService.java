package com.example.HR.service;

import com.example.HR.entity.models.PasswordResetToken;

public interface PasswordResetTokenService {
    PasswordResetToken createToken(String email);
    boolean isValid(String token);
    String getEmailByToken(String token);
    void markUsed(String token);
}
