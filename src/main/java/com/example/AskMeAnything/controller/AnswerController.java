package com.example.AskMeAnything.controller;

import com.example.AskMeAnything.dto.CategoryDto;
import com.example.AskMeAnything.entity.Answer;
import com.example.AskMeAnything.service.AnswerService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/answer")
@Getter
@Validated
@RequiredArgsConstructor
public class AnswerController {

    private final AnswerService answerService;

    @GetMapping("/{id}")
    public ResponseEntity<Answer> findAnswerById(@PathVariable Long id) {
        return new ResponseEntity<>(getAnswerService().findById(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Answer> createAnswer(@RequestBody Answer answer) {
        return new ResponseEntity<>(getAnswerService().createAnswer(answer), HttpStatus.CREATED);
    }
}
