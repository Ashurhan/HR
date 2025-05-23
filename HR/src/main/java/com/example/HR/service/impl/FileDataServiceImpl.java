package com.example.HR.service.impl;

import com.example.HR.entity.models.FileData;
import com.example.HR.exception.CustomException;
import com.example.HR.exception.NotFoundException;
import com.example.HR.mapper.FileMapper;
import com.example.HR.repository.FileRepository;
import com.example.HR.service.FileDataService;
import jakarta.servlet.http.HttpServletResponse;
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
public class FileDataServiceImpl implements FileDataService {

    private final FileRepository repository;
    private final FileMapper fileMapper;
    private final Path fileStorageLocation;

    public FileDataServiceImpl(
            FileRepository repository,
            FileMapper fileMapper,
            @Value("${file.upload-dir}") String uploadDir) {
        this.repository = repository;
        this.fileMapper = fileMapper;
        this.fileStorageLocation = Paths.get(uploadDir).toAbsolutePath().normalize();

        try {
            Files.createDirectories(this.fileStorageLocation);
        } catch (IOException ex) {
            throw new CustomException("Could not create the directory where the uploaded files will be stored.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public FileData uploadFile(MultipartFile file, FileData oldDocument) {
        if (oldDocument != null) {
            deleteFile(oldDocument.getName());
        }
        return uploadFile(file);
    }

    @Override
    public FileData uploadFile(MultipartFile file) {
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

            return repository.save(fileData);
        } catch (IOException ex) {
            throw new CustomException("Could not store file " + originalFileName + ". Please try again!", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public void downloadFile(Long id, HttpServletResponse response) {
        FileData fileData = repository.findById(id)
                .orElseThrow(() -> new NotFoundException("File not found with id: " + id, HttpStatus.NOT_FOUND));

        Path filePath = this.fileStorageLocation.resolve(fileData.getName());
        if (!Files.exists(filePath)) {
            throw new NotFoundException("File not found on disk: " + fileData.getName(), HttpStatus.NOT_FOUND);
        }

        try {
            response.setContentType(fileData.getType());
            response.setHeader("Content-Disposition", "attachment; filename=\"" + fileData.getName() + "\"");
            Files.copy(filePath, response.getOutputStream());
            response.getOutputStream().flush();
        } catch (IOException ex) {
            throw new CustomException("Could not download file " + fileData.getName(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public void getFileById(Long id, HttpServletResponse response) {
        FileData fileData = repository.findById(id)
                .orElseThrow(() -> new NotFoundException("File not found with id: " + id, HttpStatus.NOT_FOUND));

        Path filePath = this.fileStorageLocation.resolve(fileData.getName());
        if (!Files.exists(filePath)) {
            throw new NotFoundException("File not found on disk: " + fileData.getName(), HttpStatus.NOT_FOUND);
        }

        try {
            response.setContentType(fileData.getType());
            response.setHeader("Content-Disposition", "inline; filename=\"" + fileData.getName() + "\"");
            Files.copy(filePath, response.getOutputStream());
            response.getOutputStream().flush();
        } catch (IOException ex) {
            throw new CustomException("Could not get file " + fileData.getName(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public void deleteFile(String fileName) {
        FileData fileData = repository.findByName(fileName);
//                .orElseThrow(() -> new NotFoundException("File not found in database: " + fileName, HttpStatus.NOT_FOUND));

        Path filePath = this.fileStorageLocation.resolve(fileName);
        if (!Files.exists(filePath)) {
            throw new NotFoundException("File not found on disk: " + fileName, HttpStatus.NOT_FOUND);
        }

        try {
            Files.deleteIfExists(filePath);
            repository.delete(fileData);
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
