package com.example.AskMeAnything.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.time.LocalDateTime;

@Entity
@Data
public class Question {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    @JoinColumn(name = "category_id")
    @JsonIgnore
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Category category;

    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonBackReference
    @OnDelete(action = OnDeleteAction.CASCADE)
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
