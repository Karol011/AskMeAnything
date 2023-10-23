package com.example.AskMeAnything.controller;

import com.example.AskMeAnything.dto.QuestionDto;
import com.example.AskMeAnything.entity.Question;
import com.example.AskMeAnything.service.QuestionService;
import jakarta.validation.Valid;
import lombok.Getter;
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
    public ResponseEntity<Question> findCategoryById(@PathVariable Long id) {
        return getQuestionService().findById(id);
    }

    @GetMapping()
    public ResponseEntity<List<Question>> getAllQuestion() {
        return getQuestionService().findAll();
    }


    @PostMapping
    public ResponseEntity<Question> createQuestion(@Valid @RequestBody QuestionDto questionDto) {

        return getQuestionService().createQuestion(questionDto);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Object> deleteQuestion(@PathVariable Long id) {
        return getQuestionService().deleteQuestion(id);
    }


}
