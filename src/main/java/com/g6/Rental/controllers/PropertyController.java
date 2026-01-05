package com.g6.Rental.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.g6.Rental.dto.request.PropertyRequest;
import com.g6.Rental.dto.response.PropertyResponse;
import com.g6.Rental.services.PropertyService;
import lombok.RequiredArgsConstructor;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

@RestController
@RequestMapping("/api/properties")
@RequiredArgsConstructor
public class PropertyController {

    private final PropertyService propertyService;

    @PostMapping("/create")
    public PropertyResponse postMethodName(@RequestBody PropertyRequest request,
            @RequestHeader("Authorization") String authHeader) {
        return propertyService.createProperty(request, authHeader);
    }

    // ✅ GET all properties
    @GetMapping
    public List<PropertyResponse> getAllProperties() {
        return propertyService.getAllProperties();
    }

    // ✅ GET property by ID
    @GetMapping("/{id}")
    public PropertyResponse getPropertyById(@PathVariable Long id) {
        return propertyService.getPropertyById(id);
    }


}
