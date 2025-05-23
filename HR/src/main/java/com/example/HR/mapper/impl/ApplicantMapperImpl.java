package com.example.HR.mapper.impl;

import com.example.HR.dto.applicant.*;
import com.example.HR.dto.image.ImageResponse;
import com.example.HR.entity.models.Applicant;
import com.example.HR.entity.models.Education;
import com.example.HR.entity.models.Vacancy;
import com.example.HR.mapper.ApplicantMapper;
import com.example.HR.entity.models.Experience;
import com.example.HR.mapper.ApplicantVacanciesResponsesMapper;
import com.example.HR.mapper.VacancyMapper;
import org.springframework.stereotype.Component;
import org.springframework.transaction.support.AbstractPlatformTransactionManager;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class ApplicantMapperImpl implements ApplicantMapper {

    private VacancyMapper vacancyMapper;

    public ApplicantMapperImpl(VacancyMapper vacancyMapper) {
        this.vacancyMapper = vacancyMapper;
    }

    @Override
    public Applicant toEntityFromPersonal(ApplicantPersonalRequest request) {
        Applicant applicant = new Applicant();
        applicant.setFirstName(request.getFirstName());
        applicant.setLastName(request.getLastName());
        applicant.setEmail(request.getEmail());
        applicant.setBirthDate(request.getDateOfBirth());
        applicant.getContactInformation().setCountry(request.getContactInformation().getCountry());
        applicant.getContactInformation().setCity(request.getContactInformation().getCity());
        applicant.getContactInformation().setAddress(request.getContactInformation().getAddress());
        applicant.getContactInformation().setPhone(request.getContactInformation().getPhone());

        return applicant;
    }
    @Override
    public void updateEntityFromPersonal(Applicant applicant, ApplicantPersonalRequest request) {
        if(request.getFirstName() != null) applicant.setFirstName(request.getFirstName());
        if(request.getLastName() != null) applicant.setLastName(request.getLastName());
        if(request.getEmail() != null) applicant.setEmail(request.getEmail());
        if(request.getDateOfBirth() != null) applicant.setBirthDate(request.getDateOfBirth());
        if(request.getContactInformation().getCountry() != null) applicant.getContactInformation().setCountry(request.getContactInformation().getCountry());
        if(request.getContactInformation().getCity() != null) applicant.getContactInformation().setCity(request.getContactInformation().getCity());
        if(request.getContactInformation().getAddress() != null) applicant.getContactInformation().setAddress(request.getContactInformation().getAddress());
        if(request.getContactInformation().getPhone() != null) applicant.getContactInformation().setPhone(request.getContactInformation().getPhone());
//        if(imageFileData != null) applicant.setImage(imageFileData);
    }

    @Override
    public void updateEntityFromProfession(Applicant applicant, ApplicantProfessionRequest request) {
        if (request.getAbout() != null) {
            applicant.setAbout(request.getAbout());
        }
        if (request.getEducations() != null) {
            List<Education> educations = request.getEducations().stream()
                    .map(this::toEntityEducation)
                    .collect(Collectors.toList());
            applicant.setEducations(educations);
        }
        if (request.getExperiences() != null) {
            List<Experience> experiences = request.getExperiences().stream()
                    .map(this::toEntityExperience)
                    .collect(Collectors.toList());
            applicant.setExperiences(experiences);
        }
    }
    

    @Override
    public ExperienceResponse toDtoExperience(Experience experience) {
        ExperienceResponse response = new ExperienceResponse();
        response.setId(experience.getId());
        response.setPosition(experience.getPosition());
        response.setCustomPosition(experience.getCustomPosition());
        response.setCompany(experience.getCompany());
        response.setStartMonth(experience.getStartMonth());
        response.setStartYear(experience.getStartYear());
        response.setEndMonth(experience.getEndMonth());
        response.setEndYear(experience.getEndYear());
        response.setIsCurrent(experience.getIsCurrent());
        response.setSkills(experience.getSkills());
        return response;
    }

    @Override
    public Experience toEntityExperience(ExperienceRequest request) {
        Experience experience = new Experience();
        experience.setPosition(request.getPosition());
        experience.setCustomPosition(request.getCustomPosition());
        experience.setCompany(request.getCompany());
        experience.setStartMonth(request.getStartMonth());
        experience.setStartYear(request.getStartYear());
        experience.setEndMonth(request.getEndMonth());
        experience.setEndYear(request.getEndYear());
        experience.setIsCurrent(request.getIsCurrent());
        experience.setSkills(request.getSkills());
        return experience;
    }

    @Override
    public EducationResponce toDtoEducation(Education education) {
        EducationResponce response = new EducationResponce();
        response.setId(education.getId());
        response.setDegree(education.getDegree());
        response.setInstitution(education.getInstitution());
        response.setEndMonth(education.getEndMonth());
        response.setEndYear(education.getEndYear());
        response.setIsCurrent(education.getIsCurrent());
        return response;
    }

    @Override
    public Education toEntityEducation(EducationRequest request) {
        Education education = new Education();
        education.setDegree(request.getDegree());
        education.setInstitution(request.getInstitution());
        education.setEndMonth(request.getEndMonth());
        education.setEndYear(request.getEndYear());
        education.setIsCurrent(request.getIsCurrent());
        return education;
    }

    @Override
    public ApplicantPersonalResponse toPersonalResponse(Applicant applicant) {
        ApplicantPersonalResponse response = new ApplicantPersonalResponse();
        response.setId(applicant.getId());
        response.setFirstName(applicant.getFirstName());
        response.setLastName(applicant.getLastName());
        response.setDateOfBirth(applicant.getBirthDate());
        response.getContactInformation().setCountry(applicant.getContactInformation().getCountry());
        response.getContactInformation().setCity(applicant.getContactInformation().getCity());
        response.getContactInformation().setAddress(applicant.getContactInformation().getAddress());
        response.getContactInformation().setPhone(applicant.getContactInformation().getPhone());
        response.setEmail(applicant.getEmail());
        response.setImage(applicant.getImage());
        
        if (applicant.getImage() != null) {
            ImageResponse imageResponse = new ImageResponse();
            imageResponse.setId(applicant.getImage().getId());
            imageResponse.setName(applicant.getImage().getName());
            imageResponse.setType(applicant.getImage().getType());
            imageResponse.setPath(applicant.getImage().getFilePath());
            imageResponse.setOwnerId(applicant.getId());
            imageResponse.setOwnerType("APPLICANT");
            response.setImageResponse(imageResponse);
        }
        
        return response;
    }

    @Override
    public ApplicantProfessionResponse toProfessionResponse(Applicant applicant) {
        ApplicantProfessionResponse response = new ApplicantProfessionResponse();
        response.setId(applicant.getId());
        response.setAbout(applicant.getAbout());
        if (applicant.getEducations() != null) {
            response.setEducations(applicant.getEducations().stream()
                    .map(this::toEntityEducationRequest)
                    .collect(Collectors.toList()));
        }
        if (applicant.getExperiences() != null) {
            response.setExperiences(applicant.getExperiences().stream()
                    .map(this::toEntityExperienceRequest)
                    .collect(Collectors.toList()));
        }
        response.setResume(applicant.getResume());
        return response;
    }

    // Вспомогательные методы для преобразования сущностей в Request-DTO (для возврата на фронт)
    private EducationRequest toEntityEducationRequest(Education education) {
        EducationRequest req = new EducationRequest();
        req.setDegree(education.getDegree());
        req.setInstitution(education.getInstitution());
        req.setEndMonth(education.getEndMonth());
        req.setEndYear(education.getEndYear());
        req.setIsCurrent(education.getIsCurrent());
        return req;
    }

    private ExperienceRequest toEntityExperienceRequest(Experience experience) {
        ExperienceRequest req = new ExperienceRequest();
        req.setPosition(experience.getPosition());
        req.setCustomPosition(experience.getCustomPosition());
        req.setCompany(experience.getCompany());
        req.setStartMonth(experience.getStartMonth());
        req.setStartYear(experience.getStartYear());
        req.setEndMonth(experience.getEndMonth());
        req.setEndYear(experience.getEndYear());
        req.setIsCurrent(experience.getIsCurrent());
        req.setSkills(experience.getSkills());
        return req;
    }

    @Override
    public Applicant toEntityFromProfession(ApplicantProfessionRequest request) {
        Applicant applicant = new Applicant();
        applicant.setAbout(request.getAbout());
        if (request.getEducations() != null) {
            List<Education> educations = request.getEducations().stream()
                    .map(this::toEntityEducation)
                    .collect(Collectors.toList());
            applicant.setEducations(educations);
        }
        if (request.getExperiences() != null) {
            List<Experience> experiences = request.getExperiences().stream()
                    .map(this::toEntityExperience)
                    .collect(Collectors.toList());
            applicant.setExperiences(experiences);
        }
        return applicant;
    }
    @Override
    public ApplicantResponse toResponse(Applicant applicant) {
        ApplicantResponse response = new ApplicantResponse();
        response.setId(applicant.getId());
        response.setFirstName(applicant.getFirstName());
        response.setLastName(applicant.getLastName());
        response.setEmail(applicant.getEmail());
        response.setBirthDate(applicant.getBirthDate());
        response.getContactInformation().setCountry(applicant.getContactInformation().getCountry());
        response.getContactInformation().setCity(applicant.getContactInformation().getCity());
        response.getContactInformation().setAddress(applicant.getContactInformation().getAddress());
        response.getContactInformation().setPhone(applicant.getContactInformation().getPhone());
        response.setAbout(applicant.getAbout());
        response.setPosition(applicant.getPosition());
        response.setCustomPosition(applicant.getCustomPosition());
        response.setSkills(applicant.getSkills());
        response.setResume(applicant.getResume());
        response.setImage(applicant.getImage());
        response.setRole(applicant.getRole());
        if (applicant.getExperiences() != null) {
            response.setExperiences(
                    applicant.getExperiences().stream()
                            .map(this::toDtoExperience)
                            .collect(Collectors.toList())
            );
        }
        if (applicant.getEducations() != null) {
            response.setEducations(
                    applicant.getEducations().stream()
                            .map(this::toDtoEducation)
                            .collect(Collectors.toList())
            );
        }
        return response;
    }

    @Override
    public ApplicantVacanciesResponses convertToVacancyApplicantResponse(Vacancy vacancy) {
        if (vacancy == null) {
            return null;
        }
        ApplicantVacanciesResponses vacanciesResponses = new ApplicantVacanciesResponses();
        vacanciesResponses.setId(vacancy.getId());
        vacanciesResponses.setOwnerName(vacancy.getCompanyInfo());
        vacanciesResponses.setVacancyResponse(vacancyMapper.toDto(vacancy));
        return vacanciesResponses;    }

    @Override
    public List<ApplicantVacanciesResponses> convertToVacancyApplicantResponses(List<Vacancy> vacancies) {
        List<ApplicantVacanciesResponses> ApplicantVacanciesResponses = new ArrayList<>();
        for (Vacancy vacancy : vacancies) {
            ApplicantVacanciesResponses.add(convertToVacancyApplicantResponse(vacancy));
        }
        return ApplicantVacanciesResponses;
    }





}


