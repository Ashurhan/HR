package com.example.HR.dto;

import com.example.HR.entity.enums.UserRole;
import com.example.HR.dto.file.FileResponse;
import com.example.HR.entity.models.UserMessages;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserResponse {
    private Long id;
    private String firstname;
    private String lastname;
    private String companyName;
    private String email;
    private UserMessages userMessages;
    private UserRole role;
    private boolean isOnline;
    private FileResponse fileResponse;

    public int getUnReadMessages() {
        return userMessages != null ? userMessages.getUnreadCount() : 0;
    }


}