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

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;
    private final UserRepository userRepository;
    private final JwtUtil jwtUtil;


    
   @Override
   @Transactional
    public String addAgentRole(String authHeader) {

        // 0. Extract token from header

    String token = authHeader;
        if (authHeader.startsWith("Bearer ")) {
            token = authHeader.substring(7);
        }

    // 1. Extract userId
    Long userId = jwtUtil.getUserIdFromToken(token);

    // 2. Find user
    User user = userRepository.findById(userId)
            .orElseThrow(() -> new ResourceNotFoundException("User not found"));

    // 3. Find role "agent"
    Role agentRole = roleRepository.findByName("agent")
            .orElseThrow(() -> new ResourceNotFoundException("Role 'agent' not found"));

    if (user.getRoles() == null) {
        user.setRoles(new ArrayList<>());
    }

    boolean alreadyHasRole = user.getRoles().stream()
                .anyMatch(r -> r.getName().equalsIgnoreCase("agent"));

        if (alreadyHasRole) {
            return "User already has AGENT role";
        }

    // 6. Add AGENT role without removing existing roles
    user.getRoles().add(agentRole);

    // 7. Save user
    userRepository.save(user);

    return "Role AGENT added successfully";
}

}
