package com.example.AskMeAnything.dto;

import com.example.AskMeAnything.entity.Question;
import com.example.AskMeAnything.exception.CategoryNotFoundException;
import com.example.AskMeAnything.exception.UserNotFoundException;
import com.example.AskMeAnything.repository.CategoryRepository;
import com.example.AskMeAnything.repository.UserRepository;
import org.springframework.stereotype.Component;

@Component
public class QuestionMapper {

    private final CategoryRepository categoryRepository;
    private final UserRepository userRepository;

    public QuestionMapper(CategoryRepository categoryRepository, UserRepository userRepository) {
        this.categoryRepository = categoryRepository;
        this.userRepository = userRepository;
    }

    public Question mapQuestionDTOToEntity(QuestionDto questionDto) {
        Question question = new Question();
        question.setCategory(categoryRepository.findById(questionDto.getCategoryId()).orElseThrow(() -> new CategoryNotFoundException("Category with id not found")));
        question.setUserId(userRepository.findById(question.getId()).orElseThrow(()-> new UserNotFoundException("User with id not found")));
        question.setText(questionDto.getText());
        return question;
    }

}
