package com.example.HR.entity.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Table(name = "list_support_table")
@Entity
public class ListSupport {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String personName;
    private String personEmail;
    private Integer personPhoneNumber;

    @Column(columnDefinition = "TEXT")
    private String message;
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

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getDateSent() {
        return dateSent;
    }

    public void setDateSent(String dateSent) {
        this.dateSent = dateSent;
    }
}