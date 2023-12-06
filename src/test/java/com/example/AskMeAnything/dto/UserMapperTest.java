package com.example.AskMeAnything.dto;

import com.example.AskMeAnything.entity.Category;
import com.example.AskMeAnything.entity.Question;
import com.example.AskMeAnything.entity.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class UserMapperTest {

    @Test
    void shouldReturnEntity() {
        Long categoryId = 1L;
        Category category = new Category();
        category.setId(categoryId);
        category.setName("Test Category");

        Long userId = 1L;
        String password = "password123";
        String name = "karolek";
        String email = "randomemail@123.pl";

        UserDto userDto = new UserDto();
        userDto.setId(userId);
        userDto.setName(name);
        userDto.setPassword(password);
        userDto.setEmail(email);

        Question question = new Question();
        question.setId(1L);
        question.setText("random");
        question.setCategory(category);

        userDto.setQuestions(List.of(question));

        UserMapper userMapper = new UserMapper();
        User user = userMapper.toEntity(userDto);

        assertNotNull(user);
        Assertions.assertEquals(userDto.getId(), user.getId());
        Assertions.assertEquals(userDto.getName(), user.getName());
        Assertions.assertEquals(userDto.getPassword(), user.getPassword());
        Assertions.assertEquals(userDto.getEmail(), user.getEmail());
        Assertions.assertEquals(userDto.getQuestions(), user.getQuestions());

    }

    @Test
    void testToEntity() {
    }

    @Test
    void shouldReturnDto() {
        Long categoryId = 1L;
        Category category = new Category();
        category.setId(categoryId);
        category.setName("Test Category");

        Long userId = 1L;
        String password = "password123";
        String name = "karolek";
        String email = "randomemail@123.pl";

        User user = new User();
        user.setId(userId);
        user.setName(name);
        user.setPassword(password);
        user.setEmail(email);

        Question question = new Question();
        question.setUser(user);
        question.setId(1L);
        question.setText("random");
        question.setCategory(category);

        user.setQuestions(List.of(question));

        UserMapper userMapper = new UserMapper();
        UserDto userDto = userMapper.toDto(user);

        assertNotNull(userDto);
        Assertions.assertEquals(userDto.getId(), user.getId());
        Assertions.assertEquals(userDto.getName(), user.getName());
        Assertions.assertEquals(userDto.getPassword(), user.getPassword());
        Assertions.assertEquals(userDto.getEmail(), user.getEmail());
        Assertions.assertEquals(userDto.getQuestions(), user.getQuestions());
    }
}