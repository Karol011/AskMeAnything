package com.example.AskMeAnything.dto;

import com.example.AskMeAnything.entity.Question;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.List;

@Data
public class CategoryDto {

    private Long id;

    @NotBlank(message = "This field must not be empty.")
    @Size(min = 2, max = 50)
    private String name;

    private List<Question> questions;
}
