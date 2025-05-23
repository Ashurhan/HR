package com.example.HR.service.impl;

import com.example.HR.dto.applicant.ApplicantVacanciesResponses;
import com.example.HR.dto.vacancy.VacancyRequest;
import com.example.HR.dto.vacancy.VacancyResponse;
import com.example.HR.entity.enums.UserRole;
import com.example.HR.entity.models.*;
import com.example.HR.entity.enums.Status;
import com.example.HR.exception.NotFoundException;
import com.example.HR.mapper.ApplicantMapper;
import com.example.HR.mapper.VacancyMapper;
import com.example.HR.repository.CategoryRepository;
import com.example.HR.repository.VacancyRepository;
import com.example.HR.service.VacancyService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.nio.file.AccessDeniedException;
import java.util.List;
import java.util.Locale;
import java.util.Map;

@Service
public class VacancyServiceImpl implements VacancyService {
    private VacancyRepository vacancyRepository;

    private CategoryRepository categoryRepository;
    private ApplicantMapper applicantMapper;
    private VacancyMapper vacancyMapper;

    public VacancyServiceImpl(VacancyRepository vacancyRepository,CategoryRepository categoryRepository,VacancyMapper vacancyMapper,ApplicantMapper applicantMapper){
        this.vacancyRepository= vacancyRepository;
        this.categoryRepository=categoryRepository;
        this.applicantMapper=applicantMapper;
        this.vacancyMapper=vacancyMapper;
    }


    @Transactional
    public VacancyResponse createVacancy(VacancyRequest request) throws AccessDeniedException {
        User currentUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        // Проверяем, что у пользователя есть роль "EMPLOYER"
        if (!currentUser.getRole().equals(UserRole.EMPLOYER)) {
            throw new AccessDeniedException("Доступ запрещен. Только работодатели могут создавать вакансии.");
        }

        Employer employer = currentUser.getEmployer();
        if (employer == null) {
            throw new IllegalStateException("Работодатель не связан с текущей учетной записью пользователя.");
        }

        Vacancy vacancy = vacancyMapper.toEntity(request);

        vacancy.setEmployerId(employer);

        Vacancy savedVacancy = vacancyRepository.save(vacancy);

        VacancyResponse response = vacancyMapper.toDto(savedVacancy);


        return response;
    }



    public List<Vacancy> getMyVacancies(Long employerId) {
        return vacancyRepository.findByEmployerId_Id(employerId);
    }

    public List<Vacancy> getVacanciesByStatus(Long employerId, Status status) {
        return vacancyRepository.findByEmployerId_IdAndStatus(employerId, status);
    }

    public List<Vacancy> getVacanciesByResponses(Long employerId) {
        return vacancyRepository.findByEmployerId_IdOrderByResponseListSizeDesc(employerId);
    }

    public List<Vacancy> getVacanciesByCreatedAt(Long employerId) {
        return vacancyRepository.findByEmployerId_IdOrderByCreatedAtDesc(employerId);
    }

    @Override
    public List<Vacancy> getVacanciesByCategory(Category category) {
        return categoryRepository.findByVacancies(category);
    }

    @Transactional
    public Vacancy editVacancy(Long id, VacancyRequest updatedVacancy, Long employerId) {
        Vacancy vacancy = vacancyRepository.findById(id)
                .filter(v -> v.getEmployerId().getId().equals(employerId))
                .orElseThrow(() -> new RuntimeException("Вакансия не найдена или не ваша"));
        
        vacancy.setPosition(updatedVacancy.getPosition());
        vacancy.setEmploymentType(updatedVacancy.getEmploymentType());
        vacancy.setSalary(updatedVacancy.getSalary());
        vacancy.setExperience(updatedVacancy.getExperience());
        vacancy.setCompanyInfo(updatedVacancy.getCompanyInfo());
        vacancy.setCustomPosition(updatedVacancy.getCustomPosition());
        vacancy.setIndustry(updatedVacancy.getIndustry());
        vacancy.setVacancyDescription(updatedVacancy.getVacancyDescription());
        vacancy.setRequiredSkills(updatedVacancy.getRequiredSkills());
        vacancy.setContactInformation(updatedVacancy.getContactInformation());

        return vacancyRepository.save(vacancy);
    }

