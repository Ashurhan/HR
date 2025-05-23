package com.example.HR.repository;

import com.example.HR.entity.models.ContactInformation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ConatactInformationRepository extends JpaRepository<ContactInformation , Long> {
}
