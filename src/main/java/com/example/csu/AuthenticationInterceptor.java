package com.example.csu;

import com.example.csu.dto.UserDto;
import com.example.csu.service.AuthService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.http.HttpStatus;

@Component
public class AuthenticationInterceptor implements HandlerInterceptor {
    @Autowired
    private AuthService authService;
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if ("OPTIONS".equalsIgnoreCase(request.getMethod())) {
            return true;
        }
        HttpSession session = request.getSession(false);
        if (session == null || authService.isLoggedIn(session) == null ) {
            response.setStatus(HttpStatus.UNAUTHORIZED.value());
            response.getWriter().write("No user logged in");
            return false;
        }
        return true;
    }

}
