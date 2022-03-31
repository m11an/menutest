package com.example.menutest.model;

import com.google.gson.annotations.SerializedName;

public class Venues {
    @SerializedName("venue")
    final Venue venue;

    public Venues(Venue venue) {
        this.venue = venue;
    }

    public Venue getVenue() {
        return venue;
    }
}
