package com.example.HR.controller;


import com.example.HR.dto.applicant.RegisterApplicantRequest;
import com.example.HR.dto.auth.AuthenticationRequest;
import com.example.HR.dto.auth.AuthenticationResponse;
import com.example.HR.dto.employer.RegisterEmployerRequest;
import com.example.HR.entity.models.Applicant;
import com.example.HR.entity.models.User;
import com.example.HR.service.AuthenticationService;
import com.example.HR.service.UserService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@RestController
@RequestMapping("/api/v1/auth")

@CrossOrigin(origins = "*", maxAge = 3600)
public class AuthenticationController {

    private final AuthenticationService service;

    private final UserService userService;

    public AuthenticationController(AuthenticationService service, UserService userService) {
        this.service = service;
        this.userService = userService;
    }

    @PostMapping("/register/admin")
    public ResponseEntity<?> adminRegister(@RequestBody RegisterApplicantRequest request) {
        return service.adminRegister(request);
    }

    @PostMapping("/register/app")
    public ResponseEntity<?> jobSeekerRegister(@RequestBody RegisterApplicantRequest request) {
        return service.applicantRegister(request);
    }

    @PostMapping("/register/emp")
    public ResponseEntity<?> employerRegister(@RequestBody RegisterEmployerRequest request) {
        return service.employerRegister(request);
    }

    @PostMapping("/authenticate")
    public ResponseEntity<?> authenticate(@RequestBody AuthenticationRequest request) {
        return ResponseEntity.ok(service.authenticate(request));
    }
    @PostMapping("/verify")
    public String verify(@RequestParam String email, @RequestParam String code){
        boolean isValid=service.validateVerificationCode(email,code);
        if(isValid){
            return "Email succesfully verified";
        }
        else {
            return "Email verification failed";
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@Valid @RequestBody AuthenticationRequest authLoginRequest) {
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String formattedDate = now.format(formatter);
        
        try {
            AuthenticationResponse response = service.login(authLoginRequest);
            
            // Update last visit time
            User user = userService.findByEmail(authLoginRequest.getEmail());
            if (user == null) {
                throw new IllegalArgumentException("User not found with email: " + authLoginRequest.getEmail());
            }
            user.setLastVisit(formattedDate);
            userService.save(user);
            
            return ResponseEntity.ok(response);
        } catch (AuthenticationException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body("Invalid email or password");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("An error occurred during login: " + e.getMessage());
        }
    }
}