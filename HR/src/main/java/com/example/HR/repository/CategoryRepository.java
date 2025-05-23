package com.example.HR.repository;

import com.example.HR.entity.models.Category;
import com.example.HR.entity.models.Vacancy;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CategoryRepository extends JpaRepository<Category, Long> {
    Category findByName(String name);
    Category findById(long id);
    List<Vacancy> findByVacancies(Category category);
}
