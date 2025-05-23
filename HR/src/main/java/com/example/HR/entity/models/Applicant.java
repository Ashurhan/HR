package com.example.HR.entity.models;

import com.example.HR.entity.enums.Position;
import com.example.HR.entity.enums.UserRole;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
@Entity
@Table(name = "applicants")
public class Applicant implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToOne(mappedBy = "applicant")
    private User user;

    @NotBlank(message = "Имя обязательно")
    @Column(name = "first_name", nullable = false)
    private String firstName;

    @NotBlank(message = "Фамилия обязательна")
    @Column(name = "last_name", nullable = false)
    private String lastName;

    @NotBlank(message = "Электронная почта обязательна")
    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @NotBlank(message = "Пароль обязателен")
    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "birth_date")
    private LocalDate birthDate;

    @OneToOne
    @JoinColumn(name = "contact_information_id")
    @NotNull
    private ContactInformation contactInformation;


    @Column(name = "about", columnDefinition = "TEXT")
    private String about;

    @OneToMany(mappedBy = "applicant", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Education> educations;

    @OneToMany(mappedBy = "applicant", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Experience> experiences;

    @NotNull(message = "Позиция обязательна")
    @Enumerated(EnumType.STRING)
    @Column(name = "position", nullable = false)
    private Position position;

    @Column(name = "custom_position")
    private String customPosition;

    @Column(name = "skills", columnDefinition = "TEXT")
    private String skills;



    @NotNull(message = "Роль обязательна")
    @Enumerated(EnumType.STRING)
    @Column(name = "role", nullable = false)
    private UserRole role = UserRole.APPLICANT;

    @ManyToMany(mappedBy = "favorites")
    private List<Employer> employers;

    @OneToOne(cascade = CascadeType.ALL)
    private FileData resume;

    @OneToOne(cascade = CascadeType.ALL)
    private FileData image;

    @Column(name = "last_visit")
    private LocalDateTime lastVisit;

    public LocalDateTime getLastVisit() {
        return lastVisit;
    }

    public void setLastVisit(LocalDateTime lastVisit) {
        this.lastVisit = lastVisit;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singletonList(new SimpleGrantedAuthority("ROLE_" + role.name()));
    }

    @Override
    public String getUsername() {
        return email; // Используем email как имя пользователя
    }

    @Override
    public String getPassword() {
        return password; // Возвращаем пароль
    }

    @Override
    public boolean isAccountNonExpired() {
        return true; // Срок действия аккаунта не истёк
    }

    @Override
    public boolean isAccountNonLocked() {
        return true; // Аккаунт не заблокирован
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true; // Учетные данные не истекли
    }

    @Override
    public boolean isEnabled() {
        return false;
    }


    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getFirstName() { return firstName; }
    public void setFirstName(String firstName) { this.firstName = firstName; }
    public String getLastName() { return lastName; }
    public void setLastName(String lastName) { this.lastName = lastName; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public void setPassword(String password) { this.password = password; }
    public LocalDate getBirthDate() { return birthDate; }
    public void setBirthDate(LocalDate birthDate) { this.birthDate = birthDate; }
    public @NotNull ContactInformation getContactInformation() {
        return contactInformation;
    }
    public void setContactInformation(@NotNull ContactInformation contactInformation) {
        this.contactInformation = contactInformation;
    }

    public String getAbout() { return about; }
    public void setAbout(String about) { this.about = about; }
    public List<Employer> getEmployers() {
        return employers;
    }

    public void setEmployers(List<Employer> employers) {
        this.employers = employers;
    }

    public List<Education> getEducations() { return educations; }
    public void setEducations(List<Education> educations) { this.educations = educations; }
    public List<Experience> getExperiences() { return experiences; }
    public void setExperiences(List<Experience> experiences) { this.experiences = experiences; }
    public Position getPosition() { return position; }
    public void setPosition(Position position) { this.position = position; }
    public String getCustomPosition() { return customPosition; }
    public void setCustomPosition(String customPosition) { this.customPosition = customPosition; }
    public String getSkills() { return skills; }
    public void setSkills(String skills) { this.skills = skills; }
    public UserRole getRole() { return role; }
    public void setRole(UserRole role) { this.role = role; }

    public void setImage(FileData image) {
        this.image = image;
    }

    public FileData getImage() {
        return image;
    }

    public FileData getResume() {
        return resume;
    }

    public void setResume(FileData resume) {
        this.resume = resume;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}