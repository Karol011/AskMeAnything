package com.example.AskMeAnything.controller;

import com.example.AskMeAnything.dto.UserDto;
import com.example.AskMeAnything.service.UserService;
import jakarta.validation.Valid;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;
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
    public ResponseEntity<UserDto> findUserById(@PathVariable Long id) {
        return new ResponseEntity<>(userService.findDtoById(id), HttpStatus.OK);
    }

    @GetMapping()
    public ResponseEntity<List<UserDto>> getAllUsers() {

        return new ResponseEntity<>(userService.findAll(),HttpStatus.OK);
    }


    @PostMapping
    public ResponseEntity<UserDto> createUser(@Valid @RequestBody UserDto userDto) {
        return new ResponseEntity<>(userService.createUser(userDto), HttpStatus.CREATED);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Object> deleteUser(@PathVariable Long id) {
        return userService.deleteUser(id);
    }
}
