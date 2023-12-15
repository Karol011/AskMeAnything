package com.example.AskMeAnything.service;

import com.example.AskMeAnything.entity.Answer;
import com.example.AskMeAnything.exception.AnswerNotFoundException;
import com.example.AskMeAnything.repository.AnswerRepository;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@Getter
@RequiredArgsConstructor
public class AnswerService {

    private final AnswerRepository answerRepository;

    public Answer findById(Long id) {
        Answer answer = getAnswerRepository()
                .findById(id)
                .orElseThrow(() -> new AnswerNotFoundException("Answer with id " + id + " not found"));
        return answer;
    }

    public Answer createAnswer(Answer answer) {
        return new Answer();
    }
}
