package com.jorgesanmartin.sample.ui.view.splash;

import android.content.Context;
import android.util.AttributeSet;
import android.view.Gravity;

import com.jorgesanmartin.sample.R;
import com.jorgesanmartin.sample.ui.view.base.BaseLinearLayout;

/**
 * Created by jorgesanmartin on 11/2/17.
 */

public class SplashScreen extends BaseLinearLayout {

    public SplashScreen(Context context) {
        super(context);
    }

    @Override
    public int getLayout() {
        return R.layout.splash_lay;
    }

    @Override
    public void initUI(AttributeSet attributeSet) {
        setGravity(Gravity.CENTER);
    }
}
