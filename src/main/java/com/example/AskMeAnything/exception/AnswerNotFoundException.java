package com.example.AskMeAnything.exception;

public class AnswerNotFoundException extends RuntimeException{
    public AnswerNotFoundException(String message) {
        super(message);
    }
}
