package com.g6.Rental.dto.response;

import java.util.List;

import lombok.Data;

@Data
public class GetReviewResponse {
    private Long propertyId;
    private double averageRating;
    private int totalReviewer;
    private List<ReviewResponse> reviews;

    public GetReviewResponse(Long propertyId, double averageRating, int totalReviewer, List<ReviewResponse> reviews) {
        this.propertyId = propertyId;
        this.averageRating = averageRating;
        this.totalReviewer = totalReviewer;
        this.reviews = reviews;
    }


}
