package com.jorgesanmartin.sample.di;

import android.app.Activity;

import com.jorgesanmartin.sample.di.component.DaggerPresenterComponent;
import com.jorgesanmartin.sample.di.component.PresenterComponent;
import com.jorgesanmartin.sample.di.module.NavigatorModule;
import com.jorgesanmartin.sample.di.module.PresenterModule;
import com.jorgesanmartin.sample.di.module.UseCaseModule;
import com.jorgesanmartin.sample.ui.application.SampleApplication;

/**
 * Created by jorgesanmartin on 11/3/17.
 */

public class InjectorHelper {

    public static PresenterComponent getPresenterComponent(Activity activity) {
        return DaggerPresenterComponent.builder()
                .applicationComponent(SampleApplication.getInstance()
                        .getApplicationComponent())
                .useCaseModule(new UseCaseModule(activity))
                .navigatorModule(new NavigatorModule(activity))
                .presenterModule(new PresenterModule())
                .build();
    }
}
