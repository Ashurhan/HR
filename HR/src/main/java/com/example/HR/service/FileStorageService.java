package com.example.HR.service;

import com.example.HR.entity.models.FileData;
import org.springframework.web.multipart.MultipartFile;

public interface FileStorageService {
    FileData storeFile(MultipartFile file);
    void deleteFile(String fileName);
} 