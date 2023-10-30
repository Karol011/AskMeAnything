package com.example.AskMeAnything.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<Object> handleUserNotFoundException(UserNotFoundException exception) {
        return buildResponseEntity(new ExceptionResponse(HttpStatus.NOT_FOUND, exception.getMessage()));
    }

    @ExceptionHandler(CategoryNotFoundException.class)
    public ResponseEntity<Object> handleCategoryNotFoundException(CategoryNotFoundException exception) {
        return buildResponseEntity(new ExceptionResponse(HttpStatus.NOT_FOUND, exception.getMessage()));
    }
    @ExceptionHandler(QuestionNotFoundException.class)
    public ResponseEntity<Object> handleQuestionNotFoundException(QuestionNotFoundException exception) {
        return buildResponseEntity(new ExceptionResponse(HttpStatus.NOT_FOUND, exception.getMessage()));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Object> handleValidationExceptions(MethodArgumentNotValidException ex) {
        // Create a list to store validation error messages.
        var errors = ex.getBindingResult().getFieldErrors().stream()
                .map(error -> "Validation error in field '" + error.getField() + "': " + error.getDefaultMessage())
                .toList();
        // Return a Bad Request response with the validation error messages.
        return ResponseEntity.badRequest().body(errors);
    }

    private ResponseEntity<Object> buildResponseEntity(ExceptionResponse exceptionResponse) {
        return new ResponseEntity<>(exceptionResponse, exceptionResponse.getStatus());
    }


}
