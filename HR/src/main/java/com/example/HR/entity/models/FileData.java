package com.example.HR.entity.models;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class FileData {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String type;
    private String filePath;
    private Long size;

    @OneToOne(mappedBy = "image")
    private Applicant applicantImage;

    @OneToOne(mappedBy = "resume")
    private Applicant applicantResume;

    @OneToOne(mappedBy = "profileImage")
    private Employer employerProfileImage;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public Long getSize() {
        return size;
    }

    public void setSize(Long size) {
        this.size = size;
    }

    public Applicant getApplicantImage() {
        return applicantImage;
    }

    public void setApplicantImage(Applicant applicantImage) {
        this.applicantImage = applicantImage;
    }

    public Applicant getApplicantResume() {
        return applicantResume;
    }

    public void setApplicantResume(Applicant applicantResume) {
        this.applicantResume = applicantResume;
    }

    public Employer getEmployerProfileImage() {
        return employerProfileImage;
    }

    public void setEmployerProfileImage(Employer employerProfileImage) {
        this.employerProfileImage = employerProfileImage;
    }
}
