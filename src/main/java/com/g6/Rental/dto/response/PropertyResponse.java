package com.g6.Rental.dto.response;

import lombok.Data;

@Data
public class PropertyResponse {
    private Long id;
    private String title;
    private String description;
    private String location;
    private double price;
    private String type;
    private boolean available;
    private java.util.List<String> imageUrls;

    public PropertyResponse(Long id, String title, String description, String location, double price, String type,
            boolean available, java.util.List<String> imageUrls) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.location = location;
        this.price = price;
        this.type = type;
        this.available = available;
        this.imageUrls = imageUrls;
    }
}
