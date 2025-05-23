package com.example.HR.service;

import com.example.HR.entity.models.City;
import java.util.List;

public interface CityService {
    // Получение списка городов по ID страны
    List<City> getCitiesByCountryId(Long countryId);
    
    // Получение города по ID
    City getCityById(Long id);
    
    // Инициализация городов для страны
    void initializeCitiesForCountry(Long countryId);
}
