package com.example.HR.dto.applicant;

import com.example.HR.dto.vacancy.VacancyResponse;
import com.example.HR.entity.models.Vacancy;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
public class ApplicantVacanciesResponses {
    private Long id;
    private String ownerName;
    private VacancyResponse vacancyResponse;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }

    public VacancyResponse getVacancyResponse() {
        return vacancyResponse;
    }

    public void setVacancyResponse(VacancyResponse vacancyResponse) {
        this.vacancyResponse = vacancyResponse;
    }

}
