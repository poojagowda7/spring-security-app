package com.security.auth.controller;

import com.security.auth.service.CustomAuthenticationService;
import com.security.auth.service.JwtService;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final CustomAuthenticationService customAuthenticationService;
    private final JwtService jwtService;

    public AuthController(CustomAuthenticationService customAuthenticationService, JwtService jwtService) {
        this.customAuthenticationService = customAuthenticationService;
        this.jwtService = jwtService;
    }

    @PostMapping("/login")
    public String login(@RequestParam String username, @RequestParam String password) {
        Authentication authentication = customAuthenticationService.authenticate(username, password);
        String token = jwtService.generateToken(authentication.getName());  // Generate JWT Token
        return token;  // Return token
    }
}
