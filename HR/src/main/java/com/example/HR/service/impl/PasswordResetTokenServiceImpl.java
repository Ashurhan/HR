package com.example.HR.service.impl;

import com.example.HR.entity.models.PasswordResetToken;
import com.example.HR.repository.PasswordResetTokenRepository;
import com.example.HR.service.PasswordResetTokenService;
import com.example.HR.service.EmailService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class PasswordResetTokenServiceImpl implements PasswordResetTokenService {

    private final PasswordResetTokenRepository tokenRepository;
    private final EmailService emailService;

    public PasswordResetTokenServiceImpl(PasswordResetTokenRepository tokenRepository, EmailService emailService) {
        this.tokenRepository = tokenRepository;
        this.emailService = emailService;
    }


    @Override
    public PasswordResetToken createToken(String email) {
        PasswordResetToken token = new PasswordResetToken();
        token.setToken(UUID.randomUUID().toString());
        token.setEmail(email);
        token.setCreatedAt(LocalDateTime.now());
        token.setUsed(false);
        return tokenRepository.save(token);
    }

    @Override
    public boolean isValid(String token) {
        return tokenRepository.findByToken(token)
                .filter(t -> !t.isUsed() && t.getCreatedAt().isAfter(LocalDateTime.now().minusMinutes(15)))
                .isPresent();
    }

    @Override
    public String getEmailByToken(String token) {
        return tokenRepository.findByToken(token)
                .filter(t -> !t.isUsed())
                .map(PasswordResetToken::getEmail)
                .orElseThrow(() -> new RuntimeException("Invalid or expired token"));
    }

    @Override
    public void markUsed(String token) {
        tokenRepository.findByToken(token).ifPresent(t -> {
            t.setUsed(true);
            tokenRepository.save(t);
        });
    }

    // Метод для отправки письма с двумя ссылками
    public void sendResetConfirmationEmail(String email, String token) {
        String confirmUrl = "https://your-app.com/reset-password/confirm?token=" + token;
        String denyUrl = "https://your-app.com/reset-password/deny?token=" + token;
        String message = "Вы запрашивали смену пароля? <a href='" + confirmUrl + "'>Да</a> / <a href='" + denyUrl + "'>Нет</a>";
        emailService.sendEmail(email, "Подтвердите сброс пароля", message);
    }
}
