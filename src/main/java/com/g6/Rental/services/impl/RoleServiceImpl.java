package com.g6.Rental.services.impl;

import java.util.ArrayList;

import org.springframework.stereotype.Service;
import com.g6.Rental.entity.Role;
import com.g6.Rental.entity.User;
import com.g6.Rental.exception.ResourceNotFoundException;
import com.g6.Rental.repository.RoleRepository;
import com.g6.Rental.repository.UserRepository;
import com.g6.Rental.security.JwtUtil;
import com.g6.Rental.services.RoleService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;
    private final UserRepository userRepository;
    private final JwtUtil jwtUtil;


    private Long extractUserId(String token) {
        if (token == null || token.isEmpty()) {
            throw new RuntimeException("Token is missing");
        }

        // Remove Bearer prefix
        String jwt = token.startsWith("Bearer ") ? token.substring(7) : token;

        // Validate token
        if (!jwtUtil.validateToken(jwt)) {
            throw new RuntimeException("Invalid or expired token");
        }

        // Extract userId
        return jwtUtil.getUserIdFromToken(jwt);
    }
   @Override
    public String addAgentRole(String token) {

    // 1. Extract userId
    Long userId = extractUserId(token);

    // 2. Find user
    User user = userRepository.findById(userId)
            .orElseThrow(() -> new ResourceNotFoundException("User not found"));

    // 3. Find role "agent"
    Role agentRole = roleRepository.findByName("agent")
            .orElseThrow(() -> new ResourceNotFoundException("Role 'agent' not found"));

    // 4. Initialize role list if null (rare case)
    if (user.getRoles() == null) {
        user.setRoles(new ArrayList<>());
    }

    // 5. Check if user already has AGENT role
    if (user.getRoles().contains(agentRole)) {
        return "User already has AGENT role";
    }

    // 6. Add AGENT role without removing existing roles
    user.getRoles().add(agentRole);

    // 7. Save user
    userRepository.save(user);

    return "Role AGENT added successfully";
}

}
