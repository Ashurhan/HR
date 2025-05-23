package com.example.HR.controller;

import com.example.HR.dto.applicant.ApplicantVacanciesResponses;
import com.example.HR.dto.vacancy.VacancyRequest;
import com.example.HR.dto.vacancy.VacancyResponse;
import com.example.HR.dto.error.ErrorResponse;
import com.example.HR.entity.models.Category;
import com.example.HR.entity.models.Vacancy;
import com.example.HR.entity.enums.Status;
import com.example.HR.service.VacancyService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.nio.file.AccessDeniedException;
import java.util.List;

@RestController
@RequestMapping("/api/v1/vacancies")
@CrossOrigin(origins = "*", maxAge = 3600)
public class VacancyController {

    private final VacancyService vacancyService;

    public VacancyController(VacancyService vacancyService) {
        this.vacancyService = vacancyService;
    }

    @PostMapping
    public ResponseEntity<VacancyResponse> createVacancy(@RequestBody VacancyRequest request) throws AccessDeniedException {
        return ResponseEntity.ok(vacancyService.createVacancy(request));
    }

    @GetMapping("/employer/{employerId}")
    public ResponseEntity<List<Vacancy>> getMyVacancies(@PathVariable Long employerId) {
        return ResponseEntity.ok(vacancyService.getMyVacancies(employerId));
    }

    @GetMapping("/employer/{employerId}/status/{status}")
    public ResponseEntity<List<Vacancy>> getVacanciesByStatus(
            @PathVariable Long employerId,
            @PathVariable Status status) {
        return ResponseEntity.ok(vacancyService.getVacanciesByStatus(employerId, status));
    }

    @GetMapping("/employer/{employerId}/responses")
    public ResponseEntity<List<Vacancy>> getVacanciesByResponses(@PathVariable Long employerId) {
        return ResponseEntity.ok(vacancyService.getVacanciesByResponses(employerId));
    }

    @GetMapping("/employer/{employerId}/created")
    public ResponseEntity<List<Vacancy>> getVacanciesByCreatedAt(@PathVariable Long employerId) {
        return ResponseEntity.ok(vacancyService.getVacanciesByCreatedAt(employerId));
    }

    @GetMapping("/category/{category}")
    public ResponseEntity<List<Vacancy>> getVacanciesByCategory(@PathVariable Category category) {
        return ResponseEntity.ok(vacancyService.getVacanciesByCategory(category));
    }

    @PutMapping("/{id}/employer/{employerId}")
    public ResponseEntity<Vacancy> editVacancy(
            @PathVariable Long id,
            @RequestBody VacancyRequest updatedVacancy,
            @PathVariable Long employerId) {
        return ResponseEntity.ok(vacancyService.editVacancy(id, updatedVacancy, employerId));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Vacancy> updateVacancy(
            @PathVariable Long id,
            @RequestBody VacancyRequest vacancyRequest) {
        return ResponseEntity.ok(vacancyService.update(id, vacancyRequest));
    }

    @GetMapping("/search")
    public ResponseEntity<List<ApplicantVacanciesResponses>> searchVacancy(@RequestParam String search) {
        return ResponseEntity.ok(vacancyService.searchVacancy(search));
    }

    @GetMapping("/{id}")
    public ResponseEntity<VacancyResponse> aboutVacancy(@PathVariable Long id) {
        return ResponseEntity.ok(vacancyService.aboutVacancy(id));
    }

    @PostMapping("/{id}/archive/employer/{employerId}")
    public ResponseEntity<?> archiveVacancy(@PathVariable Long id, @PathVariable Long employerId) {
        vacancyService.archiveVacancy(id, employerId);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/{id}/open/employer/{employerId}")
    public ResponseEntity<?> openVacancy(@PathVariable Long id, @PathVariable Long employerId) {
        vacancyService.openVacancy(id, employerId);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/{id}/close/employer/{employerId}")
    public ResponseEntity<?> closeVacancy(@PathVariable Long id, @PathVariable Long employerId) {
        vacancyService.closeVacancy(id, employerId);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}/employer/{employerId}")
    public ResponseEntity<?> deleteVacancy(@PathVariable Long id, @PathVariable Long employerId) {
        vacancyService.deleteVacancy(id, employerId);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        boolean deleted = vacancyService.delete(id);
        if (deleted) {
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.badRequest().body("Failed to delete vacancy");
    }

    // Обработка ошибок валидации
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleValidationExceptions(MethodArgumentNotValidException ex) {
        StringBuilder errorMessage = new StringBuilder();
        ex.getBindingResult().getAllErrors().forEach(error ->
                errorMessage.append(error.getDefaultMessage()).append("; "));
        return ResponseEntity.status(400).body(new ErrorResponse(errorMessage.toString()));
    }
}

