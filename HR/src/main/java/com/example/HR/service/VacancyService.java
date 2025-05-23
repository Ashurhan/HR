package com.example.HR.service;

import com.example.HR.dto.applicant.ApplicantVacanciesResponses;
import com.example.HR.dto.vacancy.VacancyRequest;
import com.example.HR.dto.vacancy.VacancyResponse;
import com.example.HR.entity.models.Category;
import com.example.HR.entity.models.Vacancy;
import com.example.HR.entity.enums.Status;

import java.nio.file.AccessDeniedException;
import java.util.List;

public interface VacancyService {
    VacancyResponse createVacancy(VacancyRequest request) throws AccessDeniedException;
    List<Vacancy> getMyVacancies(Long employerId);
    List<Vacancy> getVacanciesByStatus(Long employerId, Status status);
    List<Vacancy> getVacanciesByResponses(Long employerId);
    List<Vacancy> getVacanciesByCreatedAt(Long employerId);
    List<Vacancy> getVacanciesByCategory(Category category);
    Vacancy editVacancy(Long id, VacancyRequest updatedVacancy, Long employerId);
    Vacancy update(Long id, VacancyRequest vacancyRequest);
    List<ApplicantVacanciesResponses> searchVacancy(String search);
    VacancyResponse aboutVacancy(Long id);

    void archiveVacancy(Long id, Long employerId);
    void openVacancy(Long id, Long employerId);
    void closeVacancy(Long id, Long employerId);
    void deleteVacancy(Long id, Long employerId);
    boolean delete(Long id);

}



