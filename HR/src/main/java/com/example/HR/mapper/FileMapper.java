package com.example.HR.mapper;

import java.util.List;

import com.example.HR.entity.models.FileData;
import com.example.HR.dto.file.FileResponse;

public interface FileMapper {
    FileResponse toDto(FileData fileData);
    List<FileResponse> toDtos(List<FileData> files);

}
