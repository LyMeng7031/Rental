package com.g6.Rental.dto.request;

import java.util.List;

import lombok.Data;

@Data
public class PropertyRequest {
  private String title;
  private String description;
  private String location;
  private double price;
  private String type;
  private boolean available;
  private List<String> imageUrls;
}
