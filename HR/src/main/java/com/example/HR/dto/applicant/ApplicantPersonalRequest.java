package com.example.HR.dto.applicant;

import com.example.HR.entity.models.ContactInformation;
import com.example.HR.entity.models.FileData;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;


public class ApplicantPersonalRequest {
    private String firstName;
    private String lastName;
    private LocalDate dateOfBirth;
    private ContactInformation contactInformation;
    private String email;
    private MultipartFile image;


    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public ContactInformation getContactInformation() {
        return contactInformation;
    }
    public void setContactInformation(ContactInformation contactInformation) {
        this.contactInformation = contactInformation;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public MultipartFile getImage() {
        return image;
    }

    public void setImage(MultipartFile image) {
        this.image = image;
    }
}
