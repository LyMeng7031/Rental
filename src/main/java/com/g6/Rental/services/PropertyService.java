package com.g6.Rental.services;

import org.springframework.stereotype.Service;

import com.g6.Rental.dto.request.PropertyRequest;
import com.g6.Rental.dto.response.PropertyResponse;

@Service
public interface PropertyService {
    PropertyResponse createProperty(PropertyRequest request,String authHeader);
    
}
