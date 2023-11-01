package com.example.AskMeAnything.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Data
public class Question {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "category_id")
    @JsonIgnore
    private Category category;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonBackReference
    private User user;

    @NotBlank(message = "This field must not be empty.")
    @Size(min = 2, max = 500)
    private String text;

    private LocalDateTime dateTime;

    public Question() {
        this.dateTime = LocalDateTime.now();
    }

    public Question(Category category, User userId, String text) {
        this.category = category;
        this.user = userId;
        this.text = text;
        this.dateTime = LocalDateTime.now();
    }

}
