package com.example.HR.mapper.impl;

import com.example.HR.dto.employer.EmployerRequest;
import com.example.HR.dto.employer.EmployerResponse;
import com.example.HR.entity.models.Employer;
import com.example.HR.mapper.EmployerMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class EmployerMapperImpl implements EmployerMapper {
    @Override
    public  List<EmployerResponse> toDtoS(List<Employer> all){
        List<EmployerResponse> dtos = new ArrayList<>();
        for(Employer emp : all) {
            dtos.add(toDto(emp));
        }
        return dtos;
    }

    @Override
    public EmployerResponse toDto(Employer emp) {
        EmployerResponse dto = new EmployerResponse();
        dto.setId(emp.getId());
        dto.setEmail(emp.getEmail());
        dto.setNameOfCompany(emp.getNameOfCompany());
        return dto;
    }

    @Override
    public Employer toEntity(EmployerRequest employerRequest, PasswordEncoder passwordEncoder) {
        Employer employer = new Employer();
        employer.setEmail(employerRequest.getEmail());
        employer.setNameOfCompany(employerRequest.getNameOfCompany());
        employer.setPassword(passwordEncoder.encode(employerRequest.getPassword()));
        return employer;

    }



}
