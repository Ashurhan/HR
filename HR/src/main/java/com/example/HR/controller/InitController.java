package com.example.HR.controller;


import com.example.HR.service.CountryService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/init")
public class InitController {
    private final CountryService countryService;
    public InitController(CountryService countryService) {
        this.countryService = countryService;
    }

    @PostMapping("country")
    public String initCountry() {
        countryService.initializeCountries();
        return "Success";
    }

}
