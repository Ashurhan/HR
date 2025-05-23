package com.example.HR.repository;

import com.example.HR.entity.models.FileData;
import org.springframework.data.jpa.repository.JpaRepository;


public interface FileRepository extends JpaRepository<FileData, Long> {
    FileData findByName(String name);
}
