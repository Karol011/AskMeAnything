package com.example.AskMeAnything.dto;

import com.example.AskMeAnything.entity.Question;

public class QuestionMapper {

    public static Question mapQuestionDTOToEntity(QuestionDto questionDto) {
        Question question = new Question();
        question.setCategory(questionDto.getCategory());
        question.setUserId(questionDto.getUser());
        question.setText(questionDto.getText());
        return question;
    }
}
