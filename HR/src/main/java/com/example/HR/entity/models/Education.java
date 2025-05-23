package com.example.HR.entity.models;

import com.example.HR.entity.enums.EducationLevel;
import com.example.HR.entity.enums.Month;
import jakarta.persistence.*;

@Entity
@Table(name = "educations")
public class Education {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "degree", nullable = false)
    private EducationLevel degree; // Бакалавр, Магистр, Доктор наук

    @Column(name = "institution")
    private String institution; // Например, "Кыргызско-Германский институт"

    @Enumerated(EnumType.STRING)
    @Column(name = "end_month")
    private Month endMonth; // Например, MAY

    @Column(name = "end_year")
    private Integer endYear; // Например, 2020

    @Column(name = "is_current")
    private Boolean isCurrent; // true, если "По настоящее время"

    @ManyToOne
    @JoinColumn(name = "applicant_id", nullable = false)
    private Applicant applicant;

    // Геттеры и сеттеры
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public EducationLevel getDegree() { return degree; }
    public void setDegree(EducationLevel degree) { this.degree = degree; }

    public String getInstitution() { return institution; }
    public void setInstitution(String institution) { this.institution = institution; }

    public Month getEndMonth() { return endMonth; }
    public void setEndMonth(Month endMonth) { this.endMonth = endMonth; }

    public Integer getEndYear() { return endYear; }
    public void setEndYear(Integer endYear) { this.endYear = endYear; }

    public Boolean getIsCurrent() { return isCurrent; }
    public void setIsCurrent(Boolean isCurrent) { this.isCurrent = isCurrent; }

    public Applicant getApplicant() { return applicant; }
    public void setApplicant(Applicant applicant) { this.applicant = applicant; }
}