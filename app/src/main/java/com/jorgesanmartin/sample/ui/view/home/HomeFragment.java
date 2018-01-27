package com.jorgesanmartin.sample.ui.view.home;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.jorgesanmartin.sample.di.InjectorHelper;
import com.jorgesanmartin.sample.ui.presenter.home.HomePresenter;
import com.jorgesanmartin.sample.ui.view.base.MVPFragment;
import com.jorgesanmartin.sample.ui.view.customviews.SearchView;

/**
 * Created by jorgesanmartin on 11/3/17.
 */

public class HomeFragment extends MVPFragment<HomePresenter, HomePresenter.View> implements SearchView.Listener, HomePresenter.View, HomeScreen.Listener{

    private SearchView mSearchView;
    private HomeScreen homeScreen;

    @Override
    protected void injectDependencies() {
        InjectorHelper.getPresenterComponent(getActivity()).inject(this);
    }

    @Override
    protected View getRootView() {
        homeScreen = new HomeScreen(getActivity());
        homeScreen.setListener(this);
        return homeScreen;
    }

    @Override
    protected void onDidAppear() {
        presenter.getCharacters();
        homeScreen.setDataSource(presenter);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addSearchBar();
    }

    private void addSearchBar() {
        Toolbar toolbar = ((HomeActivity) getActivity()).getToolbar();
        if (toolbar != null) {
            mSearchView = new SearchView(getActivity());
            mSearchView.setLayoutParams(new Toolbar.LayoutParams(Toolbar.LayoutParams
                    .MATCH_PARENT, Toolbar.LayoutParams.MATCH_PARENT));
            mSearchView.setListener(this);
            toolbar.addView(mSearchView);
            toolbar.setLogo(null);
        }
    }

    /*
    HomePresenter View
    */

    @Override
    public void reloadData() {
        homeScreen.reloadData();
    }

    /*
     Listener HomeScreen
     */

    @Override
    public void itemPressed(int position) {
        presenter.gotoDetailHeroe(position);
    }
    /*
     Listener search View
     */

    @Override
    public void searchText(String text) {
        presenter.filter(text);
    }
}

