package com.g6.Rental.controllers;

import com.g6.Rental.dto.request.RegisterRequest;
import com.g6.Rental.dto.response.RegisterResponse;
import com.g6.Rental.services.AuthService;
import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/register")
    public RegisterResponse register(@RequestBody RegisterRequest request) {
        return authService.register(request);
    }

    
}
