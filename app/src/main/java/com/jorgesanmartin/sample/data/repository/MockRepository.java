package com.jorgesanmartin.sample.data.repository;

import com.jorgesanmartin.sample.data.model.HeroeResponse;

import javax.inject.Inject;

import rx.Observable;

/**
 * Created by fvillalba on 30/08/2016.
 */
public class MockRepository implements TinkerLinkRepository {

    @Inject
    public MockRepository() {
    }

    @Override
    public Observable<HeroeResponse> getCharacters() {
        return Observable.just(new HeroeResponse());
    }
}
