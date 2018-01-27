package com.jorgesanmartin.sample.data.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by jorgesanmartin on 11/5/17.
 */

public enum HeroeLinkType {

    @SerializedName("detail")
    DETAIL,
    @SerializedName("comiclink")
    COMIC,
    @SerializedName("wiki")
    WIKI;
}
