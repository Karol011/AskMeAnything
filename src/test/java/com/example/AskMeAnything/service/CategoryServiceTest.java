package com.example.AskMeAnything.service;

import com.example.AskMeAnything.dto.CategoryDto;
import com.example.AskMeAnything.dto.CategoryMapper;
import com.example.AskMeAnything.entity.Category;
import com.example.AskMeAnything.repository.CategoryRepository;
import com.example.AskMeAnything.exception.CategoryNotFoundException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.stubbing.OngoingStubbing;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CategoryServiceTest {

    @Mock
    private CategoryRepository categoryRepository;
    @Mock
    private CategoryMapper categoryMapper;
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

    @Test
    public void shouldCreateCategory() {
        Long categoryId = 1L;
        Category category = new Category();
        category.setId(categoryId);
        category.setName("Test Category");

        CategoryDto categoryDto = CategoryDto.builder()
                .id(1L)
                .name("Test Category")
                .build();

        when(categoryMapper.toEntity(categoryDto)).thenReturn(category);
        when(categoryMapper.toDto(category)).thenReturn(categoryDto);
        when(categoryRepository.save(category)).thenReturn(category);

        // When
        CategoryDto createdCategory = categoryService.createCategory(categoryDto);

        // Then
        assertEquals(categoryDto, createdCategory);

        // Verify that methods were called
        verify(categoryMapper, times(1)).toEntity(categoryDto);
        verify(categoryMapper, times(1)).toDto(category);
        verify(categoryRepository, times(1)).save(category);
    }

}