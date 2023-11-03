package com.example.AskMeAnything.dto;

import com.example.AskMeAnything.entity.Question;
import com.example.AskMeAnything.exception.CategoryNotFoundException;
import com.example.AskMeAnything.exception.UserNotFoundException;
import com.example.AskMeAnything.repository.CategoryRepository;
import com.example.AskMeAnything.repository.UserRepository;
import com.example.AskMeAnything.service.CategoryService;
import com.example.AskMeAnything.service.UserService;
import org.springframework.stereotype.Component;

@Component
public class QuestionMapper {

    private final CategoryService categoryService;
    private final UserService userService;

    public QuestionMapper(CategoryService categoryService, UserService userService) {
        this.categoryService = categoryService;
        this.userService = userService;
    }

    public Question toEntity(QuestionDto questionDto) {
        Question question = new Question();

        question.setCategory(categoryService.findDById(questionDto.getCategoryId()));
        question.setUser(userService.findById(questionDto.getUserId()));
        question.setText(questionDto.getText());
        question.setDateTime(questionDto.getLocalDateTime());
        return question;
    }

    public QuestionDto toDto(Question question) {
        QuestionDto questionDto = new QuestionDto();

        questionDto.setCategoryId(question.getCategory().getId());
        questionDto.setUserId(question.getId());
        questionDto.setText(question.getText());
        questionDto.setLocalDateTime(question.getDateTime());
        return questionDto;
    }

}
