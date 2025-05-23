package com.example.HR.service;

import com.example.HR.entity.enums.EducationLevel;
import com.example.HR.entity.enums.Position;
import com.example.HR.entity.models.Applicant;
import com.example.HR.entity.models.Country;
import com.example.HR.entity.models.Response;

import java.util.List;

public interface ResponseService {
    Response createResponse(Long vacancyId, Long applicantId);
    List<Response> allResponses(Long vacancyId);

    List<Applicant> filterApplicants(Long vacancyId, String firstName, Position position, Country country,
                                     String city, EducationLevel educationLevel, Integer minYears, Integer maxYears);
}