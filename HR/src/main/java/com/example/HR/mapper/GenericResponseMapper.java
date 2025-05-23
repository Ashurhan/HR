package com.example.HR.mapper;

import com.example.HR.dto.GenericResponseForUserResponses;
import com.example.HR.dto.applicant.ApplicantResponse;
import com.example.HR.dto.employer.EmployerResponse;

public interface GenericResponseMapper {
    GenericResponseForUserResponses toGenericEResponse(EmployerResponse employerResponse);
    GenericResponseForUserResponses toGenericAResponse(ApplicantResponse applicantResponse);
} 