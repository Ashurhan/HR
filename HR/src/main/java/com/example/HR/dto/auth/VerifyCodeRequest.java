package com.example.HR.dto.auth;

import lombok.Data;

@Data
public class VerifyCodeRequest {
    private String email;
    private String verificationCode;
} 