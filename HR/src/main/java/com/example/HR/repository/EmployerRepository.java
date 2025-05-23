package com.example.HR.repository;

import com.example.HR.entity.models.Employer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EmployerRepository extends JpaRepository<Employer, Long> {
    Optional<Employer> findByEmail(String email);
    Optional<Employer> findByUserId(Long userId);

}
