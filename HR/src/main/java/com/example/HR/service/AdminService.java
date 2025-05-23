package com.example.HR.service;

import com.example.HR.dto.GenericResponseForUserResponses;
import com.example.HR.dto.admin.ResponsesForAdmin;
import com.example.HR.dto.admin.ResponsesForSupport;
import com.example.HR.dto.admin.SupportRequest;
import com.example.HR.dto.user.UserResponse;
import com.example.HR.entity.models.User;
import com.nimbusds.oauth2.sdk.Response;
import org.springframework.security.core.Authentication;

import java.util.List;

public interface AdminService {
    List<ResponsesForAdmin> getAllUsers();
    void block(String email);

    User isAuth(Authentication authentication);
    List<ResponsesForSupport> getListSupport();
    ResponsesForSupport createSupport(SupportRequest request);

    GenericResponseForUserResponses setRoleForUser(Long userId, String role);
    GenericResponseForUserResponses getResponsesForUserById(Long userId);

    boolean userBlocked(Long userId, Boolean aBoolean);


    boolean listForDeletingUsers(List<Long> selectedUserIds);
    boolean listForDeletingVacancy(List<Long> selectedVacancyIds);

    boolean deleteByAccount(String email);


    void deleteUser(Long id, String role);
}
