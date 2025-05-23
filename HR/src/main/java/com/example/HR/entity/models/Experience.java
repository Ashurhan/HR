package com.example.HR.entity.models;


import com.example.HR.entity.enums.Month;
import com.example.HR.entity.enums.Position;
import jakarta.persistence.*;


@Entity
@Table(name = "experiences")
public class Experience {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "position")
    private Position position; // Например, WEB_DEVELOPER

    @Column(name = "custom_position")
    private String customPosition; // Для OTHER

    @Column(name = "company")
    private String company; // Например, "Fortylines IO"

    @Enumerated(EnumType.STRING)
    @Column(name = "start_month")
    private Month startMonth; // Например, MAY

    @Column(name = "start_year")
    private Integer startYear; // Например, 2020

    @Enumerated(EnumType.STRING)
    @Column(name = "end_month")
    private Month endMonth; // Например, MAY (если не "по настоящее время")

    @Column(name = "end_year")
    private Integer endYear; // Например, 2023 (если не "по настоящее время")

    @Column(name = "is_current")
    private Boolean isCurrent; // true, если "По настоящее время"

    @Column(name = "skills", columnDefinition = "TEXT")
    private String skills; // Например, "Исследование пользователя; Прототипирование"

    @ManyToOne
    @JoinColumn(name = "applicant_id", nullable = false)
    private Applicant applicant;

    // Геттеры и сеттеры
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Position getPosition() { return position; }
    public void setPosition(Position position) { this.position = position; }

    public String getCustomPosition() { return customPosition; }
    public void setCustomPosition(String customPosition) { this.customPosition = customPosition; }

    public String getCompany() { return company; }
    public void setCompany(String company) { this.company = company; }

    public Month getStartMonth() { return startMonth; }
    public void setStartMonth(Month startMonth) { this.startMonth = startMonth; }

    public Integer getStartYear() { return startYear; }
    public void setStartYear(Integer startYear) { this.startYear = startYear; }

    public Month getEndMonth() { return endMonth; }
    public void setEndMonth(Month endMonth) { this.endMonth = endMonth; }

    public Integer getEndYear() { return endYear; }
    public void setEndYear(Integer endYear) { this.endYear = endYear; }

    public Boolean getIsCurrent() { return isCurrent; }
    public void setIsCurrent(Boolean isCurrent) { this.isCurrent = isCurrent; }

    public String getSkills() { return skills; }
    public void setSkills(String skills) { this.skills = skills; }

    public Applicant getApplicant() { return applicant; }
    public void setApplicant(Applicant applicant) { this.applicant = applicant; }
}