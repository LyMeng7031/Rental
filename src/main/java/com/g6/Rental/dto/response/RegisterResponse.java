package com.g6.Rental.dto.response;

import lombok.Data;

@Data
public class RegisterResponse {
    private String fullName;
    private String username;
    private String email;
    private String phone;
    private String profileImage;

    public RegisterResponse(String fullName, String username, String email, String phone, String profileImage) {
        this.fullName = fullName;
        this.username = username;
        this.email = email;
        this.phone = phone;
        this.profileImage = profileImage;
    }

}