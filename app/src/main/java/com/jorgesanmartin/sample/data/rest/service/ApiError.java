package com.jorgesanmartin.sample.data.rest.service;

import com.google.gson.annotations.SerializedName;

/**
 * Created by jorgesanmartin on 11/2/17.
 */

public class ApiError {

    public static final int API_ERROR_UNDEFINED = 0x01;
    public static final String API_ERROR_MESSAGE = "UNDEFINED ERROR";

    @SerializedName("statusCode")
    protected int statusCode;

    @SerializedName("message")
    protected String message;

    @SerializedName("code")
    protected int code;

    public int getStatusCode() {
        return statusCode;
    }

    public ApiError setStatusCode(int statusCode) {
        this.statusCode = statusCode;
        return this;
    }

    public String getMessage() {
        return message;
    }

    public ApiError setMessage(String message) {
        this.message = message;
        return this;
    }

    public int getCode() {
        return code;
    }

    public ApiError setCode(int code) {
        this.code = code;
        return this;
    }

    public static ApiError getDefaultError() {
        return new ApiError().setCode(API_ERROR_UNDEFINED).setMessage(API_ERROR_MESSAGE);
    }
}
