package com.example.AskMeAnything.entity;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;

@Entity
@Data
@AllArgsConstructor
public class User {

    private long id;
    private String username;
    private String password;
    private String email;


}




