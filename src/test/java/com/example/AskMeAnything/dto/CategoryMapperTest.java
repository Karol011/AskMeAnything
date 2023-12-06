package com.example.AskMeAnything.dto;

import com.example.AskMeAnything.entity.Category;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CategoryMapperTest {

    @Test
    void shouldReturnEntity() {
        Long categoryId = 1L;
        CategoryDto categoryDto = new CategoryDto();
        categoryDto.setId(categoryId);
        categoryDto.setName("Test Category");
        CategoryMapper categoryMapper = new CategoryMapper();

        Category category = categoryMapper.toEntity(categoryDto);

        assertNotNull(category);
        assertEquals(categoryDto.getId(), category.getId());
        assertEquals(categoryDto.getName(), category.getName());

    }

    @Test
    void shouldReturnDTO() {

        Long categoryId = 1L;
        Category category = new Category();
        category.setId(categoryId);
        category.setName("Test Category");
        CategoryMapper categoryMapper = new CategoryMapper();

        CategoryDto categoryDto = categoryMapper.toDto(category);

        assertNotNull(categoryDto);
        assertEquals(category.getId(), categoryDto.getId());
        assertEquals(category.getName(), categoryDto.getName());
    }
}