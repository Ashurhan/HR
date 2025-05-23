package com.example.HR.dto.applicant;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public class ApplicantProfessionRequest {
    private String about;
    private List<EducationRequest> educations;
    private List<ExperienceRequest> experiences;
    private MultipartFile resume;

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

    public MultipartFile getResume() {
        return resume;
    }

    public void setResume(MultipartFile resume) {
        this.resume = resume;
    }
}
