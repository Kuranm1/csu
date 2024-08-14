package com.example.csu.mapper;

import com.example.csu.dto.UserDto;
import com.example.csu.model.User;

public class UserMapper {

    public static UserDto toDto(User user) {
        return  UserDto.builder()
                .id(user.getId())
                .fullName(user.getFullName())
                .username(user.getUsername())
                .roles(user.getRoles())
                .build();
    }

    public static User toEntity(UserDto user) {
        return  User.builder()
                .id(user.getId())
                .fullName(user.getFullName())
                .username(user.getUsername())
                .roles(user.getRoles())
                .build();
    }

}
