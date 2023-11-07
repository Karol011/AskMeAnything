package com.example.AskMeAnything.service;


import com.example.AskMeAnything.dto.CategoryDto;
import com.example.AskMeAnything.dto.CategoryMapper;
import com.example.AskMeAnything.entity.Category;
import com.example.AskMeAnything.exception.CategoryNotFoundException;
import com.example.AskMeAnything.repository.CategoryRepository;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Getter
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;

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

    public CategoryDto updateCategory(Long id, CategoryDto categoryDto) {
        Category category = findDById(id);

        category.setName(categoryDto.getName());
        category.setQuestions(categoryDto.getQuestions());

        this.getCategoryRepository().save(category);
        return getCategoryMapper().toDto(category);
    }

    public HttpStatus deleteCategory(Long id) {
        Optional<Category> searchedCategory = getCategoryRepository().findById(id);
        HttpStatus httpStatus;

        if (searchedCategory.isPresent()) {
            getCategoryRepository().deleteById(id);
            httpStatus = HttpStatus.OK;
        } else {
            String notFoundMessage = String.format("Category with id %d not found", id);
            httpStatus = HttpStatus.NOT_FOUND;
            throw new CategoryNotFoundException(notFoundMessage);
        }
        return httpStatus;
    }

    public HttpStatus deleteAll() {
        HttpStatus httpStatus;
        if (getCategoryRepository() != null) {
            getCategoryRepository().deleteAll();
            httpStatus = HttpStatus.OK;
        } else {
            httpStatus = HttpStatus.NOT_FOUND;
        }
        return httpStatus;
    }


}

