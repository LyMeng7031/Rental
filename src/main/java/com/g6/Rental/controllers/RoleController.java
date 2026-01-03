package com.g6.Rental.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.g6.Rental.services.RoleService;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/roles")
@RequiredArgsConstructor
public class RoleController {
    private final RoleService roleService;

@PostMapping("/add-agent")
public ResponseEntity<String> addAgentRole(@RequestHeader("Authorization") String token) {
    String message = roleService.addAgentRole(token);
    return ResponseEntity.ok(message);
}



}
