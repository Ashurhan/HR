package com.example.HR.service.impl;

import com.example.HR.entity.models.Country;
import com.example.HR.repository.CountryRepository;
import com.example.HR.service.CountryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
//@Slf4j
@Service
@Transactional
public class CountryServiceImpl implements CountryService {

    private static final Logger log = LoggerFactory.getLogger(CityServiceImpl.class);
    private final CountryRepository countryRepository;
    private final RestTemplate restTemplate;

    public CountryServiceImpl(CountryRepository countryRepository, RestTemplate restTemplate) {
        this.countryRepository = countryRepository;
        this.restTemplate = restTemplate;
    }

    @Override
    @Transactional
    public void initializeCountries() {
        try {
            if (countryRepository.count() == 0) {
                log.info("Starting countries initialization");
                
                List<Country> countries = Arrays.stream(Locale.getISOCountries())
                    .map(code -> {
                        Locale locale = new Locale("", code);
                        Country country = new Country(locale.getDisplayCountry(), code);
                        return country;
                    })
                    .toList();
                
                countryRepository.saveAll(countries);
                log.info("Successfully initialized {} countries", countries.size());
            }
        } catch (Exception e) {
            log.error("Error initializing countries", e);
            throw new RuntimeException("Failed to initialize countries", e);
        }
    }

    @Override
    @Cacheable("countries")
    public List<Country> getAllCountries() {
        log.info("Fetching all countries");
        return countryRepository.findAll();
    }

    @Override
    public String getCountryName(String countryCode) {
        log.info("Fetching country name for code: {}", countryCode);
        String url = "https://restcountries.com/v3.1/alpha/" + countryCode + "?fields=name";
        Map<String, Object> countryData = restTemplate.getForObject(url, Map.class);
        if (countryData != null && countryData.containsKey("name")) {
            Map<String, String> nameData = (Map<String, String>) countryData.get("name");
            return nameData.get("common");
        }
        return null;
    }

    @Override
    public void clearCache() {
        countryRepository.deleteAll();
    }

} 