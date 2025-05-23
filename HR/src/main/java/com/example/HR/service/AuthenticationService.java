package com.example.HR.service;

import com.example.HR.dto.applicant.RegisterApplicantRequest;
import com.example.HR.dto.auth.AuthenticationRequest;
import com.example.HR.dto.auth.AuthenticationResponse;
import com.example.HR.dto.auth.GoogleLoginRequest;
import com.example.HR.dto.employer.RegisterEmployerRequest;
import org.springframework.http.ResponseEntity;


public interface AuthenticationService {
    AuthenticationResponse login(AuthenticationRequest request);
    String googleLogin(GoogleLoginRequest request);
    AuthenticationResponse authenticate(AuthenticationRequest request);
    ResponseEntity<AuthenticationResponse> applicantRegister(RegisterApplicantRequest request);
    boolean validateVerificationCode(String email,String code);
    ResponseEntity<AuthenticationResponse> employerRegister(RegisterEmployerRequest request);
    ResponseEntity<AuthenticationResponse> adminRegister(RegisterApplicantRequest request);

}