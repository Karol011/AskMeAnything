package com.example.AskMeAnything.service;

import com.example.AskMeAnything.dto.CategoryDto;
import com.example.AskMeAnything.dto.QuestionDto;
import com.example.AskMeAnything.dto.QuestionMapper;
import com.example.AskMeAnything.entity.Category;
import com.example.AskMeAnything.entity.Question;
import com.example.AskMeAnything.entity.User;
import com.example.AskMeAnything.exception.QuestionNotFoundException;
import com.example.AskMeAnything.repository.QuestionRepository;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Getter
public class QuestionService {

    private final CategoryService categoryService;
    private final UserService userService;
    private final QuestionMapper questionMapper;
    private final QuestionRepository questionRepository;

    public QuestionService(CategoryService categoryService, UserService userService, QuestionMapper questionMapper, QuestionRepository questionRepository) {
        this.categoryService = categoryService;
        this.userService = userService;
        this.questionMapper = questionMapper;
        this.questionRepository = questionRepository;
    }


    public QuestionDto findDtoById(Long id) {
        Question question = getQuestionRepository()
                .findById(id)
                .orElseThrow(() -> new QuestionNotFoundException("Question with id " + id + " not found"));
        return questionMapper.toDto(question);
    }

    public Question findById(Long id) {
        Question question = getQuestionRepository()
                .findById(id)
                .orElseThrow(() -> new QuestionNotFoundException("Question with id " + id + " not found"));
        return question;
    }


    public List<QuestionDto> findAll() {

        List<QuestionDto> list = getQuestionRepository().findAll()
                .stream()
                .map(questionMapper::toDto)
                .toList();
        return list;
    }

    public List<QuestionDto> getQuestionsByCategory(Long id) {
        return getCategoryService().findDById(id)
                .getQuestions()
                .stream()
                .map(questionMapper::toDto)
                .toList();
    }

    public QuestionDto createQuestion(QuestionDto questionDto, Long userId) {

        Category category = getCategoryService().findDById(questionDto.getCategoryId());
        User user = getUserService().findById(userId);

        Question question = questionMapper.toEntity(questionDto, category, user);
        getQuestionRepository().save(question);

        return getQuestionMapper().toDto(question);
    }

    public QuestionDto updateQuestion(Long questionId, QuestionDto questionDto) {
        Question question = findById(questionId);
        question = questionMapper.toEntity(questionDto);

        getQuestionRepository().save(question);
        return questionMapper.toDto(question);
    }


    public QuestionDto updateQuestionCategory(Long questionId, Long categoryId) {
        Question question = findById(questionId);
        Category category = getCategoryService().findDById(categoryId);

        question.setCategory(category);
        getQuestionRepository().save(question);

        return getQuestionMapper().toDto(question);
    }

    public Object deleteQuestion(Long id) {
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
