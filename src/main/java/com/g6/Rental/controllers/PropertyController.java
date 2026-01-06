package com.g6.Rental.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.g6.Rental.dto.request.PropertyRequest;
import com.g6.Rental.dto.response.PropertyResponse;
import com.g6.Rental.services.PropertyService;
import lombok.RequiredArgsConstructor;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
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
    @GetMapping("/getAll")
    public List<PropertyResponse> getAllProperties() {
        return propertyService.getAllProperties();
    }

    // ✅ GET property by ID
    @GetMapping("/{id}")
    public PropertyResponse getPropertyById(@PathVariable Long id) {
        return propertyService.getPropertyById(id);
    }

    @GetMapping("/getAllPropertiesByUserId")
    public List<PropertyResponse> getAllPropertiesByUserId(@RequestHeader("Authorization") String authHeader) {
        return propertyService.getAllPropertiesByUserId(authHeader);
    }

    @GetMapping("/getPropertyIdByUserId/{propertyId}")
    public PropertyResponse getPropertyById(@PathVariable Long propertyId,
            @RequestHeader("Authorization") String authHeader) {
        return propertyService.getPropertyIdByUserId(propertyId, authHeader);
    }

    @PutMapping("/update/{propertyId}")
    public PropertyResponse updateProperty(
            @PathVariable Long propertyId,
            @RequestBody PropertyRequest request,
            @RequestHeader("Authorization") String authHeader) {
        return propertyService.updateProperty(propertyId, request, authHeader);
    }
@DeleteMapping("/delete/{propertyId}")
public ResponseEntity<String> deleteProperty(
        @PathVariable Long propertyId,
        @RequestHeader("Authorization") String authHeader) {

    propertyService.deleteProperty(propertyId, authHeader);
    return ResponseEntity.ok("Property deleted successfully");
}


}
