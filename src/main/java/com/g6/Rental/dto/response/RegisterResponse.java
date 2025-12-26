package com.g6.Rental.dto.response;

import lombok.Data;

@Data
public class RegisterResponse {
    private Long id;
    private String fullName;
    private String username;
    private String email;
    private String phone;
    private String profileImage;
    private String status;

    public RegisterResponse(Long id, String fullName, String username, String email, String phone, String profileImage,
            String status) {
        this.fullName = fullName;
        this.username = username;
        this.email = email;
        this.phone = phone;
        this.profileImage = profileImage;
    }

}