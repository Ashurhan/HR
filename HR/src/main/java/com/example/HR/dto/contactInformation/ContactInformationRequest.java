package com.example.HR.dto.contactInformation;

import com.example.HR.entity.models.City;
import com.example.HR.entity.models.Country;

public class ContactInformationRequest {
    private Country country;
    private City city;
    private String phone;
    private String address;

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
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
