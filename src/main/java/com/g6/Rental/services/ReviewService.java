package com.g6.Rental.services;

import org.springframework.stereotype.Service;

import com.g6.Rental.dto.request.ReviewRequest;
import com.g6.Rental.dto.response.ReviewResponse;

@Service
public interface ReviewService {
    ReviewResponse createReview(Long propertyId, ReviewRequest request, String authHeader);
}
