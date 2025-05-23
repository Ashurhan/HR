package com.example.HR.controller;

import com.example.HR.service.PasswordResetTokenService;
import com.example.HR.service.UserService;
import com.example.HR.dto.auth.ForgotPasswordRequest;
import com.example.HR.dto.auth.ResetPasswordRequest;
import com.example.HR.dto.auth.ResetPasswordWithGoogleRequest;
import com.example.HR.entity.models.PasswordResetToken;
import com.example.HR.service.EmailService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/password")
public class PasswordResetController {
    private final PasswordResetTokenService tokenService;
    private final UserService userService;
    private final EmailService emailService;

    public PasswordResetController(PasswordResetTokenService tokenService, UserService userService, EmailService emailService) {
        this.tokenService = tokenService;
        this.userService = userService;
        this.emailService = emailService;
    }

    @PostMapping("/forgot")
    public ResponseEntity<?> forgotPassword(@RequestBody ForgotPasswordRequest request) {
        PasswordResetToken token = tokenService.createToken(request.getEmail());
        String confirmUrl = "https://your-app.com/reset-password/confirm?token=" + token.getToken();
        String denyUrl = "https://your-app.com/reset-password/deny?token=" + token.getToken();
        String message = "Вы запрашивали смену пароля? <a href='" + confirmUrl + "'>Да</a> / <a href='" + denyUrl + "'>Нет</a>";
        emailService.sendEmail(request.getEmail(), "Подтвердите сброс пароля", message);
        return ResponseEntity.ok("Письмо отправлено");
    }

    @GetMapping("/reset-password/confirm")
    public ResponseEntity<?> confirmReset(@RequestParam String token) {
        if (!tokenService.isValid(token)) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid or expired token");
        }
        // Фронт перенаправляет на форму смены пароля с этим токеном
        return ResponseEntity.ok("Token valid, show password reset form");
    }

    @PostMapping("/reset-password")
    public ResponseEntity<?> resetPassword(@RequestBody ResetPasswordRequest request) {
        if (!request.getNewPassword().equals(request.getConfirmPassword())) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Passwords do not match");
        }
        if (!tokenService.isValid(request.getToken())) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid or expired token");
        }
        String email = tokenService.getEmailByToken(request.getToken());
        userService.resetPasswordWithGoogle(new ResetPasswordWithGoogleRequest(email, request.getNewPassword(), request.getConfirmPassword()));
        tokenService.markUsed(request.getToken());
        return ResponseEntity.ok("Password changed successfully");
    }

    @GetMapping("/reset-password/deny")
    public ResponseEntity<?> denyReset(@RequestParam String token) {
        tokenService.markUsed(token);
        return ResponseEntity.ok("Запрос на смену пароля отменён.");
    }
} 