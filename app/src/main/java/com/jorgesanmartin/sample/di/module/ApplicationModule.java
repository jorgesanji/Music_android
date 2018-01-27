package com.jorgesanmartin.sample.di.module;

import android.app.Application;

import com.jorgesanmartin.sample.data.repository.LocalRepository;
import com.jorgesanmartin.sample.data.repository.MockRepository;
import com.jorgesanmartin.sample.data.repository.RestRepository;
import com.jorgesanmartin.sample.data.rest.service.RestAdapter;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by jorgesanmartin on 11/3/17.
 */

@Module
public class ApplicationModule {

    private final Application application;

    public ApplicationModule(Application application) {
        this.application = application;
    }

    @Provides
    @Singleton
    public Application provideApplicationContext() {
        return this.application;
    }

    @Provides
    @Singleton
    public RestRepository provideRestRepository(RestAdapter restAdapter) {
        return new RestRepository(restAdapter);
    }

    @Provides
    @Singleton
    public LocalRepository provideLocalRepository() {
        return new LocalRepository();
    }

    @Provides
    @Singleton
    public MockRepository provideMockRepository() {
        return new MockRepository();
    }

    @Provides
    @Singleton
    public RestAdapter provideRestAdapter() {
        return new RestAdapter();
    }


}
