package com.example.HR.service.impl;

import com.example.HR.entity.models.FileData;
import com.example.HR.exception.CustomException;
import com.example.HR.repository.FileRepository;
import com.example.HR.service.FileStorageService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.UUID;

@Service
public class FileStorageServiceImpl implements FileStorageService {

    private final Path fileStorageLocation;
    private final FileRepository fileRepository;

    public FileStorageServiceImpl(
            @Value("${file.upload-dir}") String uploadDir,
            FileRepository fileRepository) {
        this.fileRepository = fileRepository;
        this.fileStorageLocation = Paths.get(uploadDir).toAbsolutePath().normalize();

        try {
            Files.createDirectories(this.fileStorageLocation);
        } catch (IOException ex) {
            throw new CustomException("Could not create the directory where the uploaded files will be stored.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public FileData storeFile(MultipartFile file) {
        String originalFileName = StringUtils.cleanPath(file.getOriginalFilename());
        String fileExtension = getFileExtension(originalFileName);
        String fileName = UUID.randomUUID().toString() + fileExtension;

        try {
            if (file.isEmpty()) {
                throw new CustomException("Failed to store empty file " + originalFileName, HttpStatus.BAD_REQUEST);
            }

            if (originalFileName.contains("..")) {
                throw new CustomException("Cannot store file with relative path outside current directory " + originalFileName, HttpStatus.BAD_REQUEST);
            }

            Path targetLocation = this.fileStorageLocation.resolve(fileName);
            Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);

            FileData fileData = new FileData();
            fileData.setName(fileName);
            fileData.setType(file.getContentType());
            fileData.setFilePath(targetLocation.toString());
            fileData.setSize(file.getSize());

            return fileRepository.save(fileData);
        } catch (IOException ex) {
            throw new CustomException("Could not store file " + originalFileName + ". Please try again!", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public void deleteFile(String fileName) {
        try {
            Path filePath = this.fileStorageLocation.resolve(fileName);
            Files.deleteIfExists(filePath);
        } catch (IOException ex) {
            throw new CustomException("Could not delete file " + fileName, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    private String getFileExtension(String fileName) {
        if (fileName == null || fileName.lastIndexOf(".") == -1) {
            return "";
        }
        return fileName.substring(fileName.lastIndexOf("."));
    }
} 