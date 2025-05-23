package com.example.HR.controller;

import com.example.HR.dto.employer.EmployerResponse;
import com.example.HR.dto.password.ChangePasswordRequest;
import com.example.HR.repository.EmployerRepository;
import com.example.HR.service.EmployerService;
import com.example.HR.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/employers")

@CrossOrigin(origins = "*", maxAge = 3600)
public class EmployerController {
    private final EmployerService employerService;
    private final EmployerRepository employerRepository;
    private final UserService userService;

    public EmployerController(EmployerService employerService, EmployerRepository employerRepository, UserService userService) {
        this.employerService = employerService;
        this.employerRepository = employerRepository;
        this.userService = userService;
    }

    @PostMapping("change/password")
    public ResponseEntity<String> changePassword(@RequestParam String email, @RequestBody ChangePasswordRequest changePasswordRequest) {
        boolean success = userService.changePassword(email, changePasswordRequest);
        if (success) {
            return ResponseEntity.ok("Password changed successfully");
        } else {
            return ResponseEntity.badRequest().body("Failed to change password. Check your old password and make sure new passwords match.");
        }
    }

    @GetMapping("/{employerId}")
    public ResponseEntity<EmployerResponse> getEmployerProfile(@PathVariable Long employerId) {
        return ResponseEntity.ok(employerService.getEmployerProfile(employerId));
    }

    @GetMapping
    public ResponseEntity<List<EmployerResponse>> getEmployers() {
        return ResponseEntity.ok(employerService.getEmployers());
    }

    @DeleteMapping("/{employerId}")
    public ResponseEntity<?> deleteEmployer(@PathVariable Long employerId) {
        employerService.deleteEmployer(employerId);
        return ResponseEntity.ok().build();
    }
}
