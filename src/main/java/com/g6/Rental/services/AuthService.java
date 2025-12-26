package com.g6.Rental.services;

import org.springframework.stereotype.Service;

import com.g6.Rental.dto.request.RegisterRequest;
import com.g6.Rental.dto.response.RegisterResponse;

@Service
public interface AuthService {

    RegisterResponse register(RegisterRequest registerRequest);

}