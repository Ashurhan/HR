package com.example.HR.dto.applicant;

import com.example.HR.dto.image.ImageResponse;
import com.example.HR.entity.models.City;
import com.example.HR.entity.models.ContactInformation;
import com.example.HR.entity.models.Country;
import com.example.HR.entity.models.FileData;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;

public class ApplicantPersonalResponse {
    private Long id;
    private ImageResponse imageResponse;
    private String firstName;
    private String lastName;
    private LocalDate dateOfBirth;
    private ContactInformation contactInformation;
    private String email;
    private FileData photo;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ImageResponse getImageResponse() {
        return imageResponse;
    }

    public void setImageResponse(ImageResponse imageResponse) {
        this.imageResponse = imageResponse;
    }

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

    public FileData getImage() {
        return photo;
    }

    public void setImage(FileData photo) {
        this.photo = photo ;
    }
}
