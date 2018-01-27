package com.jorgesanmartin.sample.ui.view.home;

import android.os.Bundle;

import com.jorgesanmartin.sample.R;
import com.jorgesanmartin.sample.ui.view.base.BaseActivity;

public class HomeActivity extends BaseActivity<HomeFragment> {

    @Override
    public Class<HomeFragment> getFragment() {
        return HomeFragment.class;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle(getString(R.string.title_home));
    }

    @Override
    public int toolbarColor() {
        return android.R.color.white;
    }
}
