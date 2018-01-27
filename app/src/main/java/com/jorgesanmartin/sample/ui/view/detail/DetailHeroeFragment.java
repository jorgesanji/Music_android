package com.jorgesanmartin.sample.ui.view.detail;

import android.view.View;

import com.jorgesanmartin.sample.di.InjectorHelper;
import com.jorgesanmartin.sample.ui.presenter.detail.DetailHeroePresenter;
import com.jorgesanmartin.sample.ui.view.base.MVPFragment;

/**
 * Created by jorgesanmartin on 11/3/17.
 */

public class DetailHeroeFragment extends MVPFragment<DetailHeroePresenter, DetailHeroePresenter.View> implements DetailHeroePresenter.View, DetailHeroeScreen.Listener {

    private DetailHeroeScreen detailHeroeScreen;

    @Override
    protected void injectDependencies() {
        InjectorHelper.getPresenterComponent(getActivity()).inject(this);
    }

    @Override
    protected View getRootView() {
        detailHeroeScreen = new DetailHeroeScreen(getActivity());
        detailHeroeScreen.setListener(this);
        return detailHeroeScreen;
    }

    @Override
    protected void onDidAppear() {
        presenter.getHeroeInfoDetail();
        detailHeroeScreen.setDataSource(presenter);
    }

    /*
    DetailHeroePresenter View
    */

    @Override
    public void setHeroeName(String name) {
        detailHeroeScreen.setHeroeName(name);
    }

    @Override
    public void setHeroeDescription(String name) {
        detailHeroeScreen.setHeroeDescription(name);
    }

    @Override
    public void setHeroeImage(String image) {
        detailHeroeScreen.setHeroeImage(image);
    }

    @Override
    public void showWikiButton(){
        detailHeroeScreen.showWikiButton();
    }

    @Override
    public void showComicsButton(){
        detailHeroeScreen.showComicsButton();
    }

    @Override
    public void showDetailsButton(){
        detailHeroeScreen.showDetailsButton();
    }

    @Override
    public void reloadData() {
        detailHeroeScreen.reloadData();
    }

    @Override
    public void setTotalComics(String totalComics) {
        detailHeroeScreen.setTotalComics(totalComics);
    }

    @Override
    public void setTotalEvents(String totalEvents) {
        detailHeroeScreen.setTotalEvents(totalEvents);
    }

    /*
     Listener DetailHeroeScreen
     */

    @Override
    public void detailPressed() {
        presenter.showDetailLink();
    }

    @Override
    public void wikiPressed() {
        presenter.showWikiLink();
    }

    @Override
    public void comicPressed() {
        presenter.showComicLink();
    }

    @Override
    public void comicListPressed() {
        presenter.showComicList();
    }

    @Override
    public void eventListPressed() {
        presenter.showEventList();
    }
}

