package com.example.AskMeAnything.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.time.LocalDateTime;


@Data
public class QuestionDto {

    private long id;
    private Long categoryId;
    private Long userId;
    @NotNull(message = "This field must not be empty.")
    @NotBlank(message = "This field must not be empty.")
    @Size(min = 2, max = 500, message = "Size must be between 2 and 500 characters")
    private String text;
    private LocalDateTime localDateTime = LocalDateTime.now();
}

