package com.example.AskMeAnything.controller;

import com.example.AskMeAnything.dto.CategoryDto;
import com.example.AskMeAnything.service.CategoryService;
import jakarta.validation.Valid;
import lombok.Getter;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/category")
@Getter
@Validated
public class CategoryController {

    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoryDto> findCategoryById(@PathVariable Long id) {
        return getCategoryService().findById(id);
    }

    @GetMapping()
    public ResponseEntity<List<CategoryDto>> getAllCategories() {
        return getCategoryService().findAll();
    }


    @PostMapping
    public ResponseEntity<CategoryDto> createCategory(@Valid @RequestBody CategoryDto categoryDto) {

        return getCategoryService().createCategory(categoryDto);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Object> deleteCategory(@PathVariable Long id) {
        return getCategoryService().deleteCategory(id);
    }
}
