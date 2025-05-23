package com.example.HR.dto.vacancy;


import com.example.HR.entity.enums.Currency;
import com.example.HR.entity.enums.EmploymentType;
import com.example.HR.entity.enums.Industry;
import com.example.HR.entity.enums.Position;
import com.example.HR.entity.enums.Experience;
import com.example.HR.entity.models.ContactInformation;
import com.example.HR.entity.models.Salary;


public class VacancyResponse {

    private Long id;

    private String companyInfo;

    private Position position;

    private String customPosition;

    private Industry industry;

    private String vacancyDescription;

    private String requiredSkills;

    private Salary salary;


    private EmploymentType employmentType;

    private Experience experience;

    private ContactInformation contactInformation;

//    private ContactInformation contactInfo; // Код страны (например, "KG")
//
//    private String contactInfoName; // Полное название страны (например, "Kyrgyzstan")

    private String additionalInfo;
    private Long employerId;

    // Геттеры и сеттеры
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getEmployerId() {
        return employerId;
    }

    public void setEmployerId(Long employerId) {
        this.employerId = employerId;
    }

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

    public void setContactInformation(ContactInformation contactInformation) {
        this.contactInformation = contactInformation;
    }

    public String getAdditionalInfo() {
        return additionalInfo;
    }

    public void setAdditionalInfo(String additionalInfo) {
        this.additionalInfo = additionalInfo;
    }
}
