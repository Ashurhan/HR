package com.example.HR.service.impl;

import com.example.HR.dto.City.CityResponse;
import com.example.HR.entity.models.City;
import com.example.HR.entity.models.Country;
import com.example.HR.repository.CityRepository;
import com.example.HR.repository.CountryRepository;
import com.example.HR.service.CityService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.List;

//@Slf4j
@Service

@Transactional
public class CityServiceImpl implements CityService {

    private static final Logger log = LoggerFactory.getLogger(CityServiceImpl.class);
    private final CityRepository cityRepository;
    private final CountryRepository countryRepository;
    private final WebClient.Builder webClientBuilder;

    public CityServiceImpl(CityRepository cityRepository, CountryRepository countryRepository, WebClient.Builder webClientBuilder) {
        this.cityRepository = cityRepository;
        this.countryRepository = countryRepository;
        this.webClientBuilder = webClientBuilder;
    }

    @Value("${rapidapi.key}")
    private String rapidApiKey;

    @Value("${rapidapi.host}")
    private String rapidApiHost;

    @Override
    @Cacheable("cities")
    public List<City> getCitiesByCountryId(Long countryId) {
        log.info("Fetching cities by country id: {}", countryId);
        // Сначала проверяем в базе данных
        List<City> citiesFromDb = cityRepository.findByCountryId(countryId);
        if (!citiesFromDb.isEmpty()) {
            log.info("Found {} cities in database for country id: {}", citiesFromDb.size(), countryId);
            return citiesFromDb;
        }

        // Если в базе нет, получаем из API
        Country country = countryRepository.findById(countryId)
            .orElseThrow(() -> new EntityNotFoundException("Country not found with id: " + countryId));

        return fetchCitiesFromApi(country);
    }

    @Override
    @Cacheable("city")
    public City getCityById(Long id) {
        log.info("Fetching city by id: {}", id);
        return cityRepository.findById(id)
            .orElseThrow(() -> new EntityNotFoundException("City not found with id: " + id));
    }

    @Override
    @Transactional
    public void initializeCitiesForCountry(Long countryId) {
        log.info("Initializing cities for country id: {}", countryId);
        Country country = countryRepository.findById(countryId)
            .orElseThrow(() -> new EntityNotFoundException("Country not found with id: " + countryId));

        // Проверяем, есть ли уже города для этой страны
        if (!cityRepository.findByCountryId(countryId).isEmpty()) {
            log.info("Cities already initialized for country: {}", country.getName());
            return;
        }

        // Получаем города из API и сохраняем в базу
        List<City> cities = fetchCitiesFromApi(country);
        cityRepository.saveAll(cities);
        log.info("Successfully initialized {} cities for country: {}", cities.size(), country.getName());
    }

    private List<City> fetchCitiesFromApi(Country country) {
        log.info("Fetching cities from API for country: {}", country.getName());
        
        WebClient webClient = webClientBuilder
            .baseUrl("https://wft-geo-db.p.rapidapi.com/v1/geo")
            .defaultHeader("x-rapidapi-host", rapidApiHost)
            .defaultHeader("x-rapidapi-key", rapidApiKey)
            .build();

        try {
            CityResponse response = webClient.get()
                .uri(uriBuilder -> uriBuilder
                    .path("/countries/{countryName}/cities")
                    .queryParam("limit", "100")
                    .queryParam("offset", "0")
                    .queryParam("sort", "-population")
                    .build(country.getName()))
                .retrieve()
                .bodyToMono(CityResponse.class)
                .block();

            if (response == null || response.getData() == null) {
                log.warn("No cities found for country: {}", country.getName());
                return List.of();
            }

            return response.getData().stream()
                .map(cityData -> {
                    City city = new City(cityData.getName());
                    city.setCountry(country);
                    return city;
                })
                .toList();
        } catch (Exception e) {
            log.error("Error fetching cities from API for country: {}", country.getName(), e);
            throw new RuntimeException("Failed to fetch cities from API", e);
        }
    }
}

