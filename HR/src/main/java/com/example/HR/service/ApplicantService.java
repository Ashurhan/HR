package com.example.HR.service;

import com.example.HR.dto.applicant.ApplicantPersonalRequest;
import com.example.HR.dto.applicant.ApplicantProfessionRequest;
import com.example.HR.dto.applicant.ApplicantResponse;
import com.example.HR.entity.models.Applicant;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;

@Component
public interface ApplicantService {
    Applicant savePersonalApplicant(Long id, ApplicantPersonalRequest applicant) throws IOException;

    Applicant saveProfessionApplicant(Long id, ApplicantProfessionRequest applicantProfession) throws IOException;

    ApplicantResponse getApplicantProfile(Long id);

    Applicant editPersonalProfile(Long applicantId, ApplicantPersonalRequest personalRequest) throws IOException;

    Applicant editProfessionProfile(Long applicantId, ApplicantProfessionRequest professionRequest) throws IOException;

    List<ApplicantResponse> getAllApplicants();

    void deleteApplicant(Long id);
}
