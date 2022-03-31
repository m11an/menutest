package com.example.menutest.network.dto;

import com.google.gson.annotations.SerializedName;

public class LocationDto {
    @SerializedName("latitude")
    final double lat;
    @SerializedName("longitude")
    final double lon;

    public LocationDto(double lat, double lon) {
        this.lat = lat;
        this.lon = lon;
    }
}
