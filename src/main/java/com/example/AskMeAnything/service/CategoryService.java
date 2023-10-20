package com.example.AskMeAnything.service;


import com.example.AskMeAnything.entity.Category;
import com.example.AskMeAnything.entity.User;
import com.example.AskMeAnything.exception.CategoryNotFoundException;
import com.example.AskMeAnything.exception.UserNotFoundException;
import com.example.AskMeAnything.repository.CategoryRepository;
import com.example.AskMeAnything.repository.UserRepository;
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

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }


    public ResponseEntity<Category> findById(Long id) {
        Category category = getCategoryRepository()
                .findById(id)
                .orElseThrow(() -> new CategoryNotFoundException("Category with id " + id + " not found"));
        return new ResponseEntity<>(category,
                HttpStatus.OK);
    }

    public ResponseEntity<List<Category>> findAll() {
        return new ResponseEntity<>(getCategoryRepository().findAll(), HttpStatus.OK);
    }

    public ResponseEntity<Category> createCategory(Category newCategory) {

        Category category = new Category(
                newCategory.getName()
        );
        categoryRepository.save(category);

        return new ResponseEntity<>(category, HttpStatus.CREATED);
    }

    public ResponseEntity<Object> deleteCategory(Long id) {
        Optional<Category> searchedCategory = getCategoryRepository().findById(id);

        if (searchedCategory.isPresent()) {
            Category deletedCategory = searchedCategory.get();
            getCategoryRepository().deleteById(id);
            return ResponseEntity.ok(deletedCategory);
        }
        String notFoundMessage = String.format("Category with id %d not found", id);
        return new ResponseEntity<>(notFoundMessage, HttpStatus.NOT_FOUND);
    }
}

