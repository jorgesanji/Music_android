package com.jorgesanmartin.sample.ui.presenter.detail;

import com.f2prateek.dart.InjectExtra;
import com.jorgesanmartin.sample.R;
import com.jorgesanmartin.sample.data.model.Heroe;
import com.jorgesanmartin.sample.data.model.HeroeLinkType;
import com.jorgesanmartin.sample.data.model.HeroeUrl;
import com.jorgesanmartin.sample.data.model.Summary;
import com.jorgesanmartin.sample.ui.presenter.base.PrensenterImpl;
import com.jorgesanmartin.sample.ui.presenter.base.Presenter;
import com.jorgesanmartin.sample.ui.utils.IODataSource;
import com.jorgesanmartin.sample.ui.view.AppNavigation;

/**
 * Created by jorgesanmartin on 11/3/17.
 */

public class DetailHeroePresenter extends PrensenterImpl<DetailHeroePresenter.View> implements
        IODataSource<Summary>{

    public interface View extends Presenter.View {
        void setHeroeName(String name);
        void setHeroeDescription(String name);
        void setHeroeImage(String image);
        void showWikiButton();
        void showComicsButton();
        void showDetailsButton();
        void setTotalEvents(String totalEvents);
        void setTotalComics(String totalComics);
        void reloadData();
    }

    @InjectExtra Heroe heroe;
    private boolean isEventsList;

    public DetailHeroePresenter(AppNavigation appNavigation) {
        super(appNavigation);
        this.isEventsList = false;
    }

    private String getLink(HeroeLinkType type){
        for(HeroeUrl heroeUrl :heroe.getUrls()){
            if (heroeUrl.getType() == type) {
                return heroeUrl.getUrl();
            }
        }
        return "";
    }

    public void getHeroeInfoDetail(){
        view.setHeroeName(heroe.getName());
        view.setHeroeDescription(heroe.getDescription());
        view.setHeroeImage(heroe.getImage());
        view.getActivity().setTitle(heroe.getName());
        for(HeroeUrl heroeUrl :heroe.getUrls()){
            if (heroeUrl.getType() == HeroeLinkType.COMIC){
                view.showComicsButton();
            }else if (heroeUrl.getType() == HeroeLinkType.DETAIL){
                view.showDetailsButton();
            }else{
                view.showWikiButton();
            }
        }
        view.setTotalComics(String.format(view.getActivity().getString(R.string.detail_comics_list_title), heroe.getComics().getItems().size()));
        view.setTotalEvents(String.format(view.getActivity().getString(R.string.detail_events_list_title), heroe.getEvents().getItems().size()));
        view.reloadData();
    }

    public void showDetailLink(){
        appNavigation.launchWebDetail(getLink(HeroeLinkType.DETAIL));
    }

    public void showWikiLink(){
        appNavigation.launchWebDetail(getLink(HeroeLinkType.DETAIL));
    }

    public void showComicLink(){
        appNavigation.launchWebDetail(getLink(HeroeLinkType.DETAIL));
    }

    public void showEventList(){
        this.isEventsList = true;
        view.reloadData();
    }

    public void showComicList(){
        this.isEventsList = false;
        view.reloadData();
    }

    @Override
    public Summary getItemAtPosition(int position) {
        if (isEventsList){
            return heroe.getEvents().getItems().get(position);
        }
        return heroe.getComics().getItems().get(position);
    }

    @Override
    public int getCount() {
        if (isEventsList){
            return heroe.getEvents().getItems().size();
        }
       return heroe.getComics().getItems().size();
    }
}
