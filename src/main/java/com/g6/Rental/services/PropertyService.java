package com.g6.Rental.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.g6.Rental.dto.request.PropertyRequest;
import com.g6.Rental.dto.response.PropertyResponse;

@Service
public interface PropertyService {
    PropertyResponse createProperty(PropertyRequest request, String authHeader);

    List<PropertyResponse> getAllProperties();

    PropertyResponse getPropertyById(Long id);

    List<PropertyResponse> getAllPropertiesByUserId(String authHeader);

    PropertyResponse getPropertyIdByUserId(Long propertyId, String authHeader);
    
    PropertyResponse updateProperty(Long propertyId, PropertyRequest request, String authHeader);

void deleteProperty(Long propertyId, String authHeader);


}
