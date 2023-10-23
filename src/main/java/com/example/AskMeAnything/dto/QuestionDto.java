package com.example.AskMeAnything.dto;

import com.example.AskMeAnything.entity.Category;
import com.example.AskMeAnything.entity.User;
import lombok.Data;


@Data
public class QuestionDto {
    private Category category;
    private User user;
    private String text;
}

