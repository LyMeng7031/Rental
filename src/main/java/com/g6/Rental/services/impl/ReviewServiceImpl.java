package com.g6.Rental.services.impl;

import org.springframework.stereotype.Service;

import com.g6.Rental.dto.request.ReviewRequest;
import com.g6.Rental.dto.response.ReviewResponse;
import com.g6.Rental.entity.Property;
import com.g6.Rental.entity.Review;
import com.g6.Rental.entity.User;
import com.g6.Rental.exception.BadRequestException;
import com.g6.Rental.exception.ForbiddenException;
import com.g6.Rental.exception.ResourceNotFoundException;
import com.g6.Rental.repository.PropertyRepository;
import com.g6.Rental.repository.ReviewRepository;
import com.g6.Rental.repository.UserRepository;
import com.g6.Rental.security.JwtUtil;
import com.g6.Rental.services.ReviewService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ReviewServiceImpl implements ReviewService {

    private final ReviewRepository reviewRepository;
    private final UserRepository userRepository;
    private final PropertyRepository propertyRepository;
    private final JwtUtil jwtUtil;

    @Override
    public ReviewResponse createReview(Long propertyId, ReviewRequest request, String authHeader) {

        // 1️⃣ Validate Authorization Header
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            throw new ForbiddenException("Authorization header is missing or invalid");
        }

        String token = authHeader.substring(7);
        Long userId = jwtUtil.getUserIdFromToken(token);

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        Property property = propertyRepository.findById(propertyId)
                .orElseThrow(() -> new ResourceNotFoundException("Property not found"));

        if (reviewRepository.existsByUserAndProperty(user, property)) {
            throw new ForbiddenException("You have already reviewed this property");
        }

        int rating = request.getRating();
        if (rating < 1 || rating > 5) {
            throw new BadRequestException("Rating must be between 1 and 5");
        }


        Review review = new Review();
        review.setRating(rating);
        review.setComment(request.getComment());
        review.setUser(user);
        review.setProperty(property);

        Review savedReview = reviewRepository.save(review);

        return new ReviewResponse(
                savedReview.getId(),
                savedReview.getRating(),
                savedReview.getComment()
        );
    }
}
