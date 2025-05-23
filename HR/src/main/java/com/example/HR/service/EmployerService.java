package com.example.HR.service;


import com.example.HR.dto.employer.EmployerResponse;


import java.util.List;

public interface EmployerService {
    EmployerResponse getEmployerProfile(Long employerId);
    List<EmployerResponse> getEmployers();
    void deleteEmployer(Long employerId);
}
