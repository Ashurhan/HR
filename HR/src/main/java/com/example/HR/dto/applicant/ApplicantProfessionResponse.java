package com.example.HR.dto.applicant;

import com.example.HR.entity.models.FileData;

import java.util.List;

public class ApplicantProfessionResponse {
    private Long id;
    private String about;
    private List<EducationRequest> educations;
    private List<ExperienceRequest> experiences;
    private FileData resume;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAbout() {
        return about;
    }

    public void setAbout(String about) {
        this.about = about;
    }

    public List<EducationRequest> getEducations() {
        return educations;
    }

    public void setEducations(List<EducationRequest> educations) {
        this.educations = educations;
    }

    public List<ExperienceRequest> getExperiences() {
        return experiences;
    }

    public void setExperiences(List<ExperienceRequest> experiences) {
        this.experiences = experiences;
    }

    public FileData resume() {
        return resume;
    }

    public void setResume(FileData resume) {
        this.resume = resume;
    }
}
