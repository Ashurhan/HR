package com.example.HR.repository;

import com.example.HR.entity.models.Employer;
import com.example.HR.entity.models.Vacancy;
import com.example.HR.entity.enums.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface VacancyRepository extends JpaRepository<Vacancy, Long> {
    List<Vacancy> findByEmployerId_Id(Long employerId);

    // Фильтр по статусу
    List<Vacancy> findByEmployerId_IdAndStatus(Long employerId, Status status);

    // Сортировка по количеству откликов
    @Query("SELECT v FROM Vacancy v LEFT JOIN v.responseList r WHERE v.employerId.id = :employerId GROUP BY v.id ORDER BY COUNT(r) DESC")
    List<Vacancy> findByEmployerId_IdOrderByResponseListSizeDesc(Long employerId);

    // Сортировка по дате создания
    List<Vacancy> findByEmployerId_IdOrderByCreatedAtDesc(Long employerId);


    List<Vacancy> findByEmployerId(Employer employerId);

    // Удаление всех вакансий работодателя
    void deleteAllByEmployerId(Employer employerId);

    @Query("""
    SELECT v FROM Vacancy v 
    WHERE lower(v.customPosition) LIKE lower(concat('%', :search, '%')) 
       OR lower(v.companyInfo) LIKE lower(concat('%', :search, '%')) 
       OR lower(v.requiredSkills) LIKE lower(concat('%', :search, '%')) 
       OR lower(v.vacancyDescription) LIKE lower(concat('%', :search, '%')) 
       OR lower(v.contactInformation.city) LIKE lower(concat('%', :search, '%')) 
       OR lower(v.contactInformation.country) LIKE lower(concat('%', :search, '%')) 
       OR lower(CAST(v.position AS string)) LIKE lower(concat('%', :search, '%')) 
       OR lower(CAST(v.industry AS string)) LIKE lower(concat('%', :search, '%')) 
       OR lower(CAST(v.experience AS string)) LIKE lower(concat('%', :search, '%'))
""")
    List<Vacancy> search(@Param("search") String search);


}
