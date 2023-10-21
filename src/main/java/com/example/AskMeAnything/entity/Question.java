package com.example.AskMeAnything.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
public class Question {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @NotNull
    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;
    @NotNull
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User userId;
    @NotBlank(message = "This field must not be empty.")
    @Size(min = 2, max = 500)
    private String text;
    private LocalDateTime dateTime;


}
