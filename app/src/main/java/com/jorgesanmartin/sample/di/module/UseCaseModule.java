package com.jorgesanmartin.sample.di.module;

import android.content.Context;

import com.jorgesanmartin.sample.business.usecase.characters.GetCharacters;
import com.jorgesanmartin.sample.data.repository.RepositoryProxy;
import com.jorgesanmartin.sample.di.PerFragment;

import dagger.Module;
import dagger.Provides;

/**
 * Created by jorgesanmartin on 11/3/17.
 */

@Module
public class UseCaseModule {

    private Context activity;

    public UseCaseModule(Context activity) {
        this.activity = activity;
    }

    @Provides
    @PerFragment
    GetCharacters provideGetCharacters(RepositoryProxy repositoryFactory) {
        return new GetCharacters(repositoryFactory);
    }
}
