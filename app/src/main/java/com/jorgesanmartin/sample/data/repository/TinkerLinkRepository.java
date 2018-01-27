package com.jorgesanmartin.sample.data.repository;

import com.jorgesanmartin.sample.data.model.HeroeResponse;

import rx.Observable;

/**
 * Created by fvillalba on 30/08/2016.
 */
public interface TinkerLinkRepository {

    Observable<HeroeResponse> getCharacters();
}
