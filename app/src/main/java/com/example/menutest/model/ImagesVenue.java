package com.example.menutest.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class ImagesVenue implements Parcelable {
    @SerializedName("thumbnail_small")
    final String small;
    @SerializedName("thumbnail_medium")
    final String medium;
    @SerializedName("fullsize")
    final String large;

    public ImagesVenue(String small, String medium, String large) {
        this.small = small;
        this.medium = medium;
        this.large = large;
    }

    protected ImagesVenue(Parcel in) {
        small = in.readString();
        medium = in.readString();
        large = in.readString();
    }

    public static final Creator<ImagesVenue> CREATOR = new Creator<ImagesVenue>() {
        @Override
        public ImagesVenue createFromParcel(Parcel in) {
            return new ImagesVenue(in);
        }

        @Override
        public ImagesVenue[] newArray(int size) {
            return new ImagesVenue[size];
        }
    };

    public String getSmall() {
        return small;
    }

    public String getMedium() {
        return medium;
    }

    public String getLarge() {
        return large;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(small);
        dest.writeString(medium);
        dest.writeString(large);
    }
}
