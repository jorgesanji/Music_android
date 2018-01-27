package com.jorgesanmartin.sample.ui.presenter.base;

import android.content.Intent;

import com.jorgesanmartin.sample.ui.view.AppNavigation;

/**
 * Created by jorgesanmartin on 11/3/17.
 */

public class PrensenterImpl<V extends  Presenter.View> implements Presenter {

    // Var
    protected AppNavigation appNavigation;
    // Views
    protected V view;

    public PrensenterImpl(AppNavigation appNavigation) {
        this.appNavigation = appNavigation;
    }

    @Override
    public void attachView(View view) {
        this.view = (V) view;
    }

    @Override
    public void resume() {

    }

    @Override
    public void pause() {

    }

    @Override
    public void destroy() {

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {

    }
}
