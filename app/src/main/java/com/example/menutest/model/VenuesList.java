package com.example.menutest.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class VenuesList {
    @SerializedName("venues")
    List<Venues> listOfVenues;

    public List<Venues> getListOfVenues() {
        return listOfVenues;
    }

    public void setListOfVenues(List<Venues> listOfVenues) {
        this.listOfVenues = listOfVenues;
    }
}
