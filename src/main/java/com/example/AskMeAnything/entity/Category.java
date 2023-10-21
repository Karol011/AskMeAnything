package com.example.AskMeAnything.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank(message = "This field must not be empty.")
    @Size(min = 2, max = 50)
    @Column(columnDefinition = "VARCHAR(50)")
    private String name;
    @OneToMany
    private List<Question> questions;

    public Category(String name) {
        this.name = name;
    }
}
