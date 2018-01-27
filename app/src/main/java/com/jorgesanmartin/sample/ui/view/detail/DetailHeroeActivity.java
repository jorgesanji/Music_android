package com.jorgesanmartin.sample.ui.view.detail;

import android.os.Bundle;

import com.f2prateek.dart.HensonNavigable;
import com.jorgesanmartin.sample.R;
import com.jorgesanmartin.sample.ui.presenter.detail.DetailHeroePresenter;
import com.jorgesanmartin.sample.ui.view.base.BaseActivity;

/**
 * Created by jorgesanmartin on 11/5/17.
 */

@HensonNavigable(model = DetailHeroePresenter.class)
public class DetailHeroeActivity extends BaseActivity<DetailHeroeFragment> {

    @Override
    public Class<DetailHeroeFragment> getFragment() {
        return DetailHeroeFragment.class;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public int toolbarColor() {
        return R.color.colorPrimary;
    }
}