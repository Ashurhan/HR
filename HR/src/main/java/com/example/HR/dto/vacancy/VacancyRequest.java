package com.example.HR.dto.vacancy;



import com.example.HR.entity.enums.*;
import com.example.HR.entity.enums.Experience;

import com.example.HR.entity.models.ContactInformation;
import com.example.HR.entity.models.Salary;
import jakarta.validation.constraints.*;

public class VacancyRequest{

    @NotBlank(message = "Поле 'О компании' обязательно")
    private String companyInfo;

    @NotNull(message = "Поле 'Позиция' обязательно")
    private Position position;

    private String customPosition; // Для ручного ввода позиции

    @NotNull(message = "Поле 'Отрасль' обязательно")
    private Industry industry;

    @NotNull(message = "Обязательно")
    private String vacancyDescription;

    @NotBlank(message = "Поле 'Требуемые навыки' обязательно")
    private String requiredSkills;

    @NotNull
    @Min(value = 0)
    private Salary salary;

    @NotNull(message = "Поле 'Вид занятости' обязательно")
    private EmploymentType employmentType;

    @NotNull(message = "Поле 'Опыт работы/стаж' обязательно")
    private Experience experience;
    @NotNull
    private ContactInformation contactInformation;
    @NotNull
    private String additionalInfo;

    // Геттеры и сеттеры
    public String getCompanyInfo() {
        return companyInfo;
    }

    public void setCompanyInfo(String companyInfo) {
        this.companyInfo = companyInfo;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public String getCustomPosition() {
        return customPosition;
    }

    public void setCustomPosition(String customPosition) {
        this.customPosition = customPosition;
    }

    public Industry getIndustry() {
        return industry;
    }

    public void setIndustry(Industry industry) {
        this.industry = industry;
    }

    public String getVacancyDescription() {
        return vacancyDescription;
    }

    public void setVacancyDescription(String vacancyDescription) {
        this.vacancyDescription = vacancyDescription;
    }

    public String getRequiredSkills() {
        return requiredSkills;
    }

    public void setRequiredSkills(String requiredSkills) {
        this.requiredSkills = requiredSkills;
    }

    public Salary getSalary() {
        return salary;
    }

    public void setSalary(Salary salary) {
        this.salary = salary;
    }

    public EmploymentType getEmploymentType() {
        return employmentType;
    }

    public void setEmploymentType(EmploymentType employmentType) {
        this.employmentType = employmentType;
    }

    public Experience getExperience() {
        return experience;
    }

    public void setExperience(Experience experience) {
        this.experience = experience;
    }

    public ContactInformation getContactInformation() {
        return contactInformation;
    }

    public void setContactInformation(ContactInformation contactInfo) {
        this.contactInformation = contactInfo;
    }

    public String getAdditionalInfo() {
        return additionalInfo;
    }

    public void setAdditionalInfo(String additionalInfo) {
        this.additionalInfo = additionalInfo;
    }

    // Кастомная валидация для customPosition
    @AssertTrue(message = "Поле 'customPosition' обязательно, если выбрано 'Нужная позиция отсутствует в списке'")
    public boolean isCustomPositionValid() {
        if (position == Position.NOT_IN_LIST) {
            return customPosition != null && !customPosition.trim().isEmpty();
        }
        return true;
    }
}