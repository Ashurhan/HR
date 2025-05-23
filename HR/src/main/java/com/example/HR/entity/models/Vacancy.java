package com.example.HR.entity.models;

import com.example.HR.entity.enums.*;
import com.example.HR.entity.enums.Experience;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
@Entity
@Table(name = "vacancies")
public class Vacancy {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Поле 'О компании' обязательно")
    @Column(columnDefinition = "TEXT")
    private String companyInfo;

    @NotNull(message = "Поле 'Позиция' обязательно")
    @Enumerated(EnumType.STRING)
    private Position position;

    private String customPosition;
    private Long searchCounter;

    @NotNull(message = "Поле Dreams of the industry' обязательно")
    @Enumerated(EnumType.STRING)
    private Industry industry;

    @Column(columnDefinition = "TEXT")
    private String vacancyDescription;

    @NotBlank(message = "Поле 'Требуемые навыки' обязательно")
    @Column(columnDefinition = "TEXT")
    private String requiredSkills;

    @OneToOne(cascade = CascadeType.ALL)
    private Salary salary;

    @NotNull(message = "Поле 'Вид занятости' обязательно")
    @Enumerated(EnumType.STRING)
    private EmploymentType employmentType;

    @OneToOne
    @JoinColumn(name = "contact_information_id")
    private ContactInformation contactInformation;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "employer_id", nullable = false)
    @NotNull(message = "Поле 'Работодатель' обязательно")
    private Employer employerId;

    @NotNull(message = "Поле 'Опыт работы/стаж' обязательно")
    @Enumerated(EnumType.STRING)
    private Experience experience;

    private String additionalInfo;

    @NotNull(message = "Поле 'Статус' обязательно")
    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private Status status = Status.OPEN;

    @OneToMany(mappedBy = "vacancy", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private List<Response> responseList = new ArrayList<>();

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
    }

    public int getResponses() {
        return responseList.size();
    }

    public void addResponse(Response response) {
        if (response != null) {
            this.responseList.add(response);
        }
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public @NotBlank(message = "Поле 'О компании' обязательно") String getCompanyInfo() {
        return companyInfo;
    }

    public void setCompanyInfo(@NotBlank(message = "Поле 'О компании' обязательно") String companyInfo) {
        this.companyInfo = companyInfo;
    }

    public @NotNull(message = "Поле 'Позиция' обязательно") Position getPosition() {
        return position;
    }

    public void setPosition(@NotNull(message = "Поле 'Позиция' обязательно") Position position) {
        this.position = position;
    }

    public String getCustomPosition() {
        return customPosition;
    }

    public void setCustomPosition(String customPosition) {
        this.customPosition = customPosition;
    }

    public @NotNull(message = "Поле Dreams of the industry' обязательно") Industry getIndustry() {
        return industry;
    }

    public void setIndustry(@NotNull(message = "Поле Dreams of the industry' обязательно") Industry industry) {
        this.industry = industry;
    }

    public Long getSearchCounter() {
        return searchCounter;
    }

    public void setSearchCounter(Long searchCounter) {
        this.searchCounter = searchCounter;
    }

    public String getVacancyDescription() {
        return vacancyDescription;
    }

    public void setVacancyDescription(String vacancyDescription) {
        this.vacancyDescription = vacancyDescription;
    }

    public @NotBlank(message = "Поле 'Требуемые навыки' обязательно") String getRequiredSkills() {
        return requiredSkills;
    }

    public void setRequiredSkills(@NotBlank(message = "Поле 'Требуемые навыки' обязательно") String requiredSkills) {
        this.requiredSkills = requiredSkills;
    }

    public Salary getSalary() {
        return salary;
    }

    public void setSalary(Salary salary) {
        this.salary = salary;
    }

    public @NotNull(message = "Поле 'Вид занятости' обязательно") EmploymentType getEmploymentType() {
        return employmentType;
    }

    public void setEmploymentType(@NotNull(message = "Поле 'Вид занятости' обязательно") EmploymentType employmentType) {
        this.employmentType = employmentType;
    }

    public ContactInformation getContactInformation() {
        return contactInformation;
    }

    public void setContactInformation(ContactInformation contactInformation) {
        this.contactInformation = contactInformation;
    }

    public @NotNull(message = "Поле 'Работодатель' обязательно") Employer getEmployerId() {
        return employerId;
    }

    public void setEmployerId(@NotNull(message = "Поле 'Работодатель' обязательно") Employer employerId) {
        this.employerId = employerId;
    }

    public @NotNull(message = "Поле 'Опыт работы/стаж' обязательно") Experience getExperience() {
        return experience;
    }

    public void setExperience(@NotNull(message = "Поле 'Опыт работы/стаж' обязательно") Experience experience) {
        this.experience = experience;
    }

    public String getAdditionalInfo() {
        return additionalInfo;
    }

    public void setAdditionalInfo(String additionalInfo) {
        this.additionalInfo = additionalInfo;
    }

    public @NotNull(message = "Поле 'Статус' обязательно") Status getStatus() {
        return status;
    }

    public void setStatus(@NotNull(message = "Поле 'Статус' обязательно") Status status) {
        this.status = status;
    }

    public List<Response> getResponseList() {
        return responseList;
    }

    public void setResponseList(List<Response> responseList) {
        this.responseList = responseList;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}