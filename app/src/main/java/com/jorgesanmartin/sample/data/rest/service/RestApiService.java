package com.jorgesanmartin.sample.data.rest.service;

import com.jorgesanmartin.sample.data.model.HeroeResponse;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by jorgesanmartin on 11/2/17.
 */

public interface RestApiService {

    String API_KEY = "apikey";
    String HASH = "hash";
    String TIMESTAMP = "ts";

    @GET("v1/public/characters")
    Observable<HeroeResponse> getCharacters(
            @Query(API_KEY) String publicKey,
            @Query(HASH) String hash,
            @Query(TIMESTAMP) long timestamp);

}
