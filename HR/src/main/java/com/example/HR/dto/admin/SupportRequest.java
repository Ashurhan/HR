package com.example.HR.dto.admin;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
public class SupportRequest {
    private String personName;
    private String personEmail;
    private Integer personPhoneNumber;
    private String massage;

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

    public void setMessage(String massage) {
        this.massage = massage;
    }
}
