package com.g6.Rental.services.impl;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.stereotype.Service;

import com.g6.Rental.dto.request.PropertyRequest;
import com.g6.Rental.dto.response.PropertyResponse;
import com.g6.Rental.entity.Property;
import com.g6.Rental.entity.PropertyImage;
import com.g6.Rental.entity.User;
import com.g6.Rental.exception.ForbiddenException;
import com.g6.Rental.exception.ResourceNotFoundException;
import com.g6.Rental.repository.PropertyRepository;
import com.g6.Rental.repository.UserRepository;
import com.g6.Rental.security.JwtUtil;
import com.g6.Rental.services.PropertyService;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PropertyServiceImpl implements PropertyService {

    private final PropertyRepository propertyRepository;
    private final UserRepository userRepository;
    private final JwtUtil jwtUtil;

    
@Transactional
@Override
public PropertyResponse createProperty(PropertyRequest request, String authHeader) {

    // 1. Safe Header Extraction
    if (authHeader == null || !authHeader.startsWith("Bearer ")) {
        throw new ForbiddenException("Authorization header is missing or invalid");
    }
    String token = authHeader.substring(7);

    // 2. Role Check (Check this BEFORE hitting the DB)
    List<String> roles = jwtUtil.getRolesFromToken(token);
    if (roles == null || !roles.contains("agent")) {
        throw new ForbiddenException("You are not allowed to create a property");
    }

    // 3. User Extraction
    Long userId = jwtUtil.getUserIdFromToken(token);
    User user = userRepository.findById(userId)
            .orElseThrow(() -> new ResourceNotFoundException("User not found"));

    // 4. Property Creation
    Property property = new Property();
    property.setTitle(request.getTitle());
    property.setDescription(request.getDescription());
    property.setLocation(request.getLocation());
    property.setPrice(BigDecimal.valueOf(request.getPrice()));
    property.setType(request.getType());
    property.setAvailable(request.isAvailable());
    property.setUser(user);

    // 5. Safe Image Mapping
    if (request.getImageUrls() != null) {
        List<PropertyImage> images = request.getImageUrls().stream()
                .map(url -> {
                    PropertyImage img = new PropertyImage();
                    img.setImageUrl(url);
                    img.setProperty(property);
                    return img;
                }).toList();
        property.setImages(images);
    }

    Property savedProperty = propertyRepository.save(property);

    // 6. Response Mapping
    List<String> imageUrls = (savedProperty.getImages() != null) ? 
        savedProperty.getImages().stream().map(PropertyImage::getImageUrl).toList() : List.of();

    return new PropertyResponse(
            savedProperty.getId(),
            savedProperty.getTitle(),
            savedProperty.getDescription(),
            savedProperty.getLocation(),
            savedProperty.getPrice().doubleValue(),
            savedProperty.getType(),
            savedProperty.isAvailable(),
            imageUrls
    );
}
}