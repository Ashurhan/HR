package com.example.HR.mapper;

import com.example.HR.dto.vacancy.VacancyRequest;
import com.example.HR.dto.vacancy.VacancyResponse;
import com.example.HR.entity.models.Vacancy;

import java.util.List;

public interface VacancyMapper {
    List<VacancyResponse> toDtoS(List<Vacancy> all);
    Vacancy toEntity(VacancyRequest request);
    VacancyResponse toDto(Vacancy vacancy);
    VacancyResponse requestToResponse(VacancyRequest vacancyRequest);

}
