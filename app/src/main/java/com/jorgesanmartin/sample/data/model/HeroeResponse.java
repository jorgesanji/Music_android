package com.jorgesanmartin.sample.data.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

/**
 * Created by jorgesanmartin on 11/3/17.
 */

public class HeroeResponse implements Parcelable {

    @SerializedName("code")
    private int code;
    @SerializedName("status")
    private String status;
    @SerializedName("data")
    private HeroeResult data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public HeroeResult getData() {
        return data;
    }

    public void setData(HeroeResult data) {
        this.data = data;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.code);
        dest.writeString(this.status);
        dest.writeParcelable(this.data, flags);
    }

    public HeroeResponse() {
    }

    protected HeroeResponse(Parcel in) {
        this.code = in.readInt();
        this.status = in.readString();
        this.data = in.readParcelable(HeroeResult.class.getClassLoader());
    }

    public static final Parcelable.Creator<HeroeResponse> CREATOR = new Parcelable.Creator<HeroeResponse>() {
        @Override
        public HeroeResponse createFromParcel(Parcel source) {
            return new HeroeResponse(source);
        }

        @Override
        public HeroeResponse[] newArray(int size) {
            return new HeroeResponse[size];
        }
    };
}
