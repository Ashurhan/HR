package com.example.HR.service.impl;

import com.example.HR.config.JwtTokenProvider;
import com.example.HR.entity.models.*;
import com.example.HR.exception.BlockedException;
import com.example.HR.exception.CustomException;
import com.example.HR.mapper.FileMapper;
import com.example.HR.repository.EmployerRepository;
import com.example.HR.repository.FileRepository;
import com.example.HR.repository.UserRepository;
import com.example.HR.dto.UserResponse;
import com.example.HR.dto.applicant.RegisterApplicantRequest;
import com.example.HR.dto.auth.AuthenticationRequest;
import com.example.HR.dto.auth.AuthenticationResponse;
import com.example.HR.dto.auth.GoogleLoginRequest;
import com.example.HR.repository.ApplicantRepository;
import com.example.HR.service.AuthenticationService;
import com.example.HR.dto.employer.RegisterEmployerRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.context.DelegatingApplicationListener;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.example.HR.entity.enums.UserRole;
import com.example.HR.utils.CodeGenerator;
import com.example.HR.service.EmailService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

//@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class AuthenticationServiceImpl implements AuthenticationService {
    private static final Logger log = LoggerFactory.getLogger(AuthenticationServiceImpl.class);

    private final UserRepository userRepository;
    private final ApplicantRepository applicantRepository;
    private final PasswordEncoder passwordEncoder;
    private final FileRepository fileRepository;
    private final FileMapper fileMapper;
    private final JwtTokenProvider jwtTokenProvider;
    private final AuthenticationManager authenticationManager;
    private final DelegatingApplicationListener delegatingApplicationListener;
    private final EmployerServiceImpl employerServiceImpl;
    private final EmployerRepository employerRepository;
    private final EmailService emailService;

    @Override
    public AuthenticationResponse login(AuthenticationRequest request) {
        log.info("Attempting login for user: {}", request.getEmail());
        try {
            authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword())
            );
            
            User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new CustomException("User not found", HttpStatus.NOT_FOUND));
                
            if (user.getRole() != UserRole.ADMIN && user.getBlocked()) {
                throw new BlockedException("User is blocked");
            }
            
            user.setLastVisit(getCurrentTimeAsString());
            userRepository.save(user);
            
            String token = jwtTokenProvider.createToken(user.getEmail(), user.getRole());
            return AuthenticationResponse.builder()
                .user(convertToUserResponse(user))
                .accessToken(token)
                .build();
                
        } catch (AuthenticationException e) {
            log.error("Authentication failed for user: {}", request.getEmail(), e);
            throw new BadCredentialsException("Invalid username/password supplied");
        }
    }

    @Override
    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        return login(request);
    }

    private UserResponse convertToUserResponse(User user) {
        log.debug("Converting user to response: {}", user.getEmail());
        UserResponse userResponse = UserResponse.builder()
            .id(user.getId())
            .firstname(user.getFirstName())
            .lastname(user.getLastName())
            .email(user.getEmail())
            .role(user.getRole())
            .isOnline(user.isOnline())
            .companyName(user.getRole().equals(UserRole.EMPLOYER) ? user.getEmployer().getNameOfCompany() : null)
            .build();

        if (user.getRole().equals(UserRole.APPLICANT) && user.getApplicant().getImage() != null) {
            fileRepository.findById(user.getApplicant().getImage().getId())
                .ifPresent(fileData -> userResponse.setFileResponse(fileMapper.toDto(fileData)));
        }

        return userResponse;
    }

    private void checkUsernameIsExist(String email) {
        if (userRepository.findByEmail(email).isPresent()) {
            log.error("Email already exists: {}", email);
            throw new CustomException(
                String.format("Email %s already exists. Please choose another one", email),
                HttpStatus.BAD_REQUEST
            );
        }
    }

    @Override
    @Transactional
    public ResponseEntity<AuthenticationResponse> applicantRegister(RegisterApplicantRequest request) {
        log.info("Registering new applicant: {}", request.getEmail());
        checkUsernameIsExist(request.getEmail());
        
        User user = new User();
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setFirstName(request.getFirstname());
        user.setLastName(request.getLastname());
        user.setRole(UserRole.APPLICANT);
        user.setBlocked(false);
        
        Applicant applicant = new Applicant();
        applicant.setEmail(user.getEmail());
        applicant.setFirstName(request.getFirstname());
        applicant.setLastName(request.getLastname());
        applicantRepository.save(applicant);
        user.setApplicant(applicant);
        
        BlockedUser blockedUser = new BlockedUser();
        blockedUser.setViewingCompanyData(false);
        blockedUser.setViewingAndSearchingForVacancies(false);
        blockedUser.setViewTheStatusOfResponded(false);
        blockedUser.setCommunicationWithEmployers(false);
        blockedUser.setViewingCandidateData(false);
        blockedUser.setVacancyAndHiringManagement(false);
        blockedUser.setCommunicationWithJobSeekers(false);
        user.setBlockedUser(blockedUser);

        String verificationCode = CodeGenerator.generateCode();
        user.setVerificationCode(verificationCode);
        userRepository.save(user);

        emailService.sendVerificationCode(user.getEmail(), verificationCode);

        return ResponseEntity.ok(AuthenticationResponse.builder()
            .user(convertToUserResponse(user))
            .build());
    }

    @Override
    @Transactional
    public ResponseEntity<AuthenticationResponse> adminRegister(RegisterApplicantRequest request) {
        log.info("Registering new admin: {}", request.getEmail());
        checkUsernameIsExist(request.getEmail());
        
        User user = new User();
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setRole(UserRole.ADMIN);
        userRepository.save(user);
        
        return ResponseEntity.ok(AuthenticationResponse.builder()
            .user(convertToUserResponse(user))
            .build());
    }

    @Override
    @Transactional
    public ResponseEntity<AuthenticationResponse> employerRegister(RegisterEmployerRequest request) {
        log.info("Registering new employer: {}", request.getEmail());
        checkUsernameIsExist(request.getEmail());
        
        User user = new User();
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setRole(UserRole.EMPLOYER);
        
        Employer employer = new Employer();
        employer.setEmail(user.getEmail());
        employer.setNameOfCompany(request.getCompanyName());
        employerRepository.save(employer);
        user.setEmployer(employer);
        
        BlockedUser blockedUser = new BlockedUser();
        blockedUser.setViewingCompanyData(false);
        blockedUser.setViewingAndSearchingForVacancies(false);
        blockedUser.setViewTheStatusOfResponded(false);
        blockedUser.setCommunicationWithEmployers(false);
        blockedUser.setViewingCandidateData(false);
        blockedUser.setVacancyAndHiringManagement(false);
        blockedUser.setCommunicationWithJobSeekers(false);
        user.setBlockedUser(blockedUser);
        
        userRepository.save(user);
        
        return ResponseEntity.ok(AuthenticationResponse.builder()
            .user(convertToUserResponse(user))
            .build());
    }

    @Override
    public boolean validateVerificationCode(String email, String code) {
        log.info("Validating verification code for user: {}", email);
        return userRepository.findByEmail(email)
            .map(user -> user.getVerificationCode().equals(code))
            .orElse(false);
    }

    private String getCurrentTimeAsString() {
        return LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
    }

    @Override
    @Transactional
    public String googleLogin(GoogleLoginRequest request) {
        log.info("Processing Google login for user: {}", request.getEmail());
        User user = userRepository.findByEmail(request.getEmail())
            .orElseGet(() -> {
                User newUser = new User();
                newUser.setEmail(request.getEmail());
                newUser.setRole(UserRole.APPLICANT);
                newUser.setBlocked(false);
                return userRepository.save(newUser);
            });
            
        return jwtTokenProvider.createToken(user.getEmail(), user.getRole());
    }
}
