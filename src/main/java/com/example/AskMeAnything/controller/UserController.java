package com.example.AskMeAnything.controller;

import com.example.AskMeAnything.entity.User;
import com.example.AskMeAnything.service.UserService;
import lombok.Data;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
@Data
public class UserController {

    private UserService userService;

    @GetMapping("/{id}")
    public ResponseEntity<User> findUserById(@PathVariable Long id) {
        return userService.findById(id);
    }

}
