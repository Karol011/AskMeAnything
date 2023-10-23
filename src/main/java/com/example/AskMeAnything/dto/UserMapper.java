package com.example.AskMeAnything.dto;

import com.example.AskMeAnything.entity.User;

public class UserMapper {

    public static User toEntity(UserDto userDto) {
        User user = new User();
        user.setId(user.getId());
        user.setName(userDto.getName());
        user.setPassword(userDto.getPassword());
        user.setEmail(userDto.getEmail());

        return user;
    }

    public static UserDto toDto(User user) {
        UserDto userDto = new UserDto();
        userDto.setName(user.getName());
        userDto.setPassword(user.getPassword());
        userDto.setEmail(user.getEmail());

        return userDto;
    }
}
