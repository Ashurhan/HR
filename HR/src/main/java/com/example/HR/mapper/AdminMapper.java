package com.example.HR.mapper;

import com.example.HR.dto.admin.ResponsesForAdmin;
import com.example.HR.entity.models.Applicant;
import com.example.HR.entity.models.User;
import com.example.HR.entity.models.Employer;


import java.util.List;

public interface AdminMapper {
    ResponsesForAdmin toDto(User user);
    List<ResponsesForAdmin> toDtos(List<User> users);

    ResponsesForAdmin toDtoEmployer(Employer employer);
    List<ResponsesForAdmin> toDtosEmployer(List<Employer> employers);

    ResponsesForAdmin toDtoApplicant(Applicant applicant);
    List<ResponsesForAdmin> toDtosApplicant(List<Applicant> applicants);
}
