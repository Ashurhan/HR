package com.example.HR.controller;

import com.example.HR.dto.applicant.ApplicantPersonalRequest;
import com.example.HR.dto.applicant.ApplicantProfessionRequest;
import com.example.HR.dto.applicant.ApplicantResponse;
import com.example.HR.entity.models.Applicant;
import com.example.HR.service.ApplicantService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/v1/applicants")
@CrossOrigin(origins = "*", maxAge = 3600)
public class ApplicantController {

    private final ApplicantService applicantService;

    public ApplicantController(ApplicantService applicantService) {
        this.applicantService = applicantService;
    }

    @PostMapping("/{id}/personal")
    public ResponseEntity<Applicant> savePersonalApplicant(
            @PathVariable Long id,
            @RequestBody ApplicantPersonalRequest applicant) throws IOException {
        return ResponseEntity.ok(applicantService.savePersonalApplicant(id, applicant));
    }

    @PostMapping("/{id}/profession")
    public ResponseEntity<Applicant> saveProfessionApplicant(
            @PathVariable Long id,
            @RequestBody ApplicantProfessionRequest applicantProfession) throws IOException {
        return ResponseEntity.ok(applicantService.saveProfessionApplicant(id, applicantProfession));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApplicantResponse> getApplicantProfile(@PathVariable Long id) {
        return ResponseEntity.ok(applicantService.getApplicantProfile(id));
    }

    @PutMapping("/{applicantId}/personal")
    public ResponseEntity<Applicant> editPersonalProfile(
            @PathVariable Long applicantId,
            @RequestBody ApplicantPersonalRequest personalRequest) throws IOException {
        return ResponseEntity.ok(applicantService.editPersonalProfile(applicantId, personalRequest));
    }

    @PutMapping("/{applicantId}/profession")
    public ResponseEntity<Applicant> editProfessionProfile(
            @PathVariable Long applicantId,
            @RequestBody ApplicantProfessionRequest professionRequest) throws IOException {
        return ResponseEntity.ok(applicantService.editProfessionProfile(applicantId, professionRequest));
    }

    @GetMapping
    public ResponseEntity<List<ApplicantResponse>> getAllApplicants() {
        return ResponseEntity.ok(applicantService.getAllApplicants());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteApplicant(@PathVariable Long id) {
        applicantService.deleteApplicant(id);
        return ResponseEntity.ok().build();
    }
}
