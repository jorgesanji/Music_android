package com.jorgesanmartin.sample.business.usecase.base;

import android.content.Context;

import com.jorgesanmartin.sample.data.utils.RetrofitException;
import com.jorgesanmartin.sample.data.rest.service.ApiError;

import rx.Subscriber;

/**
 * Created by jorgesanmartin on 11/2/17.
 */
public abstract class BaseSubscriber<K> extends Subscriber<K> {

    private final Context context;

    public BaseSubscriber(Context context) {
        this.context = context;
    }

    public BaseSubscriber() {
        this.context = null;
    }

    @Override
    public void onCompleted() {
    }

    @Override
    public void onError(Throwable throwable) {
        try {
            RetrofitException error = (RetrofitException) throwable;
            ApiError apiError = error.getErrorBodyAs(ApiError.class);
            onError(apiError != null ? apiError : ApiError.getDefaultError());
        } catch (Exception e) {
            //TODO handle other errors
            e.printStackTrace();
            onError(ApiError.getDefaultError());
        }
    }

    public void onError(ApiError apiError) {
    }

    @Override
    public void onNext(K k) {
    }
}
