package com.example.HR.mapper;

import com.example.HR.dto.applicant.*;
import com.example.HR.entity.models.Applicant;
import com.example.HR.entity.models.Education;
import com.example.HR.entity.models.Experience;
import com.example.HR.entity.models.Vacancy;

import java.util.List;

public interface ApplicantMapper {
    ApplicantPersonalResponse toPersonalResponse(Applicant applicant);
    ApplicantProfessionResponse toProfessionResponse(Applicant applicant);
    void updateEntityFromPersonal(Applicant applicant, ApplicantPersonalRequest request) ;
    void updateEntityFromProfession(Applicant applicant, ApplicantProfessionRequest request) ;

        // Методы для Experience
    Experience toEntityExperience(ExperienceRequest request);
    ExperienceResponse toDtoExperience(Experience experience);
    
    // Методы для Education
    Education toEntityEducation(EducationRequest request);
    EducationResponce toDtoEducation(Education education);
    
    // Методы для создания сущности
    Applicant toEntityFromPersonal(ApplicantPersonalRequest request);
    Applicant toEntityFromProfession(ApplicantProfessionRequest request);

    ApplicantResponse toResponse(Applicant applicant);

    ApplicantVacanciesResponses convertToVacancyApplicantResponse(Vacancy vacancy);

    List<ApplicantVacanciesResponses> convertToVacancyApplicantResponses(List<Vacancy> vacancies);


}

