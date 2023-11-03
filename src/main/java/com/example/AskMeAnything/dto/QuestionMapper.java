package com.example.AskMeAnything.dto;

import com.example.AskMeAnything.entity.Question;
import com.example.AskMeAnything.exception.CategoryNotFoundException;
import com.example.AskMeAnything.exception.UserNotFoundException;
import com.example.AskMeAnything.repository.CategoryRepository;
import com.example.AskMeAnything.repository.QuestionRepository;
import com.example.AskMeAnything.repository.UserRepository;
import org.springframework.boot.json.JacksonJsonParser;
import org.springframework.stereotype.Component;

@Component
public class QuestionMapper {

    private final CategoryRepository categoryRepository;
    private final UserRepository userRepository;
    private final QuestionRepository questionRepository;

    public QuestionMapper(CategoryRepository categoryRepository, UserRepository userRepository, QuestionRepository questionRepository) {
        this.categoryRepository = categoryRepository;
        this.userRepository = userRepository;
        this.questionRepository = questionRepository;
    }

    public Question toEntity(QuestionDto questionDto) {
        Question question = new Question();

        question.setCategory(categoryRepository.findById(questionDto.getCategoryId()).orElseThrow(() -> new CategoryNotFoundException("Category with id not found")));
        question.setUser(userRepository.findById(question.getId()).orElseThrow(() -> new UserNotFoundException("User with id not found")));
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
