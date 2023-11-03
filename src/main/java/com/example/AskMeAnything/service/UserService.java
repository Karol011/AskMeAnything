package com.example.AskMeAnything.service;

import com.example.AskMeAnything.dto.UserDto;
import com.example.AskMeAnything.dto.UserMapper;
import com.example.AskMeAnything.entity.User;
import com.example.AskMeAnything.exception.UserNotFoundException;
import com.example.AskMeAnything.repository.UserRepository;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
@Getter
public class UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;


    public UserService(UserRepository userRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    public UserDto findDtoById(Long id) {
        User user = getUserRepository()
                .findById(id)
                .orElseThrow(() -> new UserNotFoundException("User with id " + id + " not found"));
        return userMapper.toDto(user);
    }

    public User findById(Long id) {
        User user = getUserRepository()
                .findById(id)
                .orElseThrow(() -> new UserNotFoundException("User with id " + id + " not found"));
        return user;
    }

    public List<UserDto> findAll() {

        List<UserDto> users = getUserRepository().findAll()
                .stream()
                .map(userMapper::toDto)
                .collect(Collectors.toList());

        return users;
    }

    public UserDto createUser(UserDto newUserDto) {

        User user = new User(
                newUserDto.getName(),
                newUserDto.getPassword(),
                newUserDto.getEmail()

        );
        userRepository.save(user);

        return userMapper.toDto(user);
    }

    public ResponseEntity<Object> deleteUser(Long id) {
        Optional<User> searchedUser = getUserRepository().findById(id);

        if (searchedUser.isPresent()) {
            User deletedUser = searchedUser.get();
            getUserRepository().deleteById(id);
            return ResponseEntity.ok(userMapper.toDto(deletedUser));
        }
        String notFoundMessage = String.format("User with id %d not found", id);
        return new ResponseEntity<>(notFoundMessage, HttpStatus.NOT_FOUND);
    }
}
