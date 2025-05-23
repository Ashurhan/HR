package com.example.HR.service;

import com.example.HR.dto.auth.ResetPasswordWithGoogleRequest;
import com.example.HR.dto.password.ChangePasswordRequest;
import com.example.HR.entity.models.User;

public interface UserService {
    void resetPasswordWithGoogle(ResetPasswordWithGoogleRequest request);
    boolean changePassword(String email, ChangePasswordRequest request);

    User getUserById(Long user2Id);
    User findByEmail(String email);
    User save(User user);
}
