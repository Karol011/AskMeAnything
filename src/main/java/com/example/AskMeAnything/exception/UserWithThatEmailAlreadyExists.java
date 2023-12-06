package com.example.AskMeAnything.exception;

public class UserWithThatEmailAlreadyExists extends RuntimeException {

    public UserWithThatEmailAlreadyExists(String message) {
        super(message);
    }
}