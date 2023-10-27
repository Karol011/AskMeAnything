package com.example.AskMeAnything.dto;

import lombok.Data;


@Data
public class QuestionDto {
    private Long categoryId;
    private Long userId;
    private String text;
}

