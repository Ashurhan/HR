package com.example.HR.mapper;

import com.example.HR.dto.admin.ResponsesForSupport;
import com.example.HR.entity.models.ListSupport;

import java.util.List;

public interface SupportMapper {
    ResponsesForSupport toDto(ListSupport listSupport);


    List<ResponsesForSupport> toDtos(List<ListSupport> responseList);
}
