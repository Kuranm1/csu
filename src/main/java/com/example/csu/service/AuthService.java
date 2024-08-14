package com.example.csu.service;

import com.example.csu.dto.UserDto;
import com.example.csu.mapper.UserMapper;
import com.example.csu.model.User;
import com.example.csu.repository.UserRepository;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class AuthService {
    @Autowired
    private UserRepository userRepository;

    public UserDto authenticate(String username, String password) {
        User user = userRepository.findByUsername(username);
        if (user != null && user.getPassword().equals(password)) {
            return UserMapper.toDto(user);
        }
        return null;
    }

    public UserDto isLoggedIn(HttpSession session){
        return (UserDto) session.getAttribute("user");
    }

    public Boolean isManger(HttpSession session) {
        UserDto user = (UserDto) session.getAttribute("user");
        return user != null && user.getRoles().stream().anyMatch(role -> role.getName().equals("MANGER"));
    }

}
