package com.example.HR.mapper;

import com.example.HR.dto.applicant.ApplicantVacanciesResponses;
import com.example.HR.entity.models.Vacancy;

public interface ApplicantVacanciesResponsesMapper {
    ApplicantVacanciesResponses toDtos(Vacancy v);
}
