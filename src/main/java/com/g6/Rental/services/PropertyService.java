package com.g6.Rental.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.g6.Rental.dto.request.PropertyRequest;
import com.g6.Rental.dto.response.PropertyResponse;

@Service
public interface PropertyService {
    PropertyResponse createProperty(PropertyRequest request, String authHeader);

    // ✅ Get all properties
    List<PropertyResponse> getAllProperties();

    // ✅ New: Get property by ID
    PropertyResponse getPropertyById(Long id);

}
