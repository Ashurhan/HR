package com.example.HR.controller;

import com.example.HR.entity.enums.EducationLevel;
import com.example.HR.entity.enums.Position;
import com.example.HR.entity.models.Applicant;
import com.example.HR.entity.models.Country;
import com.example.HR.entity.models.Response;
import com.example.HR.service.ResponseService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/responses")
@CrossOrigin(origins = "*", maxAge = 3600)
@Tag(name = "Response Management", description = "APIs for managing job application responses")
public class ResponseController {

    private final ResponseService responseService;

    public ResponseController(ResponseService responseService) {
        this.responseService = responseService;
    }

    @PostMapping("/vacancy/{vacancyId}/applicant/{applicantId}")
    @Operation(summary = "Create a new response", description = "Creates a new job application response")
    public ResponseEntity<Response> createResponse(
            @PathVariable Long vacancyId,
            @PathVariable Long applicantId) {
        return ResponseEntity.ok(responseService.createResponse(vacancyId, applicantId));
    }

    @GetMapping("/vacancy/{vacancyId}")
    @Operation(summary = "Get all responses for a vacancy", description = "Retrieves all responses for a specific vacancy")
    public ResponseEntity<List<Response>> getAllResponses(@PathVariable Long vacancyId) {
        return ResponseEntity.ok(responseService.allResponses(vacancyId));
    }

    @GetMapping("/vacancy/{vacancyId}/filter")
    @Operation(summary = "Filter applicants for a vacancy", description = "Filters applicants based on various criteria")
    public ResponseEntity<List<Applicant>> filterApplicants(
            @PathVariable Long vacancyId,
            @RequestParam(required = false) String firstName,
            @RequestParam(required = false) Position position,
            @RequestParam(required = false) Country country,
            @RequestParam(required = false) String city,
            @RequestParam(required = false) EducationLevel educationLevel,
            @RequestParam(required = false) Integer minYears,
            @RequestParam(required = false) Integer maxYears) {
        return ResponseEntity.ok(responseService.filterApplicants(
                vacancyId, firstName, position, country, city, educationLevel, minYears, maxYears));
    }
}


