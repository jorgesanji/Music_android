package com.jorgesanmartin.sample.data.repository;

import com.jorgesanmartin.sample.data.model.HeroeResponse;
import com.jorgesanmartin.sample.data.rest.service.RestAdapter;
import com.jorgesanmartin.sample.data.rest.service.RestConstants;
import com.jorgesanmartin.sample.data.utils.RetrofitUtils;

import java.security.NoSuchAlgorithmException;
import java.util.Date;

import rx.Observable;

/**
 * Created by fvillalba on 30/08/2016.
 */
public class RestRepository implements TinkerLinkRepository {

    RestAdapter restAdapter;

    public RestRepository(RestAdapter restAdapter) {
        this.restAdapter = restAdapter;
    }

    @Override
    public Observable<HeroeResponse> getCharacters() {
        long timestamp = new Date().getTime();
        String preHash = String.valueOf(timestamp)+RestConstants.privateKey+RestConstants.publicKey;
        String hash = null;
        try {
            hash = RetrofitUtils.md5(preHash);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return restAdapter.getService().getCharacters(RestConstants.publicKey, hash, timestamp);
    }
}

