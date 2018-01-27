package com.jorgesanmartin.sample.data.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

/**
 * Created by jorgesanmartin on 11/3/17.
 */

public class HeroeUrl implements Parcelable {

    @SerializedName("type")
    private HeroeLinkType type;
    @SerializedName("url")
    private String url;

    public HeroeLinkType getType() {
        return type;
    }

    public void setType(HeroeLinkType type) {
        this.type = type;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.type == null ? -1 : this.type.ordinal());
        dest.writeString(this.url);
    }

    public HeroeUrl() {
    }

    protected HeroeUrl(Parcel in) {
        int tmpType = in.readInt();
        this.type = tmpType == -1 ? null : HeroeLinkType.values()[tmpType];
        this.url = in.readString();
    }

    public static final Creator<HeroeUrl> CREATOR = new Creator<HeroeUrl>() {
        @Override
        public HeroeUrl createFromParcel(Parcel source) {
            return new HeroeUrl(source);
        }

        @Override
        public HeroeUrl[] newArray(int size) {
            return new HeroeUrl[size];
        }
    };
}
