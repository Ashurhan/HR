package com.example.HR.mapper.impl;

import com.example.HR.dto.category.CategoryRequest;
import com.example.HR.dto.category.CategoryResponse;
import com.example.HR.entity.models.Category;
import com.example.HR.mapper.CategoryMapper;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class CategoryMapperImpl implements CategoryMapper {

    @Override
    public CategoryResponse toDto(Category category) {
        if (category == null) {
            return null;
        }
        CategoryResponse categoryResponse = new CategoryResponse();
        categoryResponse.setId(category.getId());
        categoryResponse.setName(category.getName());
        return categoryResponse;
    }

    @Override
    public List<CategoryResponse> toDtos(List<Category> categories) {
        List<CategoryResponse> categoryResponses = new ArrayList<>();
        for(Category category : categories) {
            categoryResponses.add(toDto(category));
        }
        return categoryResponses;
    }

    @Override
    public CategoryResponse requestToResponse(CategoryRequest categoryRequest) {
    CategoryResponse categoryResponse = new CategoryResponse();
    categoryResponse.setName(categoryRequest.getName());
    return categoryResponse;
    }


}
