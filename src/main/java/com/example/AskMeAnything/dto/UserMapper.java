package com.example.AskMeAnything.dto;

import com.example.AskMeAnything.entity.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    public User toEntity(UserDto userDto) {
        User user = new User();

        if (userDto.getId() != null) {
            user.setId(userDto.getId());
        }
        user.setName(userDto.getName());
        user.setPassword(userDto.getPassword());
        user.setEmail(userDto.getEmail());
        user.setQuestions(userDto.getQuestions());

        return user;
    }
    public User toEntity(UserDto userDto, Long id) {
        User user = new User();

        if (id != null) {
            user.setId(id);
        }
        user.setName(userDto.getName());
        user.setPassword(userDto.getPassword());
        user.setEmail(userDto.getEmail());
        user.setQuestions(userDto.getQuestions());

        return user;
    }

    public UserDto toDto(User user) {
        UserDto userDto = new UserDto();
        userDto.setId(user.getId());
        userDto.setName(user.getName());
        userDto.setPassword(user.getPassword());
        userDto.setEmail(user.getEmail());
        userDto.setQuestions(user.getQuestions());

        return userDto;
    }
}
