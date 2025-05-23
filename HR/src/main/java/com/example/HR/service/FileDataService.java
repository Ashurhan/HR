package com.example.HR.service;

import com.example.HR.entity.models.FileData;
import org.springframework.web.multipart.MultipartFile;
import jakarta.servlet.http.HttpServletResponse;

public interface FileDataService {
    FileData uploadFile(MultipartFile file, FileData oldDocument);
    FileData uploadFile(MultipartFile file);
    void downloadFile(Long id, HttpServletResponse http);
    void getFileById(Long id, HttpServletResponse httpServletResponse);
    void deleteFile(String fileName);
} 