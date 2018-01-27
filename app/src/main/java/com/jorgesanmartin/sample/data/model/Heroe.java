package com.jorgesanmartin.sample.data.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by jorgesanmartin on 11/3/17.
 */

public class Heroe implements Parcelable {

    @SerializedName("id")
    private int id;
    @SerializedName("name")
    private String name;
    @SerializedName("description")
    private String description;
    @SerializedName("modified")
    private Date modified;
    @SerializedName("resourceURI")
    private String resourceURI;
    @SerializedName("urls")
    private List<HeroeUrl> urls;
    @SerializedName("thumbnail")
    private HeroeImage thumbnail;
    @SerializedName("comics")
    private HeroeComicList comics;
    @SerializedName("stories")
    private HeroeStoryList stories;
    @SerializedName("events")
    private HeroeEventList events;
    @SerializedName("series")
    private HeroeSeriesList series;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getModified() {
        return modified;
    }

    public void setModified(Date modified) {
        this.modified = modified;
    }

    public String getResourceURI() {
        return resourceURI;
    }

    public void setResourceURI(String resourceURI) {
        this.resourceURI = resourceURI;
    }

    public List<HeroeUrl> getUrls() {
        return urls;
    }

    public void setUrls(List<HeroeUrl> urls) {
        this.urls = urls;
    }

    public HeroeImage getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(HeroeImage thumbnail) {
        this.thumbnail = thumbnail;
    }

    public HeroeComicList getComics() {
        return comics;
    }

    public void setComics(HeroeComicList comics) {
        this.comics = comics;
    }

    public HeroeStoryList getStories() {
        return stories;
    }

    public void setStories(HeroeStoryList stories) {
        this.stories = stories;
    }

    public HeroeEventList getEvents() {
        return events;
    }

    public void setEvents(HeroeEventList events) {
        this.events = events;
    }

    public HeroeSeriesList getSeries() {
        return series;
    }

    public void setSeries(HeroeSeriesList series) {
        this.series = series;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.id);
        dest.writeString(this.name);
        dest.writeString(this.description);
        dest.writeLong(this.modified != null ? this.modified.getTime() : -1);
        dest.writeString(this.resourceURI);
        dest.writeList(this.urls);
        dest.writeParcelable(this.thumbnail, flags);
        dest.writeParcelable(this.comics, flags);
        dest.writeParcelable(this.stories, flags);
        dest.writeParcelable(this.events, flags);
        dest.writeParcelable(this.series, flags);
    }

    public Heroe() {
    }

    protected Heroe(Parcel in) {
        this.id = in.readInt();
        this.name = in.readString();
        this.description = in.readString();
        long tmpModified = in.readLong();
        this.modified = tmpModified == -1 ? null : new Date(tmpModified);
        this.resourceURI = in.readString();
        this.urls = new ArrayList<HeroeUrl>();
        in.readList(this.urls, HeroeUrl.class.getClassLoader());
        this.thumbnail = in.readParcelable(HeroeImage.class.getClassLoader());
        this.comics = in.readParcelable(HeroeComicList.class.getClassLoader());
        this.stories = in.readParcelable(HeroeStoryList.class.getClassLoader());
        this.events = in.readParcelable(HeroeEventList.class.getClassLoader());
        this.series = in.readParcelable(HeroeSeriesList.class.getClassLoader());
    }

    public static final Parcelable.Creator<Heroe> CREATOR = new Parcelable.Creator<Heroe>() {
        @Override
        public Heroe createFromParcel(Parcel source) {
            return new Heroe(source);
        }

        @Override
        public Heroe[] newArray(int size) {
            return new Heroe[size];
        }
    };

    public String getImage(){
        return thumbnail.getPath()+"."+thumbnail.getExtension();
    }
}
