package com.example.AskMeAnything.controller;

import com.example.AskMeAnything.dto.QuestionDto;
import com.example.AskMeAnything.entity.Category;
import com.example.AskMeAnything.entity.Question;
import com.example.AskMeAnything.exception.CategoryNotFoundException;
import com.example.AskMeAnything.exception.QuestionNotFoundException;
import com.example.AskMeAnything.service.CategoryService;
import com.example.AskMeAnything.service.QuestionService;
import jakarta.validation.Valid;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/question")
@Getter
@Validated
public class QuestionController {

    private final QuestionService questionService;

    public QuestionController(QuestionService questionService) {
        this.questionService = questionService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<QuestionDto> findCategoryById(@PathVariable Long id) {
        return getQuestionService().findById(id);
    }

    @GetMapping()
    public ResponseEntity<List<QuestionDto>> getAllQuestion() {
        return getQuestionService().findAll();
    }

    @PostMapping
    public ResponseEntity<QuestionDto> createQuestion(@Valid @RequestBody QuestionDto questionDto) {

        return getQuestionService().createQuestion(questionDto);
    }

    @PatchMapping("{questionId}/category/{categoryId}")
    public ResponseEntity<QuestionDto> updateQuestionCategory(@PathVariable Long questionId, @PathVariable Long categoryId) {
        return questionService.updateQuestionCategory(questionId, categoryId);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Object> deleteQuestion(@PathVariable Long id) {
        return getQuestionService().deleteQuestion(id);
    }


}
