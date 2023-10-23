package com.example.AskMeAnything.exception;

public class QuestionNotFoundException extends RuntimeException{

    public QuestionNotFoundException(String message) {
        super(message);
    }

}
