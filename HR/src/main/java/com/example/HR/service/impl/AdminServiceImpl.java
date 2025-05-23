package com.example.HR.service.impl;

import com.example.HR.dto.GenericResponseForUserResponses;
import com.example.HR.dto.admin.ResponsesForAdmin;
import com.example.HR.dto.admin.ResponsesForSupport;
import com.example.HR.dto.admin.SupportRequest;
import com.example.HR.dto.applicant.ApplicantResponse;
import com.example.HR.dto.employer.EmployerResponse;
import com.example.HR.entity.enums.UserRole;
import com.example.HR.entity.models.*;
import com.example.HR.mapper.*;
import com.example.HR.repository.*;
import com.example.HR.service.AdminService;
import com.example.HR.service.VacancyService;
import com.example.HR.utils.DateTimeUtill;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
 import com.example.HR.exception.NotFoundException;


import java.util.*;

@Service
public class AdminServiceImpl implements AdminService {
    private final ApplicantRepository applicantRepository;
    private final EmployerRepository employerRepository;
    private final AdminMapper adminMapper;
    private final UserRepository userRepository;
    private final ApplicantMapper applicantMapper;
    private final EmployerMapper  employerMapper;
    private final PasswordEncoder passwordEncoder;
    private final SupportMapper supportMapper;
    private final ListSupportRepository listSupportRepository;
    private final VacancyRepository vacancyRepository;
    private final VacancyService vacancyService;
    private final GenericResponseMapper genericResponseMapper;
    private static final Logger logger = LoggerFactory.getLogger(AdminServiceImpl.class);

    public AdminServiceImpl(ApplicantRepository applicantRepository, EmployerRepository employerRepository, AdminMapper adminMapper, UserRepository userRepository, ApplicantMapper applicantMapper, EmployerMapper employerMapper, PasswordEncoder passwordEncoder, SupportMapper supportMapper, ListSupportRepository listSupportRepository, VacancyRepository vacancyRepository, VacancyService vacancyService, GenericResponseMapper genericResponseMapper) {
        this.applicantRepository = applicantRepository;
        this.employerRepository = employerRepository;
        this.adminMapper = adminMapper;
        this.userRepository = userRepository;
        this.applicantMapper = applicantMapper;
        this.employerMapper = employerMapper;
        this.passwordEncoder = passwordEncoder;
        this.supportMapper = supportMapper;
        this.listSupportRepository = listSupportRepository;
        this.vacancyRepository = vacancyRepository;
        this.vacancyService = vacancyService;
        this.genericResponseMapper = genericResponseMapper;
    }

    @Override
    public List<ResponsesForAdmin> getAllUsers() {
        return adminMapper.toDtos(userRepository.findEmployersAndApplicants(
                List.of(UserRole.EMPLOYER, UserRole.ADMIN)
        ));
    }

