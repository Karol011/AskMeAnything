package com.example.AskMeAnything.dto;

import com.example.AskMeAnything.entity.Question;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
public class CategoryDto {

    private Long id;

    @NotBlank(message = "This field must not be empty.")
    @Size(min = 2, max = 50)
    private String name;

    private List<Question> questions;

    public CategoryDto(Long id, String name, List<Question> questions) {
        this.id = id;
        this.name = name;
        this.questions = questions;
    }
}
