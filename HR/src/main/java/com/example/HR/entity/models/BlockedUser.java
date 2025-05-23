package com.example.HR.entity.models;

import jakarta.persistence.*;

@Entity
public class BlockedUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Boolean viewingCompanyData;
    private Boolean viewingAndSearchingForVacancies;
    private Boolean viewTheStatusOfResponded;
    private Boolean communicationWithEmployers;
    private Boolean viewingCandidateData;
    private Boolean vacancyAndHiringManagement;
    private Boolean communicationWithJobSeekers;
    @OneToOne(mappedBy = "blockedUser")
    private User user;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Boolean getViewingCompanyData() {
        return viewingCompanyData;
    }

    public void setViewingCompanyData(Boolean viewingCompanyData) {
        this.viewingCompanyData = viewingCompanyData;
    }

    public Boolean getViewingAndSearchingForVacancies() {
        return viewingAndSearchingForVacancies;
    }

    public void setViewingAndSearchingForVacancies(Boolean viewingAndSearchingForVacancies) {
        this.viewingAndSearchingForVacancies = viewingAndSearchingForVacancies;
    }

    public Boolean getViewTheStatusOfResponded() {
        return viewTheStatusOfResponded;
    }

    public void setViewTheStatusOfResponded(Boolean viewTheStatusOfResponded) {
        this.viewTheStatusOfResponded = viewTheStatusOfResponded;
    }

    public Boolean getCommunicationWithEmployers() {
        return communicationWithEmployers;
    }

    public void setCommunicationWithEmployers(Boolean communicationWithEmployers) {
        this.communicationWithEmployers = communicationWithEmployers;
    }

    public Boolean getViewingCandidateData() {
        return viewingCandidateData;
    }

    public void setViewingCandidateData(Boolean viewingCandidateData) {
        this.viewingCandidateData = viewingCandidateData;
    }

    public Boolean getVacancyAndHiringManagement() {
        return vacancyAndHiringManagement;
    }

    public void setVacancyAndHiringManagement(Boolean vacancyAndHiringManagement) {
        this.vacancyAndHiringManagement = vacancyAndHiringManagement;
    }

    public Boolean getCommunicationWithJobSeekers() {
        return communicationWithJobSeekers;
    }

    public void setCommunicationWithJobSeekers(Boolean communicationWithJobSeekers) {
        this.communicationWithJobSeekers = communicationWithJobSeekers;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
