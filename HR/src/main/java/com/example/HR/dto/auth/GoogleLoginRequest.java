package com.example.HR.dto.auth;

import com.example.HR.entity.enums.UserRole;

public class GoogleLoginRequest {
    private String googleId;
    private String email;
    private String name;
    private UserRole role;

    public String getGoogleId() { return googleId; }
    public void setGoogleId(String googleId) { this.googleId = googleId; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public UserRole getRole() { return role; }
    public void setRole(UserRole role) { this.role = role; }

}


