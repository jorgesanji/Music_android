package com.jorgesanmartin.sample.data.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by jorgesanmartin on 11/3/17.
 */

public class HeroeResult implements Parcelable {

    @SerializedName("offset")
    private int offset;
    @SerializedName("limit")
    private int limit;
    @SerializedName("total")
    private int total;
    @SerializedName("count")
    private int count;
    @SerializedName("results")
    private List<Heroe> results;

    public int getOffset() {
        return offset;
    }

    public void setOffset(int offset) {
        this.offset = offset;
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public List<Heroe> getResults() {
        return results;
    }

    public void setResults(List<Heroe> results) {
        this.results = results;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.offset);
        dest.writeInt(this.limit);
        dest.writeInt(this.total);
        dest.writeInt(this.count);
        dest.writeTypedList(this.results);
    }

    public HeroeResult() {
    }

    protected HeroeResult(Parcel in) {
        this.offset = in.readInt();
        this.limit = in.readInt();
        this.total = in.readInt();
        this.count = in.readInt();
        this.results = in.createTypedArrayList(Heroe.CREATOR);
    }

    public static final Parcelable.Creator<HeroeResult> CREATOR = new Parcelable.Creator<HeroeResult>() {
        @Override
        public HeroeResult createFromParcel(Parcel source) {
            return new HeroeResult(source);
        }

        @Override
        public HeroeResult[] newArray(int size) {
            return new HeroeResult[size];
        }
    };
}
