package com.example.AskMeAnything.service;

import com.example.AskMeAnything.dto.UserDto;
import com.example.AskMeAnything.dto.UserMapper;
import com.example.AskMeAnything.entity.User;
import com.example.AskMeAnything.exception.InvalidArgumentException;
import com.example.AskMeAnything.exception.UserNotFoundException;
import com.example.AskMeAnything.exception.UserWithThatEmailAlreadyExists;
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
        checkIfUserWithThisSpecificEmailExists(newUserDto.getEmail());
        User user = getUserMapper().toEntity(newUserDto);
        userRepository.save(user);

        return userMapper.toDto(user);
    }

    public UserDto updateUser(Long id, UserDto userDto) {
        checkIfUserWithThisSpecificEmailExists(userDto.getEmail());
        User user = findById(id);
        user = getUserMapper().toEntity(userDto, id);

        getUserRepository().save(user);
        return getUserMapper().toDto(user);

    }

    public UserDto modifyUser(Long id, UserDto userDto) {
        checkIfUserWithThisSpecificEmailExists(userDto.getEmail());
        User user = findById(id);
        //Name
        if (userDto.getName() != null) {
            if (userDto.getName().length() > 50 || userDto.getName().length() < 2) {
                throw new InvalidArgumentException("Username must contain between 2 to 50 characters");
            }
            user.setName(userDto.getName());
        }
        //password
        if (userDto.getPassword() != null) {
            user.setPassword(userDto.getPassword());
        }
        //email
        if (userDto.getEmail() != null) {
            if (!userDto.getEmail().contains("@")) {
                throw new InvalidArgumentException("Email must be valid");
            }
            user.setEmail(userDto.getEmail());
        }
        //questions
        if (userDto.getQuestions() != null) {
            if (!userDto.getQuestions().isEmpty()) {
                user.setQuestions(userDto.getQuestions());
            }
        }

        getUserRepository().save(user);
        return getUserMapper().toDto(user);
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

    private void checkIfUserWithThisSpecificEmailExists(String email) {
        boolean userWithThatEmailAlreadyExists = false;
        userWithThatEmailAlreadyExists = findAll().stream()
                .map(UserDto::getEmail)
                .anyMatch(mail -> mail.equals(email));

        if (userWithThatEmailAlreadyExists) {
            throw new UserWithThatEmailAlreadyExists("User with email: " +email + " already exists");
        }

    }
}
