package com.g6.Rental.dto.request;

import lombok.Data;

@Data
public class RegisterRequest {
    private String fullName;
    private String username;
    private String email;
    private String password;
    private String phone;
    private String profileImage;
}
