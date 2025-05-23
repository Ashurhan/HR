package com.example.HR.controller;

import com.example.HR.entity.models.FileData;
import com.example.HR.service.FileDataService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.constraints.NotNull;

@RestController
@RequestMapping("/api/v1/files")
@CrossOrigin(origins = "*", maxAge = 3600)
@Tag(name = "File Management", description = "APIs for managing file uploads and downloads")
public class FileDataController {

    private final FileDataService fileDataService;

    public FileDataController(FileDataService fileDataService) {
        this.fileDataService = fileDataService;
    }

    @PostMapping("/upload")
    @Operation(summary = "Upload a new file", description = "Uploads a new file to the system")
    public ResponseEntity<FileData> uploadFile(
            @RequestParam("file") @NotNull MultipartFile file) {
        return ResponseEntity.ok(fileDataService.uploadFile(file));
    }

    @PostMapping("/upload/update")
    @Operation(summary = "Update an existing file", description = "Updates an existing file with a new version")
    public ResponseEntity<FileData> updateFile(
            @RequestParam("file") @NotNull MultipartFile file,
            @RequestParam("oldDocument") @NotNull FileData oldDocument) {
        return ResponseEntity.ok(fileDataService.uploadFile(file, oldDocument));
    }

    @GetMapping("/download/{id}")
    @Operation(summary = "Download a file", description = "Downloads a file by its ID")
    public void downloadFile(
            @PathVariable Long id,
            HttpServletResponse response) {
        fileDataService.downloadFile(id, response);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get file by ID", description = "Retrieves a file by its ID")
    public void getFileById(
            @PathVariable Long id,
            HttpServletResponse response) {
        fileDataService.getFileById(id, response);
    }
} 