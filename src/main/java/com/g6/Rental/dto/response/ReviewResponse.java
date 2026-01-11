package com.g6.Rental.dto.response;

import lombok.Data;

@Data
public class ReviewResponse {
    Long id;
    int rating;
    String comment;

    public ReviewResponse(Long id, int rating, String comment) {
        this.id = id;
        this.rating = rating;
        this.comment = comment;
    }
}
