package com.example.HR.service.impl;

import com.example.HR.dto.category.CategoryRequest;
import com.example.HR.dto.category.CategoryResponse;
import com.example.HR.entity.models.Category;
import com.example.HR.mapper.CategoryMapper;
import com.example.HR.repository.CategoryRepository;
import com.example.HR.repository.MessageRepository;
import com.example.HR.repository.UserRepository;
import com.example.HR.service.CategoryService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;
    private final UserRepository userRepository;
    private final MessageRepository messageRepository;

    public CategoryServiceImpl(CategoryRepository categoryRepository, CategoryMapper categoryMapper, UserRepository userRepository, MessageRepository messageRepository) {
        this.categoryRepository = categoryRepository;
        this.categoryMapper = categoryMapper;
        this.userRepository = userRepository;
        this.messageRepository = messageRepository;
    }

    @Override
    public List<CategoryResponse> getAllCategory() {
        List<CategoryResponse> categoryResponses = categoryMapper.toDtos(categoryRepository.findAll());
        return categoryResponses;
    }

    @Override
    public CategoryResponse getCategoryById(Long id) {
        CategoryResponse categoryResponse = categoryMapper.toDto(categoryRepository.findById(id).get());
        return categoryResponse;
    }


    @Override
    public CategoryResponse createCategory(CategoryRequest categoryRequest) {
        Category category = new Category();
        category.setName(categoryRequest.getName());
         categoryRepository.save(category);
         return new CategoryResponse(category.getId(), category.getName());
    }

    @Override
    public void deleteCategoryById(Long id) {
    Category category = categoryRepository.findById(id).get();
    categoryRepository.delete(category);
    }

    @Override
    public CategoryResponse updateCategory(Long id, CategoryRequest categoryRequest) {
        Category category = categoryRepository.findById(id).get();
        category.setName(categoryRequest.getName());
        categoryRepository.save(category);
        return new CategoryResponse(category.getId(), category.getName());
    }

    @Override
    public void getAllUsersMessages(String token, String email) {

    }
}
