package com.example.HR.mapper;

import com.example.HR.dto.category.CategoryRequest;
import com.example.HR.dto.category.CategoryResponse;
import com.example.HR.entity.models.Category;

import java.util.List;

public interface CategoryMapper {
    CategoryResponse toDto(Category category);

    List<CategoryResponse> toDtos(List<Category> categories);

    CategoryResponse requestToResponse(CategoryRequest categoryRequest);
}
