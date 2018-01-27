package com.jorgesanmartin.sample.di.component;

import com.jorgesanmartin.sample.di.PerFragment;
import com.jorgesanmartin.sample.di.module.NavigatorModule;
import com.jorgesanmartin.sample.di.module.PresenterModule;
import com.jorgesanmartin.sample.di.module.UseCaseModule;
import com.jorgesanmartin.sample.ui.view.detail.DetailHeroeFragment;
import com.jorgesanmartin.sample.ui.view.home.HomeFragment;

import dagger.Component;

/**
 * Created by jorgesanmartin on 11/3/17.
 */

@PerFragment
@Component(modules = {PresenterModule.class, NavigatorModule.class, UseCaseModule.class}, dependencies = {ApplicationComponent.class})
public interface PresenterComponent {

    void inject(HomeFragment homeFragment);

    void inject(DetailHeroeFragment homeFragment);
}
