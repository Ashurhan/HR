package com.example.HR.mapper;

import com.example.HR.dto.employer.EmployerRequest;
import com.example.HR.dto.employer.EmployerResponse;
import com.example.HR.entity.models.Employer;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;

public interface EmployerMapper {
    List<EmployerResponse> toDtoS(List<Employer> all);
    EmployerResponse toDto(Employer emp);
    Employer toEntity(EmployerRequest employerRequest, PasswordEncoder passwordEncoder);
}


