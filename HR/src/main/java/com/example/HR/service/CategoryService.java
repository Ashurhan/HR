package com.example.HR.service;

import com.example.HR.dto.category.CategoryRequest;
import com.example.HR.dto.category.CategoryResponse;

import java.util.List;

public interface CategoryService {
    List<CategoryResponse> getAllCategory();

    CategoryResponse getCategoryById(Long id);

    CategoryResponse createCategory(CategoryRequest categoryRequest);

    void deleteCategoryById(Long id);

    CategoryResponse updateCategory(Long id, CategoryRequest categoryRequest);

    void getAllUsersMessages(String token, String email);
}
