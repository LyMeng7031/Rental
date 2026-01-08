package com.g6.Rental.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.g6.Rental.dto.request.ReviewRequest;
import com.g6.Rental.dto.response.ReviewResponse;
import com.g6.Rental.services.ReviewService;

import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;


@RestController
@RequestMapping("/api/reviews")
@RequiredArgsConstructor
public class ReviewController {

    private final ReviewService reviewService;

    @PostMapping("/create/{propertyId}")
    public ReviewResponse createReview(@PathVariable Long propertyId, @RequestBody ReviewRequest request,
            @RequestHeader("Authorization") String authHeader) {
        return reviewService.createReview(propertyId, request, authHeader);
    }
    
}
