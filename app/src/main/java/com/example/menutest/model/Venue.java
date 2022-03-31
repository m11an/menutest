package com.example.menutest.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class Venue implements Parcelable {
    @SerializedName("id")
    final int id;
    @SerializedName("name")
    final String name;
    @SerializedName("description")
    final String desc;
    @SerializedName("is_open")
    final boolean isOpen;
    @SerializedName("welcome_message")
    final String welcomeMsg;
    @SerializedName("image")
    final ImagesVenue imagesVenue;

    public Venue(int id, String name, String desc, boolean isOpen, String welcomeMsg, ImagesVenue imagesVenue) {
        this.id = id;
        this.name = name;
        this.desc = desc;
        this.isOpen = isOpen;
        this.welcomeMsg = welcomeMsg;
        this.imagesVenue = imagesVenue;
    }

    protected Venue(Parcel in) {
        id = in.readInt();
        name = in.readString();
        desc = in.readString();
        isOpen = in.readByte() != 0;
        welcomeMsg = in.readString();
        this.imagesVenue = in.readParcelable(ImagesVenue.class.getClassLoader());
    }

    public static final Creator<Venue> CREATOR = new Creator<Venue>() {
        @Override
        public Venue createFromParcel(Parcel in) {
            return new Venue(in);
        }

        @Override
        public Venue[] newArray(int size) {
            return new Venue[size];
        }
    };

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDesc() {
        return desc;
    }

    public boolean isOpen() {
        return isOpen;
    }

    public String getWelcomeMsg() {
        return welcomeMsg;
    }

    public ImagesVenue getImagesVenue() {
        return imagesVenue;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(name);
        dest.writeString(desc);
        dest.writeByte((byte) (isOpen ? 1 : 0));
        dest.writeString(welcomeMsg);
        dest.writeParcelable(imagesVenue, flags);
    }
}