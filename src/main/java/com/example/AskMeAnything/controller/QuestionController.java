package com.example.AskMeAnything.controller;

import com.example.AskMeAnything.dto.QuestionDto;
import com.example.AskMeAnything.service.QuestionService;
import jakarta.validation.Valid;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/questions")
@Getter
@Validated
public class QuestionController {

    private final QuestionService questionService;

    public QuestionController(QuestionService questionService) {
        this.questionService = questionService;
    }

    //GET
    @GetMapping("/{id}")
    public ResponseEntity<QuestionDto> findQuestionById(@PathVariable Long id) {
        return new ResponseEntity<>(getQuestionService().findDtoById(id), HttpStatus.OK);
    }

    @GetMapping()
    public ResponseEntity<List<QuestionDto>> getAllQuestion() {
        return new ResponseEntity<>(getQuestionService().findAll(), HttpStatus.OK);
    }

    @GetMapping("/category/{id}")
    public ResponseEntity<List<QuestionDto>> getQuestionsByCategory(@PathVariable Long id) {
        return new ResponseEntity<>(getQuestionService().getQuestionsByCategory(id), HttpStatus.OK);
    }

    //POST
    @PostMapping("/users/{userId}")
    public ResponseEntity<QuestionDto> createQuestion(@Valid @RequestBody QuestionDto questionDto, @PathVariable Long userId) {
        return new ResponseEntity<>(getQuestionService().createQuestion(questionDto, userId), HttpStatus.CREATED);
    }

    //PUT
    @PutMapping("/{questionId}")
    public ResponseEntity<QuestionDto> updateQuestion(@PathVariable Long questionId, @RequestBody QuestionDto questionDto) {
        return new ResponseEntity<>(questionService.updateQuestion(questionId, questionDto), HttpStatus.OK);
    }


    //PATCH
    @PatchMapping("{questionId}/category/{categoryId}")
    public ResponseEntity<QuestionDto> updateQuestionCategory(@PathVariable Long questionId, @PathVariable Long categoryId) {
        return new ResponseEntity<>(questionService.updateQuestionCategory(questionId, categoryId), HttpStatus.OK);
    }


    //DELETE
    @DeleteMapping("{id}")
    public ResponseEntity<Object> deleteQuestion(@PathVariable Long id) {
        return new ResponseEntity<>(getQuestionService().deleteQuestion(id), HttpStatus.OK);
    }


}
