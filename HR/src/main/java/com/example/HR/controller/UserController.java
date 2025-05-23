package com.example.HR.controller;

import com.example.HR.dto.auth.ResetPasswordWithGoogleRequest;
import com.example.HR.dto.password.ChangePasswordRequest;
import com.example.HR.entity.models.User;
import com.example.HR.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/users")
@CrossOrigin(origins = "*", maxAge = 3600)
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/reset-password/google")
    public ResponseEntity<?> resetPasswordWithGoogle(@RequestBody ResetPasswordWithGoogleRequest request) {
        userService.resetPasswordWithGoogle(request);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/{email}/change-password")
    public ResponseEntity<?> changePassword(
            @PathVariable String email,
            @RequestBody ChangePasswordRequest request) {
        boolean success = userService.changePassword(email, request);
        if (success) {
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.badRequest().body("Failed to change password");
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Long id) {
        User user = userService.getUserById(id);
        return ResponseEntity.ok(user);
    }

    @GetMapping("/email/{email}")
    public ResponseEntity<User> getUserByEmail(@PathVariable String email) {
        User user = userService.findByEmail(email);
        return ResponseEntity.ok(user);
    }
} 