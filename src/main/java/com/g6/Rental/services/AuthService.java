package com.g6.Rental.services;

import org.springframework.stereotype.Service;

import com.g6.Rental.dto.request.RegisterRequest;
import com.g6.Rental.dto.response.AuthResponse;

@Service
public interface AuthService {

    AuthResponse register(RegisterRequest registerRequest);

}