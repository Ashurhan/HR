package com.example.HR.service.impl;

import com.example.HR.dto.employer.EmployerResponse;
import com.example.HR.entity.models.Employer;
import com.example.HR.mapper.EmployerMapper;
import com.example.HR.repository.EmployerRepository;
import com.example.HR.service.EmployerService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployerServiceImpl implements EmployerService {
    private final EmployerRepository employerRepository;
    private final EmployerMapper employerMapper;

    public EmployerServiceImpl(EmployerRepository employerRepository, EmployerMapper employerMapper) {
        this.employerRepository = employerRepository;
        this.employerMapper = employerMapper;
    }



    @Override
    public EmployerResponse getEmployerProfile(Long id){
        Employer employer=employerRepository.findById(id).orElseThrow(()-> new RuntimeException("Employer Not Found"));
        return employerMapper.toDto(employer);
    }

    @Override
    public List<EmployerResponse> getEmployers(){
        List<Employer> employers=employerRepository.findAll();
        return employerMapper.toDtoS(employers);
    }
    @Override
    public  void deleteEmployer(Long id){
        employerRepository.deleteById(id);
    }

}
