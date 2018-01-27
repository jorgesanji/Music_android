package com.jorgesanmartin.sample.ui.view.base;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jorgesanmartin.sample.ui.presenter.base.Presenter;

/**
 * Created by jorgesanmartin on 11/3/17.
 */

public abstract class BaseFragment extends Fragment implements Presenter.View{

    boolean readyInitialized = false;

    protected abstract View getRootView();
    protected abstract void onDidAppear();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return getRootView();
    }

    @Override
    public void onResume() {
        super.onResume();
        if (!readyInitialized) {
            onDidAppear();
            readyInitialized = true;
        }
    }

    @Override
    public void showInfo(@NonNull String message, int color) {
        if (getActivity() instanceof Presenter.View) {
            ((Presenter.View) getActivity()).showInfo(message, color);
        } else {
            makeBar(message, (color == 0) ? Color.GREEN : color);
        }
    }

    @Override
    public void showWarning(@NonNull String message, int color) {
        if (getActivity() instanceof Presenter.View) {
            ((Presenter.View) getActivity()).showWarning(message, color);
        } else {
            makeBar(message, (color == 0) ? Color.YELLOW : color);
        }
    }

    @Override
    public void showError(@NonNull String message, int color) {
        if (getActivity() instanceof Presenter.View) {
            ((Presenter.View) getActivity()).showError(message, color);
        } else {
            makeBar(message, (color == 0) ? Color.RED : color);
        }
    }

    private void makeBar(String text, int color) {
        Snackbar snackbar = Snackbar.make(getView(), text, Snackbar.LENGTH_LONG);
        ViewGroup group = (ViewGroup) snackbar.getView();
        group.setBackgroundColor(getResources().getColor(color));
        snackbar.show();
    }

    @Override
    public void showLoading() {
        ((BaseActivity)getActivity()).showLoading();
    }

    @Override
    public void hideLoading() {
        ((BaseActivity)getActivity()).hideLoading();
    }

    @Override
    public void setTitle(CharSequence title) {
        getActivity().setTitle(title);
    }

    @Override
    public void setTitle(int stringResId) {
        getActivity().setTitle(stringResId);
    }

}
