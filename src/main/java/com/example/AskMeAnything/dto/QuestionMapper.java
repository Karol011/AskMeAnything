package com.example.AskMeAnything.dto;

import com.example.AskMeAnything.entity.Category;
import com.example.AskMeAnything.entity.Question;
import com.example.AskMeAnything.entity.User;
import com.example.AskMeAnything.service.CategoryService;
import com.example.AskMeAnything.service.UserService;
import org.springframework.stereotype.Component;

@Component
public class QuestionMapper {


    public Question toEntity(QuestionDto questionDto) {
        Question question = new Question();

        question.setId(questionDto.getId());
        question.setText(questionDto.getText());
        question.setDateTime(questionDto.getLocalDateTime());
        return question;
    }

    public Question toEntity(QuestionDto questionDto, User user) {
        Question question = new Question();

        question.setId(questionDto.getId());
        question.setUser(user);
        question.setText(questionDto.getText());
        question.setDateTime(questionDto.getLocalDateTime());
        return question;
    }

    public Question toEntity(QuestionDto questionDto, Category category) {
        Question question = new Question();

        question.setId(questionDto.getId());
        question.setCategory(category);
        question.setText(questionDto.getText());
        question.setDateTime(questionDto.getLocalDateTime());
        return question;
    }

    public Question toEntity(QuestionDto questionDto, Category category, User user) {
        Question question = new Question();

        question.setId(questionDto.getId());
        question.setCategory(category);
        question.setUser(user);
        question.setText(questionDto.getText());
        question.setDateTime(questionDto.getLocalDateTime());
        return question;
    }

    public QuestionDto toDto(Question question) {
        QuestionDto questionDto = new QuestionDto();

        questionDto.setId(question.getId());
        questionDto.setCategoryId(question.getCategory().getId());
        questionDto.setUserId(question.getId());
        questionDto.setText(question.getText());
        questionDto.setLocalDateTime(question.getDateTime());
        return questionDto;
    }

}
