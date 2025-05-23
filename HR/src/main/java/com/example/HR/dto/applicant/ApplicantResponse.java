package com.example.HR.dto.applicant;

import com.example.HR.dto.image.ImageResponse;
import com.example.HR.entity.enums.Position;
import com.example.HR.entity.enums.UserRole;
import com.example.HR.entity.models.City;
import com.example.HR.entity.models.ContactInformation;
import com.example.HR.entity.models.Country;
import com.example.HR.entity.models.FileData;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
public class ApplicantResponse {
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private LocalDate birthDate;
    private ContactInformation contactInformation;
    private String phone;
    private String about;
    private Position position;
    private String customPosition;
    private String skills;
    private FileData resume;
    private FileData image;
    private UserRole role;
    private List<ExperienceResponse> experiences;
    private List<EducationResponce> educations;
    private ImageResponse imageResponse;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public ContactInformation getContactInformation() {
        return contactInformation;
    }

    public void setContactInformation(ContactInformation contactInformation) {
        this.contactInformation = contactInformation;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAbout() {
        return about;
    }

    public void setAbout(String about) {
        this.about = about;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public String getCustomPosition() {
        return customPosition;
    }

    public void setCustomPosition(String customPosition) {
        this.customPosition = customPosition;
    }

    public String getSkills() {
        return skills;
    }

    public void setSkills(String skills) {
        this.skills = skills;
    }

    public FileData getResume() {
        return resume;
    }

    public void setResume(FileData resume) {
        this.resume = resume;
    }

    public FileData getImage() {
        return image;
    }

    public void setImage(FileData image) {
        this.image = image;
    }

    public UserRole getRole() {
        return role;
    }

    public void setRole(UserRole role) {
        this.role = role;
    }

    public List<ExperienceResponse> getExperiences() {
        return experiences;
    }

    public void setExperiences(List<ExperienceResponse> experiences) {
        this.experiences = experiences;
    }

    public List<EducationResponce> getEducations() {
        return educations;
    }

    public void setEducations(List<EducationResponce> educations) {
        this.educations = educations;
    }

    public ImageResponse getImageResponse() {
        return imageResponse;
    }

    public void setImageResponse(ImageResponse imageResponse) {
        this.imageResponse = imageResponse;
    }
}