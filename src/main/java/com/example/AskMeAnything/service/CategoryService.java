package com.example.AskMeAnything.service;


import com.example.AskMeAnything.dto.CategoryDto;
import com.example.AskMeAnything.dto.CategoryMapper;
import com.example.AskMeAnything.dto.QuestionDto;
import com.example.AskMeAnything.entity.Category;
import com.example.AskMeAnything.entity.User;
import com.example.AskMeAnything.exception.CategoryNotFoundException;
import com.example.AskMeAnything.repository.CategoryRepository;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

    public ResponseEntity<CategoryDto> findById(Long id) {
        Category category = getCategoryRepository()
                .findById(id)
                .orElseThrow(() -> new CategoryNotFoundException("Category with id " + id + " not found"));

        return new ResponseEntity<>(categoryMapper.toDto(category),
                HttpStatus.OK);
    }

    public ResponseEntity<List<CategoryDto>> findAll() {
        List<CategoryDto> list = getCategoryRepository().findAll()
                .stream()
                .map(categoryMapper::toDto)
                .toList();
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    public ResponseEntity<CategoryDto> createCategory(CategoryDto categoryDto) {

        Category category = categoryMapper.toEntity(categoryDto);
        categoryRepository.save(category);

        return new ResponseEntity<>(categoryMapper.toDto(category), HttpStatus.CREATED);
    }

    public ResponseEntity<Object> deleteCategory(Long id) {
        Optional<Category> searchedCategory = getCategoryRepository().findById(id);

        if (searchedCategory.isPresent()) {
            Category deletedCategory = searchedCategory.get();
            getCategoryRepository().deleteById(id);
            return ResponseEntity.ok(categoryMapper.toDto(deletedCategory));
        }
        String notFoundMessage = String.format("Category with id %d not found", id);
        return new ResponseEntity<>(notFoundMessage, HttpStatus.NOT_FOUND);
    }
}

