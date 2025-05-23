package com.example.HR.service.impl;

import com.example.HR.entity.enums.EducationLevel;
import com.example.HR.entity.enums.Position;
import com.example.HR.entity.enums.ResponseStatus;
import com.example.HR.entity.models.Applicant;
import com.example.HR.entity.models.Country;
import com.example.HR.entity.models.Vacancy;
import com.example.HR.repository.ApplicantRepository;
import com.example.HR.repository.ResponseRepository;
import com.example.HR.repository.VacancyRepository;
import com.example.HR.service.ResponseService;
import com.example.HR.entity.models.Response;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
public class ResponseServiceimpl implements ResponseService {
    private final ResponseRepository responseRepository;
    private final VacancyRepository vacancyRepository;
    private final ApplicantRepository applicantRepository;

    public ResponseServiceimpl(ResponseRepository responseRepository, VacancyRepository vacancyRepository, ApplicantRepository applicantRepository) {
        this.responseRepository = responseRepository;
        this.vacancyRepository = vacancyRepository;
        this.applicantRepository = applicantRepository;
    }

    @Transactional
    public Response createResponse(Long vacancyId, Long applicantId) {
        Vacancy vacancy = vacancyRepository.findById(vacancyId)
                .orElseThrow(() -> new RuntimeException("Вакансия не найдена"));
        Applicant applicant = applicantRepository.findById(applicantId)
                .orElseThrow(() -> new RuntimeException("Пользователь не найден"));
        if (responseRepository.existsByVacancyIdAndApplicantId(vacancyId, applicantId)) {
            throw new RuntimeException("Вы уже откликнулись на эту вакансию");
        }
        Response response = new Response();
        response.setVacancy(vacancy);
        response.setApplicant(applicant);
        response.setStatus(ResponseStatus.UNDER_CONSIDERATION);
        return responseRepository.save(response);
    }

    @Override
    public List<Response> allResponses(Long vacancyId) {
        return responseRepository.findAllByVacancyId(vacancyId);
    }

    @Override
    public List<Applicant> filterApplicants(Long vacancyId, String firstName, Position position, Country country,
                                            String city, EducationLevel educationLevel, Integer minYears, Integer maxYears) {
        if (!vacancyRepository.existsById(vacancyId)) {
            throw new RuntimeException("Вакансия с ID " + vacancyId + " не найдена");
        }
        Long countryId = country != null ? country.getId() : null;

        Integer currentYear = LocalDate.now().getYear();
        return responseRepository.findApplicantsByVacancyIdWithFilters(
                vacancyId, firstName, position, countryId, city, educationLevel, minYears, maxYears, currentYear);
    }

}


