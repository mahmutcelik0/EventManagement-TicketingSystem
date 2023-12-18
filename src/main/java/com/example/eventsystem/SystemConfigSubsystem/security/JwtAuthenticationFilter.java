package com.example.eventsystem.SystemConfigSubsystem.security;

import com.example.eventsystem.UserManagementSubsystem.service.UserService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {
    @Autowired
    private UserService userService;

    //GEÇİÇİ OLARAK SADECE LOGINDE KONTROL YAPACAK ŞEKİLDE DÜZENLENDİ
    @SneakyThrows
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        if (request.getRequestURI().contains("/api/v1/users/login") && !userService.authenticateUser(request.getParameter("email"), request.getParameter("password"))) {
            response.setStatus(HttpStatus.UNAUTHORIZED.value());
        } else {
            filterChain.doFilter(request, response);
        }

    }
}

