package com.jorgesanmartin.sample.data.model;

import android.os.Parcel;

import com.google.gson.annotations.SerializedName;

/**
 * Created by jorgesanmartin on 11/3/17.
 */

public class HeroeStorySummary extends Summary{

    @SerializedName("type")
    private String type;

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        super.writeToParcel(dest, flags);
        dest.writeString(this.type);
    }

    public HeroeStorySummary() {
    }

    protected HeroeStorySummary(Parcel in) {
        super(in);
        this.type = in.readString();
    }

    public static final Creator<HeroeStorySummary> CREATOR = new Creator<HeroeStorySummary>() {
        @Override
        public HeroeStorySummary createFromParcel(Parcel source) {
            return new HeroeStorySummary(source);
        }

        @Override
        public HeroeStorySummary[] newArray(int size) {
            return new HeroeStorySummary[size];
        }
    };
}
