package com.example.HR.mapper.impl;

import com.example.HR.dto.applicant.ApplicantVacanciesResponses;
import com.example.HR.entity.models.Vacancy;
import com.example.HR.mapper.ApplicantVacanciesResponsesMapper;
import com.example.HR.mapper.VacancyMapper;
import org.springframework.stereotype.Component;

@Component
public class ApplicantVacanciesResponsesMapperImpl implements ApplicantVacanciesResponsesMapper {
    private final VacancyMapper vacancyMapper;

    public ApplicantVacanciesResponsesMapperImpl(VacancyMapper vacancyMapper) {
        this.vacancyMapper = vacancyMapper;
    }


    @Override
    public ApplicantVacanciesResponses toDtos(Vacancy v) {
        ApplicantVacanciesResponses applicantVacanciesResponses = new ApplicantVacanciesResponses();
        applicantVacanciesResponses.setVacancyResponse(vacancyMapper.toDto(v));
        applicantVacanciesResponses.setId(v.getId());
        applicantVacanciesResponses.setOwnerName(v.getCompanyInfo());
        return applicantVacanciesResponses;
    }


}
