package com.jorgesanmartin.sample.ui.application;

import android.app.Application;

import com.jorgesanmartin.sample.di.component.ApplicationComponent;
import com.jorgesanmartin.sample.di.component.DaggerApplicationComponent;
import com.jorgesanmartin.sample.di.module.ApplicationModule;
import com.squareup.picasso.Picasso;

/**
 * Created by jorgesanmartin on 11/2/17.
 */

public class SampleApplication extends Application {

    private static SampleApplication application;
    private ApplicationComponent applicationComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        initGraph();
        initPicasso();
        application = this;
    }

    private void initPicasso() {
        Picasso.with(this).setLoggingEnabled(true);
    }

    private void initGraph() {
        applicationComponent = DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule(this))
                .build();
    }

    public static SampleApplication getInstance() {
        return application;
    }

    public ApplicationComponent getApplicationComponent() {
        return applicationComponent;
    }
}
