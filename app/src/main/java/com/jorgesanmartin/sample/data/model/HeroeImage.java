package com.jorgesanmartin.sample.data.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

/**
 * Created by jorgesanmartin on 11/3/17.
 */

public class HeroeImage implements Parcelable {

    @SerializedName("path")
    private String path;
    @SerializedName("extension")
    private String extension;

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getExtension() {
        return extension;
    }

    public void setExtension(String extension) {
        this.extension = extension;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.path);
        dest.writeString(this.extension);
    }

    public HeroeImage() {
    }

    protected HeroeImage(Parcel in) {
        this.path = in.readString();
        this.extension = in.readString();
    }

    public static final Parcelable.Creator<HeroeImage> CREATOR = new Parcelable.Creator<HeroeImage>() {
        @Override
        public HeroeImage createFromParcel(Parcel source) {
            return new HeroeImage(source);
        }

        @Override
        public HeroeImage[] newArray(int size) {
            return new HeroeImage[size];
        }
    };
}
