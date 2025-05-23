package com.example.HR.repository;

import com.example.HR.entity.enums.EducationLevel;
import com.example.HR.entity.enums.Position;
import com.example.HR.entity.models.Applicant;
import com.example.HR.entity.models.Response;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ResponseRepository extends JpaRepository<Response, Long> {

    boolean existsByVacancyIdAndApplicantId(Long vacancyId, Long applicantId);

    List<Response> findAllByVacancyId(Long vacancyId);

    // Комбинированный фильтр кандидатов
    @Query("SELECT DISTINCT r.applicant FROM Response r " +
            "LEFT JOIN r.applicant.experiences exp " +
            "LEFT JOIN r.applicant.educations edu " +
            "WHERE r.vacancy.id = :vacancyId " +
            "AND (:firstName IS NULL OR LOWER(r.applicant.firstName) LIKE LOWER(CONCAT('%', :firstName, '%'))) " +
            "AND (:position IS NULL OR r.applicant.position = :position) " +
            "AND (:countryId IS NULL OR r.applicant.contactInformation.country.id = :countryId) " +
            "AND (:city IS NULL OR LOWER(r.applicant.contactInformation.city) LIKE LOWER(CONCAT('%', :city, '%'))) " +
            "AND (:educationLevel IS NULL OR edu.degree = :educationLevel) " +
            "AND (:minYears IS NULL OR " +
            "    (CASE WHEN exp.isCurrent = true " +
            "         THEN (:currentYear - exp.startYear) " +
            "         ELSE (exp.endYear - exp.startYear) END) >= :minYears) " +
            "AND (:maxYears IS NULL OR " +
            "    (CASE WHEN exp.isCurrent = true " +
            "         THEN (:currentYear - exp.startYear) " +
            "         ELSE (exp.endYear - exp.startYear) END) <= :maxYears)")
    List<Applicant> findApplicantsByVacancyIdWithFilters(
            @Param("vacancyId") Long vacancyId,
            @Param("firstName") String firstName,
            @Param("position") Position position,
            @Param("countryId") Long countryId,
            @Param("city") String city,
            @Param("educationLevel") EducationLevel educationLevel,
            @Param("minYears") Integer minYears,
            @Param("maxYears") Integer maxYears,
            @Param("currentYear") Integer currentYear);
}



