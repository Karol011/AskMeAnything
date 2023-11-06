package com.example.AskMeAnything.service;

import com.example.AskMeAnything.dto.CategoryDto;
import com.example.AskMeAnything.dto.QuestionDto;
import com.example.AskMeAnything.dto.QuestionMapper;
import com.example.AskMeAnything.dto.UserDto;
import com.example.AskMeAnything.entity.Question;
import com.example.AskMeAnything.exception.CategoryNotFoundException;
import com.example.AskMeAnything.exception.QuestionNotFoundException;
import com.example.AskMeAnything.repository.QuestionRepository;
import jakarta.transaction.Transactional;
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

    @Transactional
    public QuestionDto createQuestion(QuestionDto questionDto) {

        CategoryDto categoryDto = getCategoryService().findDtoById(questionDto.getCategoryId());
        UserDto userDto = getUserService().findDtoById(questionDto.getUserId());

        Question question = new Question();
        question.setCategory(getCategoryService().getCategoryMapper().toEntity(categoryDto));
        question.setUser(getUserService().getUserMapper().toEntity(userDto));
        question.setText(questionDto.getText());

        getQuestionRepository().save(question);
        userDto.getQuestions().add(question);

        categoryDto.getQuestions().add(question);
        getCategoryService().getCategoryRepository().save(getCategoryService().getCategoryMapper().toEntity(categoryDto));

        return getQuestionMapper().toDto(question);
    }

    public QuestionDto updateQuestionCategory(Long questionId, Long categoryId) {
        Question question = this.findById(questionId);
        CategoryDto categoryDto = getCategoryService().findDtoById(categoryId);

        if (question != null && categoryDto != null) {
            question.setCategory(getCategoryService().getCategoryMapper().toEntity(categoryDto));
            getQuestionRepository().save(question);
            return getQuestionMapper().toDto(question);
        } else if (question == null) {
            throw new QuestionNotFoundException("Question not found");
        } else if (categoryDto == null) {
            throw new CategoryNotFoundException("Category not found");
        }
        return new QuestionDto();
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
