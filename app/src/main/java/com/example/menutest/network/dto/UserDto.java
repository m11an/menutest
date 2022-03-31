package com.example.menutest.network.dto;

import com.google.gson.annotations.SerializedName;

public class UserDto {
    @SerializedName("email")
    final String email;
    @SerializedName("password")
    final String password;

    public UserDto(String email, String password) {
        this.email = email;
        this.password = password;
    }
}
