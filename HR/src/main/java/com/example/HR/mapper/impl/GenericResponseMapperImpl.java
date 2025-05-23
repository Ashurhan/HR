package com.example.HR.mapper.impl;

import com.example.HR.dto.GenericResponseForUserResponses;
import com.example.HR.dto.applicant.ApplicantResponse;
import com.example.HR.dto.employer.EmployerResponse;
import com.example.HR.mapper.GenericResponseMapper;
import org.springframework.stereotype.Component;

@Component
public class GenericResponseMapperImpl implements GenericResponseMapper {
    
    @Override
    public GenericResponseForUserResponses toGenericEResponse(EmployerResponse employerResponse) {
        if (employerResponse == null) {
            return null;
        }
        GenericResponseForUserResponses response = new GenericResponseForUserResponses();
        response.setId(employerResponse.getId());
        return response;
    }

    @Override
    public GenericResponseForUserResponses toGenericAResponse(ApplicantResponse applicantResponse) {
        if (applicantResponse == null) {
            return null;
        }
        GenericResponseForUserResponses response = new GenericResponseForUserResponses();
        response.setId(applicantResponse.getId());
        return response;
    }
} 