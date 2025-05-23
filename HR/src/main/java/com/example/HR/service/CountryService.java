package com.example.HR.service;

import com.example.HR.entity.models.Country;
import java.util.List;

public interface CountryService {
    // Инициализация списка стран при первом запуске приложения
    void initializeCountries();
    
    // Получение списка всех стран для выбора в форме создания вакансии
    List<Country> getAllCountries();
    
    // Получение названия страны по коду для отображения
    String getCountryName(String countryCode);

    void clearCache();

}

