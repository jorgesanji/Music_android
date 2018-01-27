package com.jorgesanmartin.sample.ui.presenter.base;

import android.app.Activity;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;

/**
 * Created by jorgesanmartin on 11/3/17.
 */

public interface Presenter<V extends Presenter.View> {

    void attachView(V view);

    /**
     * Method that control the lifecycle of the view. It should be called in the view's
     * (Activity or Fragment) onResume() method.
     */
    void resume();

    /**
     * Method that control the lifecycle of the view. It should be called in the view's
     * (Activity or Fragment) onPause() method.
     */
    void pause();

    /**
     * Method that control the lifecycle of the view. It should be called in the view's
     * (Activity or Fragment) onDestroy() method.
     */
    void destroy();

    /**
     * Method that control the lifecycle of the view. It should be called in the view's
     * (Activity or Fragment) onActivityResult() method.
     */
    void onActivityResult(int requestCode, int resultCode, Intent data);

    /**
     * Default interface for screen common (most used) operations.
     */
    interface View {
        /**
         * @return The activity.
         */
        Activity getActivity();

        /**
         * @return The fragment, if any.
         */
        Fragment getFragment();

        /**
         * Add here your calls to your widget/library to show user info messages.
         *
         * @param message Message box text.
         * @param color
         */
        void showInfo(@NonNull String message, int color);

        /**
         * Add here your calls to your widget/library to show user warning messages.
         *
         * @param message Message box text.
         * @param color
         */
        void showWarning(@NonNull String message, int color);

        /**
         * Add here your calls to your widget/library to show user error messages.
         *
         * @param message Message box text.
         * @param color
         */
        void showError(@NonNull String message, int color);

        /**
         * Shows loading widget/library or another kind of loading view.
         */
        void showLoading();

        /**
         * Hides loading widget/library or another kind of loading view.
         */
        void hideLoading();

        /**
         * Sets activity title.
         * Add here your calls to your action bar/tool bar/widget to show the screen title.
         *
         * @param title The title.
         */
        void setTitle(CharSequence title);

        /**
         * Sets activity title.
         * Add here your calls to your action bar/tool bar/widget to show the screen title.
         *
         * @param stringResId The title res.
         */
        void setTitle(int stringResId);
    }
}