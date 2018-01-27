package com.jorgesanmartin.sample.ui.view.base;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import android.widget.LinearLayout;

import butterknife.ButterKnife;

/**
 * Created by jorgesanmartin on 11/2/17.
 */

public abstract class BaseLinearLayout extends LinearLayout implements BaserView{

    public BaseLinearLayout(Context context) {
        this(context, null);
    }

    public BaseLinearLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public BaseLinearLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(attrs);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public BaseLinearLayout(Context context, AttributeSet attrs, int defStyleAttr, int
            defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(attrs);
    }

    protected void init(AttributeSet attrs) {
        int layout = getLayout();
        if (layout != 0) {
            inflate(getContext(), layout, this);
            ButterKnife.bind(this);
        }
        setBackgroundResource(android.R.color.white);
        initUI(attrs);
    }
}
