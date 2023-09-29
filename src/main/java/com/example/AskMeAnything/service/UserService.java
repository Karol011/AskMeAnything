package com.example.AskMeAnything.service;

import com.example.AskMeAnything.entity.User;
import com.example.AskMeAnything.exception.UserNotFoundException;
import com.example.AskMeAnything.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class UserService {

    private UserRepository userRepository;

    public ResponseEntity<User> findById(Long id) {
        User user = getUserRepository()
                .findById(id)
                .orElseThrow(() -> new UserNotFoundException("user with id " + id + " not found"));
        return new ResponseEntity<>(user,
                HttpStatus.OK);
    }
}
