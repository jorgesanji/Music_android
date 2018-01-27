package com.jorgesanmartin.sample.ui.view;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.util.Pair;

import com.jorgesanmartin.sample.R;
import com.jorgesanmartin.sample.data.model.Heroe;
import com.jorgesanmartin.sample.ui.view.detail.Henson;
import com.jorgesanmartin.sample.ui.view.home.HomeActivity;

/**
 * Created by jorgesanmartin on 11/2/17.
 */

public class AppNavigation {

    public static final String ANIMATION_ACTIVITY = "animation_activity";

    public enum ActivityAnimation {
        SLIDE_UP(new Pair(R.anim.anim_slide_in_up, R.anim.anim_hold), new Pair(R.anim.anim_hold, R
                .anim.anim_slide_out_down)),
        SLIDE_LEFT(new Pair(R.anim.anim_slide_in_left, R.anim.anim_hold), new Pair(R.anim
                .anim_hold, R.anim.anim_slide_out_left)),
        SLIDE_RIGHT(new Pair(R.anim.anim_slide_in_right, R.anim.anim_hold), new Pair(R.anim
                .anim_hold, R.anim.anim_slide_out_right)),
        FADE(new Pair(R.anim.fade_in_fast, R.anim.fade_out_fast), new Pair(R.anim
                .fade_in_fast, R.anim.fade_out_fast));

        private final Pair<Integer, Integer> anim_in;
        private final Pair<Integer, Integer> anim_out;

        ActivityAnimation(Pair anim_in, Pair anim_out) {
            this.anim_in = anim_in;
            this.anim_out = anim_out;
        }

        public Pair<Integer, Integer> getAnim_in() {
            return anim_in;
        }

        public Pair<Integer, Integer> getAnim_out() {
            return anim_out;
        }
    }

    private Activity activityScope;

    public AppNavigation(Activity activity) {
        this.activityScope = activity;
    }

    // ---------------------------- LAUNCH INTENT -------------------------------

    private Intent addAnimation(Intent intent, ActivityAnimation animation) {
        Bundle bundle = intent.getExtras();
        if (bundle == null) {
            bundle = new Bundle();
        }
        bundle.putSerializable(ANIMATION_ACTIVITY, animation);
        intent.putExtras(bundle);
        return intent;
    }

    private void startActivity(int enterAnim, int exitAnim, Intent intent) {
        activityScope.startActivity(intent);
        activityScope.overridePendingTransition(enterAnim, exitAnim);
    }

    private void startActivity(Intent intent, ActivityAnimation animation) {
        startActivity(animation.getAnim_in().first, animation.getAnim_in().second, addAnimation
                (intent, animation));
    }

    private void startActivity(Intent intent) {
        startActivity(intent, ActivityAnimation.FADE);
    }

    private void startActivityForResult(int enterAnim, int exitAnim, Intent intent, int code) {
        activityScope.startActivityForResult(intent, code);
        activityScope.overridePendingTransition(enterAnim, exitAnim);
    }

    private void startActivityForResult(Intent intent, int code, ActivityAnimation animation) {
        startActivityForResult(animation.getAnim_in().first, animation.getAnim_in().second,
                addAnimation(intent, animation), code);
    }

    private void startActivityForResult(Fragment fragment, int enterAnim, int exitAnim, Intent
            intent, int code) {
        fragment.startActivityForResult(intent, code);
        activityScope.overridePendingTransition(enterAnim, exitAnim);
    }

    // ---------------------------- CREATE INTENT -------------------------------

    private Intent newTask(@NonNull Class clazz, Bundle bundle) {
        Intent intent = newTask(clazz, bundle, false);
        return intent;
    }

    private Intent newTask(@NonNull Class clazz, Bundle bundle, boolean clearTop) {
        return newTask(clazz, bundle, clearTop, false);
    }

    private Intent newTask(@NonNull Class clazz, Bundle bundle, boolean clearTop, boolean
            clearTask) {
        Intent openIntent = new Intent(activityScope, clazz);
        if (bundle != null) {
            openIntent.putExtras(bundle);
        }
        if (clearTop) {
            openIntent.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
            openIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            openIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
            ActivityCompat.finishAffinity(activityScope);
        }
        if (clearTask) {
            openIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
        }
        return openIntent;
    }
    // ****************************
    //      INTENTS CREATION
    // ****************************

    private Intent home(){
        return newTask(HomeActivity.class, null, true);
    }

    private Intent detail(Heroe heroe){
        return Henson.with(activityScope).gotoDetailHeroeActivity().heroe(heroe).build();
    }

    // ****************************
    //      ACTIONS DEFINITION
    // ****************************

    public void launchHome(){
        startActivity(home());
    }

    public void launchDetail(Heroe heroe){
        startActivity(detail(heroe), ActivityAnimation.SLIDE_LEFT);
    }

    public void launchWebDetail(String url){
        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
        activityScope.startActivity(browserIntent);
    }
}
