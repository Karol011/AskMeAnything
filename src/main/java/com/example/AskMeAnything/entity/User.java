package com.example.AskMeAnything.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
@Table(name = "\"User\"")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @NotBlank(message = "This field must not be empty.")
    @Size(min = 2, max = 50)
    private String name;
    private String password;
    @NotNull
    @Email(message = "Invalid email format. Please provide a valid email address.")
    private String email;
    @OneToMany
    private List<Question> questions;

    public User(String name, String password, String email) {
        this.name = name;
        this.password = password;
        this.email = email;
    }
}




