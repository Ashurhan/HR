package com.example.HR.mapper.impl;

import com.example.HR.dto.salary.SalaryRequest;
import com.example.HR.dto.salary.SalaryResponse;
import com.example.HR.entity.models.Salary;
import com.example.HR.mapper.SalaryMapper;
import com.example.HR.exception.NotFoundException;

//import org.webjars.NotFoundException;

import java.util.ArrayList;
import java.util.List;

public class SalaryMapperImpl implements SalaryMapper {


    @Override
    public List<SalaryResponse> toDtos(List<Salary> salaries) {
        List<SalaryResponse> salariesResponse = new ArrayList<>();
        for(Salary salary : salaries) {
            salariesResponse.add(toDto(salary));
        }
        return salariesResponse;
    }

    @Override
    public SalaryResponse toDto(Salary salary) {
        if(salary == null) {
            return null;
        }
        SalaryResponse salaryResponse = new SalaryResponse();
        salaryResponse.setId(salary.getId());
        salaryResponse.setSalarySum(salary.getSalarySum());
        salaryResponse.setSalaryType(salary.getSalaryType());
        salaryResponse.setCurrency(salary.getCurrency());
        return salaryResponse;
    }

    @Override
    public Salary toEntity(SalaryRequest salaryRequest) {
        Salary salary = new Salary();
        if (salaryRequest==null)
            throw new NotFoundException("salary is null");

        salary.setSalaryType(salaryRequest.getSalaryType()==null?null:
                salaryRequest.getSalaryType());
        salary.setSalarySum(salaryRequest.getSalarySum());
        salary.setCurrency(salaryRequest.getCurrency()==null?null:
                salaryRequest.getCurrency());
        return salary;
    }
}
