package com.example.AskMeAnything.controller;

import com.example.AskMeAnything.entity.Category;
import com.example.AskMeAnything.entity.User;
import com.example.AskMeAnything.service.CategoryService;
import com.example.AskMeAnything.service.UserService;
import jakarta.validation.Valid;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/category")
@Getter
@Setter
@Validated
public class CategoryController {

    private CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Category> findCategoryById(@PathVariable Long id) {
        return getCategoryService().findById(id);
    }

    @GetMapping()
    public ResponseEntity<List<Category>> getAllCategories() {
        return getCategoryService().findAll();
    }


    @PostMapping
    public ResponseEntity<Category> createCategory(@Valid @RequestBody Category category) {

        return getCategoryService().createCategory(category);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Object> deleteCategory(@PathVariable Long id) {
        return getCategoryService().deleteCategory(id);
    }
}
