package com.example.HR.dto.applicant;


import com.example.HR.entity.enums.EducationLevel;
import com.example.HR.entity.enums.Month;

public class EducationResponce {

    private Long id;
    private EducationLevel degree;
    private String institution;
    private Month endMonth;
    private Integer endYear;
    private Boolean isCurrent;

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
}