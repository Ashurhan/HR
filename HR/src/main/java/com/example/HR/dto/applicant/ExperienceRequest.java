package com.example.HR.dto.applicant;

import com.example.HR.entity.enums.Position;
import com.example.HR.entity.enums.Month;

public class ExperienceRequest {

    private Position position;
    private String customPosition;
    private String company;
    private Month startMonth;
    private Integer startYear;
    private Month endMonth;
    private Integer endYear;
    private Boolean isCurrent;
    private String skills;

    // Геттеры и сеттеры
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

}
