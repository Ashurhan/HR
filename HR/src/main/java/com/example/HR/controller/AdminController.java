package com.example.HR.controller;


import com.example.HR.dto.user.UserResponse;
import com.example.HR.service.AdminService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin")
public class AdminController {
    private final AdminService adminService;
    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }
//    @GetMapping("/get/all/users")
//    public ResponseEntity<List<UserResponse>> getAllUsers() {
//        return ResponseEntity.ok(adminService.getAllUsers());
//    }
//    @DeleteMapping("/delete/user")
//    public ResponseEntity<Void> deleteUser(@PathVariable Long id,@RequestParam String role) {
//        adminService.deleteUser(id,role);
//        return ResponseEntity.ok().build();
//
//    }
}
