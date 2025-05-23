package com.example.HR.mapper.impl;

import com.example.HR.dto.salary.SalaryResponse;
import com.example.HR.dto.vacancy.VacancyRequest;
import com.example.HR.dto.vacancy.VacancyResponse;
import com.example.HR.entity.models.ContactInformation;
import com.example.HR.entity.models.Salary;
import com.example.HR.entity.models.Vacancy;
import com.example.HR.mapper.VacancyMapper;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class VacancyMapperImpl implements VacancyMapper {
    public List<VacancyResponse> toDtoS(List<Vacancy> all) {
        List<VacancyResponse> dtos = new ArrayList<VacancyResponse>();
        for (Vacancy vacancy : all) {
            dtos.add(toDto(vacancy));
        }
        return dtos;
    }

    public VacancyResponse toDto(Vacancy vacancy) {
        VacancyResponse dto = new VacancyResponse();
        dto.setId(vacancy.getId());
        dto.setCompanyInfo(vacancy.getCompanyInfo());
        dto.setPosition(vacancy.getPosition());
        dto.setCustomPosition(vacancy.getCustomPosition());
        dto.setIndustry(vacancy.getIndustry());
        dto.setVacancyDescription(vacancy.getVacancyDescription());
        dto.setRequiredSkills(vacancy.getRequiredSkills());
        dto.setSalary(vacancy.getSalary());
        dto.setEmploymentType(vacancy.getEmploymentType());
        dto.setExperience(vacancy.getExperience());
        dto.setContactInformation(vacancy.getContactInformation());
        dto.setAdditionalInfo(vacancy.getAdditionalInfo());
        dto.setEmployerId(vacancy.getEmployerId().getId());
        return dto;
    }

    @Override
    public VacancyResponse requestToResponse(VacancyRequest request) {
        VacancyResponse response= new VacancyResponse();
        response.setCompanyInfo(request.getCompanyInfo());
        response.setPosition(request.getPosition());
        response.setCustomPosition(request.getCustomPosition());
        response.setIndustry(request.getIndustry());
        response.setVacancyDescription(request.getVacancyDescription());
        response.setRequiredSkills(request.getRequiredSkills());

        Salary requestSalary= request.getSalary();

        if(requestSalary!=null){
            Salary salaryResponse= new Salary();
            salaryResponse.setSalaryType(requestSalary.getSalaryType());
            salaryResponse.setSalarySum(requestSalary.getSalarySum());
            salaryResponse.setCurrency(requestSalary.getCurrency());
            response.setSalary(salaryResponse);

        }else {
            throw new IllegalArgumentException("Зарплата обязательно");
        }

        response.setEmploymentType(request.getEmploymentType());
        response.setExperience(request.getExperience());

        ContactInformation contactInformationRequest= request.getContactInformation();
        if(contactInformationRequest!=null){
            ContactInformation contactResponse= new ContactInformation();
            contactResponse.setCountry((contactInformationRequest.getCountry()));
            contactResponse.setCity(contactInformationRequest.getCity());
            contactResponse.setAddress(contactInformationRequest.getAddress());
            contactResponse.setPhone(contactInformationRequest.getPhone());
            response.setContactInformation(contactResponse);
        }else{
            throw new IllegalArgumentException("Обязательно");
        }
        response.setAdditionalInfo(request.getAdditionalInfo());
        return response;
    }



    @Override
    public Vacancy toEntity(VacancyRequest request) {
        Vacancy vacancy = new Vacancy();
        vacancy.setCompanyInfo(request.getCompanyInfo());
        vacancy.setPosition(request.getPosition());
        vacancy.setCustomPosition(request.getCustomPosition());
        vacancy.setIndustry(request.getIndustry());
        vacancy.setVacancyDescription(request.getVacancyDescription());
        vacancy.setRequiredSkills(request.getRequiredSkills());
        vacancy.setSalary(request.getSalary());
        vacancy.setEmploymentType(request.getEmploymentType());
        vacancy.setExperience(request.getExperience());
        vacancy.setContactInformation(request.getContactInformation());
        vacancy.setAdditionalInfo(request.getAdditionalInfo());
        return vacancy;
    }
}


