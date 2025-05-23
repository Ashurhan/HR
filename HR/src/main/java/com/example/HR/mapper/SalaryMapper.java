package com.example.HR.mapper;

import com.example.HR.dto.salary.SalaryRequest;
import com.example.HR.dto.salary.SalaryResponse;
import com.example.HR.entity.models.Salary;

import java.util.List;

public interface SalaryMapper {
    List<SalaryResponse> toDtos(List<Salary> salaries);
    SalaryResponse toDto(Salary salary);
    Salary toEntity(SalaryRequest salaryRequest);
}