    @Override
    @Transactional
    public void block(String email) {
        if (email == null || email.isEmpty()) {
            throw new IllegalArgumentException("Email не может быть пустым");
        }
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new NotFoundException("User not found with email: " + email));
        user.setBlocked(true);
        userRepository.save(user);
    }

    @Override
    public User isAuth(Authentication authentication) {
        if (authentication == null || !authentication.isAuthenticated()) {
            return null;
        }
        return userRepository.findByEmail(authentication.getName())
                .orElse(null);
    }

    @Override
    public List<ResponsesForSupport> getListSupport() {
        return supportMapper.toDtos(listSupportRepository.findAll());
    }

    @Override
    public ResponsesForSupport createSupport(SupportRequest request) {
        ListSupport listSupport = new ListSupport();
        listSupport.setPersonName(request.getPersonName());
        listSupport.setPersonEmail(request.getPersonEmail());
        listSupport.setPersonPhoneNumber(request.getPersonPhoneNumber());
        listSupport.setMessage(request.getMassage());
        listSupport.setDateSent(DateTimeUtill.getCurrentDateTime());
        listSupportRepository.save(listSupport);
        return supportMapper.toDto(listSupport);
    }

    @Override
    public GenericResponseForUserResponses setRoleForUser(Long userId, String role) {
        if (Arrays.stream(UserRole.values()).noneMatch(r -> r.name().equals(role))) {
            throw new IllegalArgumentException("Некорректная роль: " + role);
        }
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new NotFoundException("User not found with id: " + userId));

        UserRole newRole;
        try {
            newRole = UserRole.valueOf(role);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Invalid role: " + role);
        }

        if (user.getRole() == newRole) {
            throw new IllegalArgumentException("User already has role: " + role);
        }

        if (newRole == UserRole.EMPLOYER) {
            if (user.getApplicant() != null) {
                Employer employer = new Employer();
                employer.setNameOfCompany(user.getApplicant().getFirstName());
                employer.setEmail(user.getApplicant().getEmail());
                employer.setPassword(user.getApplicant().getPassword());
                employer.setUser(user);
                employer.getContactInformation().setCountry(user.getApplicant().getContactInformation().getCountry());
                employer.getContactInformation().setCity(user.getApplicant().getContactInformation().getCity());
                employer.getContactInformation().setAddress(user.getApplicant().getContactInformation().getAddress());
                employer.getContactInformation().setPhone(user.getApplicant().getContactInformation().getPhone());

                employer = employerRepository.save(employer);

                user.setRole(UserRole.EMPLOYER);
                user.setEmployer(employer);
                user.setApplicant(null);
                user = userRepository.save(user);

                applicantRepository.delete(user.getApplicant());

                return genericResponseMapper.toGenericEResponse(employerMapper.toDto(employer));
            }
        } else if (newRole == UserRole.APPLICANT) {
            if (user.getEmployer() != null) {
                Applicant applicant = new Applicant();
                applicant.setFirstName(user.getEmployer().getNameOfCompany());
                applicant.setLastName("");
                applicant.setEmail(user.getEmployer().getEmail());
                applicant.setPassword(user.getEmployer().getPassword());
                applicant.setUser(user);
                
                ContactInformation contactInfo = new ContactInformation();
                contactInfo.setCountry(user.getEmployer().getContactInformation().getCountry());
                contactInfo.setCity(user.getEmployer().getContactInformation().getCity());
                contactInfo.setAddress(user.getEmployer().getContactInformation().getAddress());
                contactInfo.setPhone(user.getEmployer().getContactInformation().getPhone());
                applicant.setContactInformation(contactInfo);

                applicant = applicantRepository.save(applicant);


                user.setRole(UserRole.APPLICANT);
                user.setApplicant(applicant);
                user.setEmployer(null);
                user = userRepository.save(user);

                employerRepository.delete(user.getEmployer());

                return genericResponseMapper.toGenericAResponse(applicantMapper.toResponse(applicant));
            }
        }

        throw new IllegalStateException("Cannot change role: user does not have required data for conversion");
    }

    @Override
    public GenericResponseForUserResponses getResponsesForUserById(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new NotFoundException("User not found with id: " + userId));

        if (user.getRole() == UserRole.EMPLOYER) {
            if (user.getEmployer() == null) {
                throw new NotFoundException("Employer data not found for user id: " + userId);
            }
            EmployerResponse employerResponse = employerMapper.toDto(employerRepository.findById(user.getEmployer().getId())
                    .orElseThrow(() -> new NotFoundException("Employer not found with id: " + user.getEmployer().getId())));
            return genericResponseMapper.toGenericEResponse(employerResponse);
        } 
        
        if (user.getRole() == UserRole.APPLICANT) {
            if (user.getApplicant() == null) {
                throw new NotFoundException("Applicant data not found for user id: " + userId);
            }
            ApplicantResponse applicantResponse = applicantMapper.toResponse(applicantRepository.findById(user.getApplicant().getId())
                    .orElseThrow(() -> new NotFoundException("Applicant not found with id: " + user.getApplicant().getId())));
            return genericResponseMapper.toGenericAResponse(applicantResponse);
        }

        throw new IllegalArgumentException("Invalid user role: " + user.getRole());
    }

    @Override
    public boolean userBlocked(Long userId, Boolean aBoolean) {
        User user = userRepository.findById(userId).orElseThrow();
        user.setBlocked(aBoolean);
        userRepository.save(user);
        return user.getBlocked();    }

    @Override
    public boolean listForDeletingUsers(List<Long> selectedUserIds) {
        for (Long userId : selectedUserIds) {
            Optional<User> user = userRepository.findById(userId);
            if (user.isPresent())
                delete(user.get());
            userRepository.deleteById(userId);
            if (userRepository.findById(userId).isPresent()){
                System.out.println(111111111);
            }
            else {
                System.out.println(99999999);
            }

        }
        return true;
    }

    @Override
    public boolean listForDeletingVacancy(List<Long> selectedVacancyIds) {
        for(Long vacancyId: selectedVacancyIds){
            Optional<Vacancy> vacancy= vacancyRepository.findById(vacancyId);
            vacancyService.delete(vacancyId);
        }
        return true;
    }

    @Override
    public boolean deleteByAccount(String email) {
        User user = userRepository.findByEmail(email).orElseThrow(() ->
                new NotFoundException("The email is not correct: " + email));
        return delete(user);
    }


    @Override
    @Transactional
    public void deleteUser(Long id, String role) {
        User user= userRepository.findById(id).orElseThrow(() -> new NotFoundException(("Пользователь с id = " + id + " не найден")));
        if(!"ADMIN".equals(role)){
            throw  new AccessDeniedException("Вы не имеете права удалять пользователей");
        }

        Employer employer = employerRepository.findByUserId(user.getId())
                .orElseThrow(() -> new NotFoundException("Работодатель с id пользователя " + id + " не найден"));

        if (!vacancyRepository.findByEmployerId(employer).isEmpty()) {
            throw new IllegalStateException("Невозможно удалить пользователя, поскольку у него есть активные вакансии.");
        }

        vacancyRepository.deleteAllByEmployerId(employer);

        try {
            userRepository.delete(user);
            logger.info("Пользователь с id = {} успешно удален", id);
        } catch (Exception ex) {
            logger.error("Ошибка при удалении пользователя с id = {}", id, ex);
            throw new RuntimeException("Не удалось удалить пользователя: " + ex.getMessage());
        }
    }


    public boolean delete(User user) {
        if (user.getEmployer() != null) {
            Employer employer = user.getEmployer();
            employer.setUser(null);
            employerRepository.delete(employer);
        } else {
            Applicant applicant = user.getApplicant();
            applicant.setUser(null);
            applicantRepository.delete(applicant);
        }
        return true;
    }







}
