package com.example.AskMeAnything.service;

import com.example.AskMeAnything.dto.QuestionDto;
import com.example.AskMeAnything.dto.QuestionMapper;
import com.example.AskMeAnything.entity.Category;
import com.example.AskMeAnything.entity.Question;
import com.example.AskMeAnything.entity.User;
import com.example.AskMeAnything.exception.CategoryNotFoundException;
import com.example.AskMeAnything.exception.QuestionNotFoundException;
import com.example.AskMeAnything.repository.CategoryRepository;
import com.example.AskMeAnything.repository.QuestionRepository;
import com.example.AskMeAnything.repository.UserRepository;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Getter
public class QuestionService {


    private final QuestionRepository questionRepository;
    private final CategoryRepository categoryRepository;
    private final UserRepository userRepository;

    public QuestionService(QuestionRepository questionRepository, CategoryRepository categoryRepository, UserRepository userRepository) {
        this.questionRepository = questionRepository;
        this.categoryRepository = categoryRepository;
        this.userRepository = userRepository;
    }

    public ResponseEntity<Question> findById(Long id) {
        Question question = getQuestionRepository()
                .findById(id)
                .orElseThrow(() -> new QuestionNotFoundException("Question with id " + id + " not found"));
        return new ResponseEntity<>(question,
                HttpStatus.OK);
    }

    public ResponseEntity<List<Question>> findAll() {
        return new ResponseEntity<>(getQuestionRepository().findAll(), HttpStatus.OK);
    }

    public ResponseEntity<Question> createQuestion(QuestionDto questionDto) {

        Category category = categoryRepository.findById(questionDto.getCategory().getId()).orElseThrow(() -> new CategoryNotFoundException("Category not found"));
        User user = userRepository.findById(questionDto.getUser().getId()).orElseThrow(() -> new QuestionNotFoundException("Question not found"));

        Question question = QuestionMapper.mapQuestionDTOToEntity(questionDto);
        questionRepository.save(question);

        return new ResponseEntity<>(question, HttpStatus.CREATED);
    }

    public ResponseEntity<Object> deleteQuestion(Long id) {
        Optional<Question> searchedQuestion = getQuestionRepository().findById(id);

        if (searchedQuestion.isPresent()) {
            Question deletedQuestion = searchedQuestion.get();
            getQuestionRepository().deleteById(id);
            return ResponseEntity.ok(deletedQuestion);
        }
        String notFoundMessage = String.format("Question with id %d not found", id);
        return new ResponseEntity<>(notFoundMessage, HttpStatus.NOT_FOUND);
    }
}
