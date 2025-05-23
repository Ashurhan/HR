package com.example.HR.entity.models;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Entity
public class ContactInformation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "Страна обязательна")
    @ManyToOne
    @JoinColumn(name = "country_id")
    private Country country;

    @NotNull(message = "Город обязателен")
    @ManyToOne
    @JoinColumn(name = "city_id")
    private City city;

    private String phone;
    private String address;



    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public @NotNull(message = "Страна обязательна") Country getCountry() {
        return country;
    }

    public void setCountry(@NotNull(message = "Страна обязательна") Country country) {
        this.country = country;
    }

    public @NotNull(message = "Город обязателен") City getCity() {
        return city;
    }

    public void setCity(@NotNull(message = "Город обязателен") City city) {
        this.city = city;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}