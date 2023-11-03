package com.example.AskMeAnything.service;


import com.example.AskMeAnything.dto.CategoryDto;
import com.example.AskMeAnything.dto.CategoryMapper;
import com.example.AskMeAnything.entity.Category;
import com.example.AskMeAnything.exception.CategoryNotFoundException;
import com.example.AskMeAnything.repository.CategoryRepository;
import lombok.Getter;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Getter
public class CategoryService {

    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;

    public CategoryService(CategoryRepository categoryRepository, CategoryMapper categoryMapper) {
        this.categoryRepository = categoryRepository;
        this.categoryMapper = categoryMapper;
    }

    public CategoryDto findDtoById(Long id) {
        Category category = getCategoryRepository()
                .findById(id)
                .orElseThrow(() -> new CategoryNotFoundException("Category with id " + id + " not found"));
        return categoryMapper.toDto(category);
    }

    public Category findDById(Long id) {
        Category category = getCategoryRepository()
                .findById(id)
                .orElseThrow(() -> new CategoryNotFoundException("Category with id " + id + " not found"));
        return category;
    }



    public List<CategoryDto> findAll() {
        List<CategoryDto> list = getCategoryRepository().findAll()
                .stream()
                .map(categoryMapper::toDto)
                .toList();
        return list;
    }

    public CategoryDto createCategory(CategoryDto categoryDto) {

        Category category = categoryMapper.toEntity(categoryDto);
        categoryRepository.save(category);

        return categoryMapper.toDto(category);
    }

    public void deleteCategory(Long id) {
        Optional<Category> searchedCategory = getCategoryRepository().findById(id);

        if (searchedCategory.isPresent()) {
            getCategoryRepository().deleteById(id);
        } else {
            String notFoundMessage = String.format("Category with id %d not found", id);
            throw new CategoryNotFoundException(notFoundMessage);
        }

    }
}

