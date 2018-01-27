package com.jorgesanmartin.sample.di.module;

import android.app.Activity;

import com.jorgesanmartin.sample.di.PerFragment;
import com.jorgesanmartin.sample.ui.view.AppNavigation;

import dagger.Module;
import dagger.Provides;

/**
 * Created by jorgesanmartin on 11/3/17.
 */

@Module
public class NavigatorModule {

    private final Activity activity;

    public NavigatorModule(Activity activity) {
        this.activity = activity;
    }

    @Provides
    @PerFragment
    AppNavigation provideNavigator() {
        return new AppNavigation(activity);
    }
}
