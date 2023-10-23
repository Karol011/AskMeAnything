package com.example.AskMeAnything.controller;

import com.example.AskMeAnything.entity.User;
import com.example.AskMeAnything.service.UserService;
import jakarta.validation.Valid;
import lombok.*;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
@Getter
@Setter
@Validated
public class UserController {

    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> findUserById(@PathVariable Long id) {
        return userService.findById(id);
    }

    @GetMapping()
    public ResponseEntity<List<User>> getAllUsers() {
        return userService.findAll();
    }


    @PostMapping
    public ResponseEntity<User> createUser(@Valid @RequestBody User user) {

        return userService.createUser(user);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Object> deleteUser(@PathVariable Long id) {
        return userService.deleteUser(id);
    }
}