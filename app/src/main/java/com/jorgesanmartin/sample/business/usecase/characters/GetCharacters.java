package com.jorgesanmartin.sample.business.usecase.characters;

import com.jorgesanmartin.sample.business.usecase.base.BaseUseCase;
import com.jorgesanmartin.sample.data.model.HeroeResponse;
import com.jorgesanmartin.sample.data.repository.RepositoryProxy;

import rx.Observable;

/**
 * Created by jorgesanmartin on 11/3/17.
 */

public class GetCharacters extends BaseUseCase<HeroeResponse>{

    public GetCharacters(RepositoryProxy repositoryFactory) {
        super(repositoryFactory);
    }

    @Override
    public Observable<HeroeResponse> buildUseCaseObservable() {
        return repositoryFactory.getRepository().getCharacters();
    }
}
