package com.jorgesanmartin.sample.ui.presenter.home;

import com.jorgesanmartin.sample.business.usecase.base.BaseSubscriber;
import com.jorgesanmartin.sample.business.usecase.characters.GetCharacters;
import com.jorgesanmartin.sample.data.model.Heroe;
import com.jorgesanmartin.sample.data.model.HeroeResponse;
import com.jorgesanmartin.sample.data.rest.service.ApiError;
import com.jorgesanmartin.sample.ui.presenter.base.PrensenterImpl;
import com.jorgesanmartin.sample.ui.presenter.base.Presenter;
import com.jorgesanmartin.sample.ui.utils.IODataSource;
import com.jorgesanmartin.sample.ui.view.AppNavigation;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jorgesanmartin on 11/3/17.
 */

public class HomePresenter extends PrensenterImpl<HomePresenter.View> implements IODataSource {

    public interface View extends Presenter.View {
        void reloadData();
    }

    private List<Heroe> heroes = new ArrayList<>();
    private List<Heroe> currentheroes = new ArrayList<>();
    private GetCharacters getCharacters;

    public HomePresenter(AppNavigation appNavigation,GetCharacters getCharacters) {
        super(appNavigation);
        this.getCharacters = getCharacters;
    }

    public void getCharacters(){
        view.showLoading();
        getCharacters.subscribe(new BaseSubscriber<HeroeResponse>() {
            @Override
            public void onError(ApiError apiError) {
                super.onError(apiError);
                HomePresenter.this.view.hideLoading();
            }

            @Override
            public void onNext(HeroeResponse heroeResponse) {
                super.onNext(heroeResponse);
                HomePresenter.this.heroes = heroeResponse.getData().getResults();
                HomePresenter.this.currentheroes = heroeResponse.getData().getResults();
                HomePresenter.this.view.reloadData();
                HomePresenter.this.view.hideLoading();
            }
        });
    }

    /*
    Datasource
     */

    @Override
    public int getCount() {
        return currentheroes.size();
    }

    @Override
    public Object getItemAtPosition(int position) {
        return currentheroes.get(position);
    }

    public void filter(String query){
        if (query.isEmpty()){
            this.currentheroes = this.heroes;
        }else{
            List<Heroe> heroes = new ArrayList<>();
            for(Heroe heroe : this.heroes){
                if (heroe.getName().toLowerCase().contains(query)
                        || heroe.getDescription().toLowerCase().contains(query)){
                    heroes.add(heroe);
                }
            }
            this.currentheroes = heroes;
        }
        view.reloadData();
    }

    public void gotoDetailHeroe(int position){
        appNavigation.launchDetail(currentheroes.get(position));
    }

}
