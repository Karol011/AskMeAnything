package com.example.AskMeAnything.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class ExceptionResponse {

    private final HttpStatus status;


    private final String message;

    public ExceptionResponse(HttpStatus status, String message) {
        this.status = status;
        this.message = message;
    }

    public ExceptionResponse(HttpStatus status, Throwable ex) {
        this.status = status;
        this.message = "Unexpected error " + ex.getLocalizedMessage();
    }

}