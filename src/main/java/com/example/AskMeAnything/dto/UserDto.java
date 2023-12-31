package com.example.AskMeAnything.dto;

import com.example.AskMeAnything.entity.Question;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class UserDto {

    private Long id;
    @NotBlank(message = "This field must not be empty.")
    @Size(min = 2, max = 50, message = "Size must be between 2 and 50 characters")
    private String name;
    private String password;
    @NotNull(message = "This field must not be empty.")
    @Email(message = "Invalid email format. Please provide a valid email address.")
    private String email;


    private List<Question> questions;

}
