package com.example.AskMeAnything.service;

import com.example.AskMeAnything.entity.User;
import com.example.AskMeAnything.exception.UserNotFoundException;
import com.example.AskMeAnything.repository.UserRepository;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
@Getter
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public ResponseEntity<User> findById(Long id) {
        User user = getUserRepository()
                .findById(id)
                .orElseThrow(() -> new UserNotFoundException("User with id " + id + " not found"));
        return new ResponseEntity<>(user,
                HttpStatus.OK);
    }

    public ResponseEntity<List<User>> findAll() {
        return new ResponseEntity<>(getUserRepository().findAll(), HttpStatus.OK);
    }

    public ResponseEntity<User> createUser(User newUser) {

        User user = new User(
                newUser.getName(),
                newUser.getPassword(),
                newUser.getEmail()
        );
        userRepository.save(user);

        return new ResponseEntity<>(user, HttpStatus.CREATED);
    }

    public ResponseEntity<Object> deleteUser(Long id) {
        Optional<User> searchedUser = getUserRepository().findById(id);

        if (searchedUser.isPresent()) {
            User deletedUser = searchedUser.get();
            getUserRepository().deleteById(id);
            return ResponseEntity.ok(deletedUser);
        }
        String notFoundMessage = String.format("User with id %d not found", id);
        return new ResponseEntity<>(notFoundMessage, HttpStatus.NOT_FOUND);
    }
}