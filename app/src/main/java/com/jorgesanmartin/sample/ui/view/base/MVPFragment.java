package com.jorgesanmartin.sample.ui.view.base;

import android.content.Intent;
import android.os.Bundle;

import com.f2prateek.dart.Dart;
import com.jorgesanmartin.sample.ui.presenter.base.PrensenterImpl;

import javax.inject.Inject;

/**
 * Common functionalities for fragments.
 * Handles life cycle of presenters.
 */

public abstract class MVPFragment<P extends PrensenterImpl<V>, V extends PrensenterImpl.View > extends BaseFragment {

    @Inject
    protected P presenter;

    protected abstract void injectDependencies();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        injectDependencies();
        presenter.attachView(this);
        Dart.inject(presenter, getActivity());
    }

    @Override
    public void onPause() {
        super.onPause();
        if (presenter != null) {
            presenter.pause();
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        if (presenter != null) {
            presenter.resume();
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (presenter != null) {
            presenter.destroy();
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (presenter != null) {
            presenter.onActivityResult(requestCode, resultCode, data);
        }
    }

    @Override
    public MVPFragment getFragment() {
        return this;
    }
}