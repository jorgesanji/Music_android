package com.jorgesanmartin.sample.di.component;

import android.app.Application;

import com.jorgesanmartin.sample.data.repository.LocalRepository;
import com.jorgesanmartin.sample.data.repository.MockRepository;
import com.jorgesanmartin.sample.data.repository.RestRepository;
import com.jorgesanmartin.sample.data.rest.service.RestAdapter;
import com.jorgesanmartin.sample.di.module.ApplicationModule;
import com.jorgesanmartin.sample.ui.application.SampleApplication;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by jorgesanmartin on 11/3/17.
 */

@Singleton
@Component(modules = ApplicationModule.class)
public interface ApplicationComponent {

    Application provideApplication();

    RestRepository provideRestRepository();

    LocalRepository provideLocalRepository();

    MockRepository provideMockRepository();

    RestAdapter provideRestAdapter();

    void inject(SampleApplication application);
}
