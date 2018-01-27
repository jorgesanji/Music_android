package com.jorgesanmartin.sample.data.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jorgesanmartin on 11/3/17.
 */

public class HeroeEventList implements Parcelable {

    @SerializedName("available")
    private int available;
    @SerializedName("returned")
    private int returned;
    @SerializedName("collectionURI")
    private String collectionURI;
    @SerializedName("items")
    private List<HeroeEventSummary> items;

    public int getAvailable() {
        return available;
    }

    public void setAvailable(int available) {
        this.available = available;
    }

    public int getReturned() {
        return returned;
    }

    public void setReturned(int returned) {
        this.returned = returned;
    }

    public String getCollectionURI() {
        return collectionURI;
    }

    public void setCollectionURI(String collectionURI) {
        this.collectionURI = collectionURI;
    }

    public List<HeroeEventSummary> getItems() {
        return items;
    }

    public void setItems(List<HeroeEventSummary> items) {
        this.items = items;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.available);
        dest.writeInt(this.returned);
        dest.writeString(this.collectionURI);
        dest.writeList(this.items);
    }

    public HeroeEventList() {
    }

    protected HeroeEventList(Parcel in) {
        this.available = in.readInt();
        this.returned = in.readInt();
        this.collectionURI = in.readString();
        this.items = new ArrayList<HeroeEventSummary>();
        in.readList(this.items, HeroeEventSummary.class.getClassLoader());
    }

    public static final Parcelable.Creator<HeroeEventList> CREATOR = new Parcelable.Creator<HeroeEventList>() {
        @Override
        public HeroeEventList createFromParcel(Parcel source) {
            return new HeroeEventList(source);
        }

        @Override
        public HeroeEventList[] newArray(int size) {
            return new HeroeEventList[size];
        }
    };
}
