package com.g6.Rental.services.impl;
import com.g6.Rental.security.JwtUtil;
import com.g6.Rental.dto.request.LoginRequest;
import com.g6.Rental.dto.request.RegisterRequest;
import com.g6.Rental.dto.response.AuthResponse;
import com.g6.Rental.entity.Role;
import com.g6.Rental.entity.User;
import com.g6.Rental.repository.RoleRepository;
import com.g6.Rental.repository.UserRepository;
import com.g6.Rental.services.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    @Override
    public AuthResponse register(RegisterRequest registerRequest) {

        // Check if username or email already exists
        if (userRepository.findByUsername(registerRequest.getUsername()).isPresent()) {
            throw new RuntimeException("Username is already taken");
        }
        if (userRepository.findByEmail(registerRequest.getEmail()).isPresent()) {
            throw new RuntimeException("Email is already registered");
        }

        User user = new User();
        user.setFullName(registerRequest.getFullName());
        user.setUsername(registerRequest.getUsername());
        user.setEmail(registerRequest.getEmail());
        user.setPassword(passwordEncoder.encode(registerRequest.getPassword()));
        user.setPhone(registerRequest.getPhone());
        user.setProfileImage(registerRequest.getProfileImage());
        user.setStatus("ACTIVE");

        Role defaultRole = roleRepository.findByName("user")
                .orElseThrow(() -> new RuntimeException("Default role user not found"));
        List<Role> roles = new ArrayList<>();
        roles.add(defaultRole);
        user.setRoles(roles);

        User savedUser = userRepository.save(user);

        String token = jwtUtil.generateToken(savedUser.getId(), savedUser.getRoles());

        return new AuthResponse(
                savedUser.getId(),
                savedUser.getFullName(),
                savedUser.getUsername(),
                savedUser.getEmail(),
                savedUser.getPhone(),
                savedUser.getProfileImage(),
                savedUser.getStatus(),
                token

        );

    }
    @Override
    public AuthResponse login(LoginRequest loginRequest) {
        User user = userRepository.findByEmail(loginRequest.getEmail())
                .orElseThrow(() -> new RuntimeException("User not found with email: " + loginRequest.getEmail()));
        if (!passwordEncoder.matches(loginRequest.getPassword(), user.getPassword())) {
            throw new RuntimeException("Invalid password");
        }
        String token = jwtUtil.generateToken(user.getId(), user.getRoles());
        return new AuthResponse(
                user.getId(),
                user.getFullName(),
                user.getUsername(),
                user.getEmail(),
                user.getPhone(),
                user.getProfileImage(),
                user.getStatus(),
                token
        );
    }   
}