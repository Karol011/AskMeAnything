package com.example.AskMeAnything.entity;

import jakarta.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.lang.NonNull;

@Entity
@Data

@Table(name = "\"User\"")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NonNull
    private long id;
    @NonNull
    private String name;
    private String password;
    private String email;

    public User(@NonNull String name, String password, String email) {
        this.name = name;
        this.password = password;
        this.email = email;
    }
}