    @Override
    public Vacancy update(Long id, VacancyRequest vacancyRequest) {
            Vacancy vacancy = vacancyRepository.findById(id)
                    .orElseThrow(() -> new IllegalArgumentException("Vacancy with id " + id + " not found."));

            if (vacancyRequest.getCompanyInfo() != null) {
                vacancy.setCompanyInfo(vacancyRequest.getCompanyInfo());
            }
            if (vacancyRequest.getPosition() != null) {
                vacancy.setPosition(vacancyRequest.getPosition()); // Предполагается, что Position - это Enum
            }
            if (vacancyRequest.getCustomPosition() != null) {
                vacancy.setCustomPosition(vacancyRequest.getCustomPosition());
            }
            if (vacancyRequest.getIndustry() != null) {
                vacancy.setIndustry(vacancyRequest.getIndustry()); // Предполагается, что Industry - это Enum
            }
            if (vacancyRequest.getVacancyDescription() != null) {
                vacancy.setVacancyDescription(vacancyRequest.getVacancyDescription());
            }
            if (vacancyRequest.getRequiredSkills() != null) {
                vacancy.setRequiredSkills(vacancyRequest.getRequiredSkills());
            }
            if (vacancyRequest.getEmploymentType() != null) {
                vacancy.setEmploymentType(vacancyRequest.getEmploymentType()); // Enum конвертируется в строку
            }
            if (vacancyRequest.getExperience() != null) {
                vacancy.setExperience(vacancyRequest.getExperience()); // Аналогично для Enum
            }
            if (vacancyRequest.getAdditionalInfo() != null) {
                vacancy.setAdditionalInfo(vacancyRequest.getAdditionalInfo());
            }

            // Обновление связанных сущностей
            if (vacancyRequest.getSalary() != null) {
                Salary salary = vacancyRequest.getSalary(); // Зарплата как отдельная сущность
                vacancy.setSalary(salary);
            }

            if (vacancyRequest.getContactInformation() != null) {
                ContactInformation contactInformation = vacancyRequest.getContactInformation(); // Контакты
                vacancyRequest.setContactInformation(contactInformation);
            }

            return vacancyRepository.save(vacancy);
        }


    @Override
    public List<ApplicantVacanciesResponses> searchVacancy(String search) {
        List<Vacancy> vacancies;
        String s;
        if (search == null) {
            vacancies = vacancyRepository.findAll();
        } else {
            vacancies = vacancyRepository.search(search.toLowerCase(Locale.ROOT));
            for (Vacancy vacancy : vacancies) {
                if (vacancy.getSearchCounter() == Long.MAX_VALUE) {
                    vacancy.setSearchCounter(vacancy.getSearchCounter() - 1);
                    System.out.println("Long has reached the maximum value");
                }
                vacancy.setSearchCounter(vacancy.getSearchCounter() + 1);
                vacancyRepository.save(vacancy);
            }
        }
        return applicantMapper.convertToVacancyApplicantResponses(vacancies);


    }

    @Override
    public VacancyResponse aboutVacancy(Long id) {
        Vacancy vacancy= vacancyRepository.findById(id).orElseThrow(()->
                new NotFoundException("The vacancy not found with id: " + id, HttpStatus.NOT_FOUND));
        return vacancyMapper.toDto(vacancy);
    }


    @Transactional
    public void deleteVacancy(Long id, Long employerId) {
        Vacancy vacancy = vacancyRepository.findById(id)
                .filter(v -> v.getEmployerId().getId().equals(employerId))
                .orElseThrow(() -> new RuntimeException("Вакансия не найдена или не ваша"));
        vacancyRepository.delete(vacancy);
    }
    @Override
    public boolean delete(Long id){
        Vacancy vacancy= vacancyRepository.findById(id).orElseThrow(() -> new NotFoundException("Вакансия не найдена" +id , HttpStatus.NOT_FOUND));
        vacancyRepository.delete(vacancy);
        return true;
    }


    @Transactional
    public void archiveVacancy(Long id, Long employerId) {
        Vacancy vacancy = vacancyRepository.findById(id)
                .filter(v -> v.getEmployerId().getId().equals(employerId))
                .orElseThrow(() -> new RuntimeException("Вакансия не найдена или не ваша"));
        vacancy.setStatus(Status.ARCHIVE);
        vacancyRepository.save(vacancy);
    }

    @Transactional
    public void openVacancy(Long id, Long employerId) {
        Vacancy vacancy = vacancyRepository.findById(id)
                .filter(v -> v.getEmployerId().getId().equals(employerId))
                .orElseThrow(() -> new RuntimeException("Вакансия не найдена или не ваша"));
        vacancy.setStatus(Status.OPEN);
        vacancyRepository.save(vacancy);
    }

    @Transactional
    public void closeVacancy(Long id, Long employerId) {
        Vacancy vacancy = vacancyRepository.findById(id)
                .filter(v -> v.getEmployerId().getId().equals(employerId))
                .orElseThrow(() -> new RuntimeException("Вакансия не найдена или не ваша"));
        vacancy.setStatus(Status.CLOSED);
        vacancyRepository.save(vacancy);
    }

}


