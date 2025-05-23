package com.example.HR.controller;

import com.example.HR.entity.models.City;
import com.example.HR.service.CityService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cities")
public class CityController {
    private final CityService cityService;

    public CityController(CityService cityService) {
        this.cityService = cityService;
    }

    @GetMapping("/country/{countryId}")
    public ResponseEntity<List<City>> getCitiesByCountryId(@PathVariable Long countryId) {
        return ResponseEntity.ok(cityService.getCitiesByCountryId(countryId));
    }

    @GetMapping("/{id}")
    public ResponseEntity<City> getCityById(@PathVariable Long id) {
        return ResponseEntity.ok(cityService.getCityById(id));
    }

    @PostMapping("/country/{countryId}/initialize")
    public ResponseEntity<String> initializeCitiesForCountry(@PathVariable Long countryId) {
        cityService.initializeCitiesForCountry(countryId);
        return ResponseEntity.ok("Cities initialized successfully for country: " + countryId);
    }
}
