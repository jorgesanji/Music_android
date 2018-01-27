package com.jorgesanmartin.sample.data.repository;

import com.jorgesanmartin.sample.data.model.HeroeResponse;
import com.jorgesanmartin.sample.data.utils.RetrofitException;

import java.util.List;
import java.util.concurrent.Callable;

import javax.inject.Inject;

import retrofit2.Call;
import rx.Observable;

public class LocalRepository implements TinkerLinkRepository {

    @Inject
    public LocalRepository() {
    }

    @Override
    public Observable<HeroeResponse> getCharacters() {
        return mock(HeroeResponse.class);
    }

    private <T> Observable<T> mock(final Class<T> type) {
        return Observable.fromCallable(new Callable<T>() {

            @Override
            public T call() throws Exception {
                throw RetrofitException.unexpectedError(null);
            }
        });
    }

    private <I> Observable<List<I>> mockList(Class<I> item) {
        return Observable.fromCallable(new Callable<List<I>>() {

            @Override
            public List<I> call() throws Exception {
                throw RetrofitException.unexpectedError(null);
            }
        });
    }

    private <I> Call<I> mockCall(Class<I> item) {
        throw RetrofitException.unexpectedError(null);
    }
}
