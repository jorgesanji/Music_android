package com.jorgesanmartin.sample.ui.view.base;

import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.jorgesanmartin.sample.R;
import com.jorgesanmartin.sample.ui.view.AppNavigation;
import com.jorgesanmartin.sample.ui.view.customviews.LoaderView;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.jorgesanmartin.sample.ui.view.AppNavigation.ANIMATION_ACTIVITY;

/**
 * Created by jorgesanmartin on 11/3/17.
 */

public abstract class BaseActivity <F extends MVPFragment> extends AppCompatActivity{

    protected F currentFragment;

    public abstract Class<F> getFragment();

    public abstract int toolbarColor();

    @BindView(R.id.toolbar)
    protected Toolbar mToolbar;

    @BindView(R.id.progress_lv)
    protected LoaderView loaderView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lay);
        ButterKnife.bind(this);
        initFragment();
        initToolBar();
    }

    protected void initToolBar(){
        setSupportActionBar(mToolbar);
        setToolBarBackgroundColor(toolbarColor());
        setStatusColor(ContextCompat.getColor(this, R
                .color.colorPrimary));
    }

    protected void initFragment() {
        this.currentFragment = (F) Fragment.instantiate(this, getFragment().getName());
        if (currentFragment != null) {
            currentFragment.setArguments(getIntent().getExtras());
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.add(R.id.contentFrame, currentFragment);
            transaction.commit();
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
        }
        return super.onOptionsItemSelected(menuItem);
    }

    @Override
    public void finish() {
        super.finish();
        AppNavigation.ActivityAnimation activityAnimation = (AppNavigation.ActivityAnimation)
                getIntent().getSerializableExtra(ANIMATION_ACTIVITY);
        if (activityAnimation == null) {
            activityAnimation = AppNavigation.ActivityAnimation.FADE;
        }
        overridePendingTransition(activityAnimation.getAnim_out().first, activityAnimation
                .getAnim_out().second);
    }

    @Override
    public void setTitle(CharSequence title) {
        getSupportActionBar().setTitle(title);
    }

    public Toolbar getToolbar(){
        return  mToolbar;
    }

    public void setToolBarBackgroundColor(int color){
        mToolbar.setBackgroundColor(ContextCompat.getColor(this, color));
    }

    public void showLoading() {
        loaderView.setVisibility(View.VISIBLE);
    }

    public void hideLoading() {
        loaderView.setVisibility(View.GONE);
    }

    public void setStatusColor(int color) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = this.getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(color);
        }
    }
}

