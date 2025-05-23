package com.example.HR.dto.admin;


import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
public class ResponsesForSupport {
    private Long id;
    private String personName;
    private String personEmail;
    private Integer personPhoneNumber;
    private String massage;
    private String dateSent;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPersonName() {
        return personName;
    }

    public void setPersonName(String personName) {
        this.personName = personName;
    }

    public String getPersonEmail() {
        return personEmail;
    }

    public void setPersonEmail(String personEmail) {
        this.personEmail = personEmail;
    }

    public Integer getPersonPhoneNumber() {
        return personPhoneNumber;
    }

    public void setPersonPhoneNumber(Integer personPhoneNumber) {
        this.personPhoneNumber = personPhoneNumber;
    }

    public String getMassage() {
        return massage;
    }

    public void setMassage(String massage) {
        this.massage = massage;
    }

    public String getDateSent() {
        return dateSent;
    }

    public void setDateSent(String dateSent) {
        this.dateSent = dateSent;
    }
}