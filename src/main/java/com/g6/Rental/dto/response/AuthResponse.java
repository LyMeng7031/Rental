package com.g6.Rental.dto.response;

import lombok.Data;

@Data
public class AuthResponse {
    private Long id;
    private String fullName;
    private String username;
    private String email;
    private String phone;
    private String profileImage;
    private String status;
    private String token;

    public AuthResponse(Long id, String fullName, String username, String email, String phone, String profileImage,
            String status, String token) {
        this.id = id;
        this.fullName = fullName;
        this.username = username;
        this.email = email;
        this.phone = phone;
        this.profileImage = profileImage;
        this.status = status;
        this.token = token;
    }

}