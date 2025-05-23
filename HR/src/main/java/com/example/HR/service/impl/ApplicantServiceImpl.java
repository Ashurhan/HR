package com.example.HR.service.impl;

import com.example.HR.entity.models.FileData;
import com.example.HR.repository.FileRepository;
import com.example.HR.dto.applicant.ApplicantPersonalRequest;
import com.example.HR.dto.applicant.ApplicantProfessionRequest;
import com.example.HR.dto.applicant.ApplicantResponse;
import com.example.HR.entity.models.Applicant;
import com.example.HR.exception.NotFoundException;
import com.example.HR.mapper.ApplicantMapper;
import com.example.HR.repository.ApplicantRepository;
import com.example.HR.service.ApplicantService;
import com.example.HR.service.EmailService;
import com.example.HR.service.FileDataService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ApplicantServiceImpl implements ApplicantService {
    private final ApplicantRepository applicantRepository;
    private final ApplicantMapper applicantMapper;
    private final EmailService emailService;
    private final FileRepository fileDataRepository;
    private final FileDataService fileDataService;

    public ApplicantServiceImpl(ApplicantRepository applicantRepository,
                              ApplicantMapper applicantMapper,
                              EmailService emailService,
                              FileRepository fileDataRepository,
                              FileDataService fileDataService) {
        this.applicantRepository = applicantRepository;
        this.applicantMapper = applicantMapper;
        this.emailService = emailService;
        this.fileDataRepository = fileDataRepository;
        this.fileDataService = fileDataService;
    }



    @Override
    @Transactional
    public Applicant savePersonalApplicant(Long id, ApplicantPersonalRequest personalRequest) throws IOException {
        Applicant applicant = applicantRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Applicant Not Found", HttpStatus.NOT_FOUND));

        if (personalRequest.getImage() != null && !personalRequest.getImage().isEmpty()) {
            FileData imageFileData = fileDataService.uploadFile(personalRequest.getImage());
            applicant.setImage(imageFileData);
        }

        applicantMapper.updateEntityFromPersonal(applicant, personalRequest);
        return applicantRepository.save(applicant);
    }

    @Override
    @Transactional
    public Applicant saveProfessionApplicant(Long id, ApplicantProfessionRequest request) throws IOException {
        Applicant applicant = applicantRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Applicant Not Found", HttpStatus.NOT_FOUND));

        if (request.getResume() != null && !request.getResume().isEmpty()) {
            FileData resumeFile = fileDataService.uploadFile(request.getResume());
            applicant.setResume(resumeFile);
        }

        applicantMapper.updateEntityFromProfession(applicant, request);
        return applicantRepository.save(applicant);
    }

    @Override
    @Transactional(readOnly = true)
    public ApplicantResponse getApplicantProfile(Long id) {
        Applicant applicant = applicantRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Applicant not found", HttpStatus.NOT_FOUND));
        return applicantMapper.toResponse(applicant);
    }

    @Override
    @Transactional
    public Applicant editPersonalProfile(Long applicantId, ApplicantPersonalRequest personalRequest) throws IOException {
        Applicant applicant = applicantRepository.findById(applicantId)
                .orElseThrow(() -> new NotFoundException("Applicant Not Found", HttpStatus.NOT_FOUND));

        if (personalRequest.getImage() != null && !personalRequest.getImage().isEmpty()) {
            FileData imageFileData = fileDataService.uploadFile(personalRequest.getImage());
            applicant.setImage(imageFileData);
        }

        applicantMapper.updateEntityFromPersonal(applicant, personalRequest);
        return applicantRepository.save(applicant);
    }

    @Override
    @Transactional
    public Applicant editProfessionProfile(Long applicantId, ApplicantProfessionRequest professionRequest) throws IOException {
        Applicant applicant = applicantRepository.findById(applicantId)
                .orElseThrow(() -> new NotFoundException("Applicant Not Found", HttpStatus.NOT_FOUND));

        if (professionRequest.getResume() != null && !professionRequest.getResume().isEmpty()) {
            FileData resumeFile = fileDataService.uploadFile(professionRequest.getResume());
            applicant.setResume(resumeFile);
        }

        applicantMapper.updateEntityFromProfession(applicant, professionRequest);
        return applicantRepository.save(applicant);
    }

    @Override
    @Transactional(readOnly = true)
    public List<ApplicantResponse> getAllApplicants() {
        return applicantRepository.findAll().stream()
                .map(applicantMapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public void deleteApplicant(Long applicantId) {
        if (!applicantRepository.existsById(applicantId)) {
            throw new NotFoundException("Applicant not found", HttpStatus.NOT_FOUND);
        }
        applicantRepository.deleteById(applicantId);
    }


}



