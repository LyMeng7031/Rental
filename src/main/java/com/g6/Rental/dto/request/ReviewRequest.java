package com.g6.Rental.dto.request;

import lombok.Data;

@Data
public class ReviewRequest {
    int rating;
    String comment;
}
