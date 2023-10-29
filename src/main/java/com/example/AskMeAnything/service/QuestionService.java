package com.example.AskMeAnything.service;

import com.example.AskMeAnything.dto.QuestionDto;
import com.example.AskMeAnything.dto.QuestionMapper;
import com.example.AskMeAnything.entity.Category;
import com.example.AskMeAnything.entity.Question;
import com.example.AskMeAnything.entity.User;
import com.example.AskMeAnything.exception.CategoryNotFoundException;
import com.example.AskMeAnything.exception.QuestionNotFoundException;
import com.example.AskMeAnything.exception.UserNotFoundException;
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
    private final QuestionMapper questionMapper;

    public QuestionService(QuestionRepository questionRepository, CategoryRepository categoryRepository, UserRepository userRepository, QuestionMapper questionMapper) {
        this.questionRepository = questionRepository;
        this.categoryRepository = categoryRepository;
        this.userRepository = userRepository;
        this.questionMapper = questionMapper;
    }

    public ResponseEntity<QuestionDto> findById(Long id) {
        Question question = getQuestionRepository()
                .findById(id)
                .orElseThrow(() -> new QuestionNotFoundException("Question with id " + id + " not found"));
        return new ResponseEntity<>(questionMapper.toDto(question),
                HttpStatus.OK);
    }

    public ResponseEntity<List<QuestionDto>> findAll() {

        List<QuestionDto> list = getQuestionRepository().findAll()
                .stream()
                .map(questionMapper::toDto)
                .toList();
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    public ResponseEntity<QuestionDto> createQuestion(QuestionDto questionDto) {

        Category category = categoryRepository.findById(questionDto.getCategoryId()).orElseThrow(() -> new CategoryNotFoundException("Category not found"));
        User user = userRepository.findById(questionDto.getUserId()).orElseThrow(() -> new UserNotFoundException("Question not found"));

        Question question = new Question();
        question.setUser(user);
        question.setCategory(category);
        question.setText(questionDto.getText());
        questionRepository.save(question);

        category.getQuestions().add(question);
        categoryRepository.save(category);



        return new ResponseEntity<>(questionDto, HttpStatus.CREATED);
    }

    public ResponseEntity<Object> deleteQuestion(Long id) {
        Optional<Question> searchedQuestion = getQuestionRepository().findById(id);

        if (searchedQuestion.isPresent()) {
            Question deletedQuestion = searchedQuestion.get();
            getQuestionRepository().deleteById(id);
            return ResponseEntity.ok(questionMapper.toDto(deletedQuestion));
        }
        String notFoundMessage = String.format("Question with id %d not found", id);
        return new ResponseEntity<>(notFoundMessage, HttpStatus.NOT_FOUND);
    }
}
