package com.example.AskMeAnything.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UserDto {


    @NotBlank(message = "This field must not be empty.")
    @Size(min = 2, max = 50)
    private String name;
    private String password;
    @NotNull
    @Email(message = "Invalid email format. Please provide a valid email address.")
    private String email;

    public UserDto(String name, String password, String email) {
        this.name = name;
        this.password = password;
        this.email = email;
    }
}
