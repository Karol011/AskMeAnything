package com.example.AskMeAnything.service;

import com.example.AskMeAnything.entity.Category;
import com.example.AskMeAnything.repository.CategoryRepository;
import com.example.AskMeAnything.exception.CategoryNotFoundException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CategoryServiceTest {

    @Mock
    private CategoryRepository categoryRepository;
    @InjectMocks
    private CategoryService categoryService;

    @Test
    void shouldFindDById() {
        // Given
        Long categoryId = 1L;
        Category category = new Category();
        category.setId(categoryId);
        category.setName("Test Category");

        // Mock behavior of the repository method findById
        when(categoryRepository.findById(categoryId)).thenReturn(Optional.of(category));

        // When
        Category foundCategory = categoryService.findDById(categoryId);

        // Then
        assertEquals(categoryId, foundCategory.getId());
        assertEquals("Test Category", foundCategory.getName());

        // Verify that the repository method was called once with the given ID
        verify(categoryRepository, times(1)).findById(categoryId);
    }

    @Test
    public void shouldThrowCategoryNotFoundExceptionWhenFindingById() {
        // Given
        Long categoryId = 1L;

        // Mock behavior of the repository method findById
        when(categoryRepository.findById(categoryId)).thenReturn(Optional.empty());

        // When-Then
        // Ensure that CategoryNotFoundException is thrown when the category is not found
        assertThrows(CategoryNotFoundException.class, () -> categoryService.findDById(categoryId));

        // Verify that the repository method was called once with the given ID
        verify(categoryRepository, times(1)).findById(categoryId);
    }
}