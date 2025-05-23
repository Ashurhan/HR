package com.example.HR.controller;

import com.example.HR.entity.models.Country;
import com.example.HR.service.CountryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/countries")
public class CountryController {
    private final CountryService countryService;

    public CountryController(CountryService countryService) {
        this.countryService = countryService;
    }

    @GetMapping
    public ResponseEntity<List<Country>> getAllCountries() {
        return ResponseEntity.ok(countryService.getAllCountries());
    }

    @GetMapping("/name/{code}")
    public ResponseEntity<String> getCountryName(@PathVariable String code) {
        return ResponseEntity.ok(countryService.getCountryName(code));
    }

    @PostMapping("/initialize")
    public ResponseEntity<String> initializeCountries() {
        countryService.initializeCountries();
        return ResponseEntity.ok("Countries initialized successfully");
    }

    @PostMapping("/cache/clear")
    public ResponseEntity<String> clearCache() {
        countryService.clearCache();
        return ResponseEntity.ok("Cache cleared successfully");
    }
} 