package com.example.HR.service.impl;

import com.example.HR.service.EmailService;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
public class EmailServiceImpl implements EmailService {

    @Autowired
    private JavaMailSender mailSender;

    @Value("${spring.mail.username}")
    private String fromEmail;

    @Override
    public void sendVerificationCode(String toEmail, String code) {
        try {
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, false); // false, если нет вложений
            helper.setFrom(fromEmail);
            helper.setTo(toEmail);
            helper.setSubject("Ваш код подтверждения");
            helper.setText("<h1>Ваш код подтверждения</h1><p>Ваш код: <b>" + code + "</b></p>", true);

            mailSender.send(message);
        } catch (MessagingException e) {
            throw new IllegalStateException("Failed to create verification email for " + toEmail + ": " + e.getMessage());
        } catch (MailException e) {
            throw new IllegalStateException("Failed to send verification email to " + toEmail + ": " + e.getMessage());
        }
    }

    @Override
    public void sendEmail(String to, String subject, String message) {
        try {
            MimeMessage mimeMessage = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, false);
            helper.setFrom(fromEmail);
            helper.setTo(to);
            helper.setSubject(subject);
            helper.setText(message, true);
            mailSender.send(mimeMessage);
        } catch (Exception e) {
            throw new IllegalStateException("Failed to send email to " + to + ": " + e.getMessage());
        }
    }

}