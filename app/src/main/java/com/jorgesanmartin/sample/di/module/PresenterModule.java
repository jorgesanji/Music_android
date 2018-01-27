package com.jorgesanmartin.sample.di.module;

import com.jorgesanmartin.sample.business.usecase.characters.GetCharacters;
import com.jorgesanmartin.sample.di.PerFragment;
import com.jorgesanmartin.sample.ui.presenter.detail.DetailHeroePresenter;
import com.jorgesanmartin.sample.ui.presenter.home.HomePresenter;
import com.jorgesanmartin.sample.ui.view.AppNavigation;

import dagger.Module;
import dagger.Provides;

/**
 * Created by jorgesanmartin on 11/3/17.
 */

@Module
public class PresenterModule {

    @Provides
    @PerFragment
    HomePresenter provideHomePresenter(AppNavigation appNavigation, GetCharacters getCharacters) {
        return new HomePresenter(appNavigation, getCharacters);
    }

    @Provides
    @PerFragment
    DetailHeroePresenter provideDetailPresenter(AppNavigation appNavigation) {
        return new DetailHeroePresenter(appNavigation);
    }
}
