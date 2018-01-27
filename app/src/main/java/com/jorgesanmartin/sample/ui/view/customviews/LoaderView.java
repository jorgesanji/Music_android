package com.jorgesanmartin.sample.ui.view.customviews;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Paint;
import android.graphics.drawable.ClipDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.graphics.drawable.ShapeDrawable;
import android.support.annotation.ColorInt;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.Interpolator;
import android.widget.ProgressBar;

import com.jorgesanmartin.sample.R;

/**
 * Created by jorgesanmartin on 11/5/17.
 */

public class LoaderView extends ProgressBar {

    private static final int INDETERMINATE_MAX = 6000;
    private static final String SECONDARY_PROGRESS = "secondaryProgress";
    private static final int DURATION = 1500;
    private static final String PROGRESS = "progress";

    private Animator animator = null;

    public LoaderView(Context context) {
        this(context,null);
    }

    public LoaderView(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public LoaderView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        int backgroundColour;
        int progressColour;
        backgroundColour = ContextCompat.getColor(context, R.color.pale_gray);
        progressColour = ContextCompat.getColor(context, R.color.pine_green);
        Resources resources = context.getResources();
        setProgressDrawable(resources.getDrawable(android.R.drawable.progress_horizontal));
        createIndeterminateProgressDrawable(backgroundColour, progressColour);
        setMax(INDETERMINATE_MAX);
        super.setIndeterminate(false);
        this.setIndeterminate(true);
    }

    private void createIndeterminateProgressDrawable(@ColorInt int backgroundColour, @ColorInt
            int progressColour) {
        LayerDrawable layerDrawable = (LayerDrawable) getProgressDrawable();
        if (layerDrawable != null) {
            layerDrawable.mutate();
            layerDrawable.setDrawableByLayerId(android.R.id.background, createShapeDrawable
                    (backgroundColour));
            layerDrawable.setDrawableByLayerId(android.R.id.progress, createClipDrawable
                    (backgroundColour));
            layerDrawable.setDrawableByLayerId(android.R.id.secondaryProgress, createClipDrawable
                    (progressColour));
        }
    }

    private Drawable createClipDrawable(@ColorInt int colour) {
        ShapeDrawable shapeDrawable = createShapeDrawable(colour);
        return new ClipDrawable(shapeDrawable, Gravity.START, ClipDrawable.HORIZONTAL);
    }

    private ShapeDrawable createShapeDrawable(@ColorInt int colour) {
        ShapeDrawable shapeDrawable = new ShapeDrawable();
        setColour(shapeDrawable, colour);
        return shapeDrawable;
    }

    private void setColour(ShapeDrawable drawable, int colour) {
        Paint paint = drawable.getPaint();
        paint.setColor(colour);
    }

    @Override
    public synchronized void setIndeterminate(boolean indeterminate) {
        if (isStarted()) {
            return;
        }
        animator = createIndeterminateAnimator();
        animator.setTarget(this);
        animator.start();
    }

    private boolean isStarted() {
        return animator != null && animator.isStarted();
    }

    private Animator createIndeterminateAnimator() {
        AnimatorSet set = new AnimatorSet();
        Animator progressAnimator = getAnimator(SECONDARY_PROGRESS, new DecelerateInterpolator());
        Animator secondaryProgressAnimator = getAnimator(PROGRESS, new AccelerateInterpolator());
        set.playTogether(progressAnimator, secondaryProgressAnimator);
        set.setDuration(DURATION);
        return set;
    }

    @NonNull
    private ObjectAnimator getAnimator(String propertyName, Interpolator interpolator) {
        ObjectAnimator progressAnimator = ObjectAnimator.ofInt(this, propertyName, 0,
                INDETERMINATE_MAX);
        progressAnimator.setInterpolator(interpolator);
        progressAnimator.setDuration(DURATION);
        progressAnimator.setRepeatMode(ValueAnimator.RESTART);
        progressAnimator.setRepeatCount(ValueAnimator.INFINITE);
        return progressAnimator;
    }
}