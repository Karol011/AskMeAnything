package com.example.AskMeAnything.entity;

import jakarta.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.lang.NonNull;

@Entity
@Data
@AllArgsConstructor
@Table(name = "\"User\"")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NonNull
    private long id;
    @NonNull
    private String username;
    private String password;
    private String email;


}




