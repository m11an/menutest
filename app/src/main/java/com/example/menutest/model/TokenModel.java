package com.example.menutest.model;

import com.google.gson.annotations.SerializedName;

public class TokenModel {
    @SerializedName("value")
    private final String token;

    public TokenModel(String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }
}
