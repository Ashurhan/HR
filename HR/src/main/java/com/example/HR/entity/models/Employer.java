package com.example.HR.entity.models;

import com.example.HR.entity.enums.UserRole;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

@Entity
public class Employer implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String nameOfCompany;

    @Email
    @Column(unique=true)
    private String email;

    private String password;


    @OneToOne(cascade = CascadeType.ALL, mappedBy = "employer")
    private User user;


    @OneToOne(cascade = CascadeType.ALL)
    private FileData profileImage;
    @OneToOne
    @JoinColumn(name = "contact_information_id")
    @NotNull
    private ContactInformation contactInformation;




    @OneToMany(cascade = CascadeType.ALL, mappedBy = "employerId")
    private List<Vacancy> vacancyList;

    @ManyToMany
    @JoinTable(
        name = "employer_favorites",
        joinColumns = @JoinColumn(name = "employer_id"),
        inverseJoinColumns = @JoinColumn(name = "applicant_id")
    )
    private List<Applicant> favorites;


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singletonList(new SimpleGrantedAuthority("ROLE_" + getUser().getRole().name()));
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return false;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNameOfCompany() {
        return nameOfCompany;
    }

    public void setNameOfCompany(String nameOfCompany) {
        this.nameOfCompany = nameOfCompany;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    public List<Vacancy> getVacancyList() {
        return vacancyList;
    }

    public void setVacancyList(List<Vacancy> vacancyList) {
        this.vacancyList = vacancyList;
    }

    public FileData getProfileImage() {
        return profileImage;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setProfileImage(FileData profileImage) {
        this.profileImage = profileImage;
    }

    public @NotNull ContactInformation getContactInformation() {
        return contactInformation;
    }

    public void setContactInformation(@NotNull ContactInformation contactInformation) {
        this.contactInformation = contactInformation;
    }

    public List<Applicant> getFavorites() {
        return favorites;
    }

    public void setFavorites(List<Applicant> favorites) {
        this.favorites = favorites;
    }
}
