package com.jorgesanmartin.sample.ui.view.customviews;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.Gravity;
import android.widget.EditText;

import com.jorgesanmartin.sample.R;
import com.jorgesanmartin.sample.ui.view.base.BaseLinearLayout;

import butterknife.BindView;

/**
 * Created by jorgesanmartin on 11/3/17.
 */

public class SearchView extends BaseLinearLayout implements  TextWatcher{

    public interface  Listener {
        void searchText(String text);
    }

    private Listener listener;
    @BindView(R.id.search_edt)
    protected EditText mEtSearch;

    public SearchView(Context context) {
        super(context);
    }

    @Override
    public int getLayout() {
        return R.layout.search_lay;
    }

    @Override
    public void initUI(AttributeSet attributeSet) {
        setGravity(Gravity.CENTER);
        setOrientation(VERTICAL);
        setListeners();
        mEtSearch.clearFocus();
    }

    private void setListeners(){
        mEtSearch.addTextChangedListener(this);
    }

    @Override
    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    @Override
    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        if (listener != null){
            listener.searchText(charSequence.toString());
        }
    }

    @Override
    public void afterTextChanged(Editable editable) {

    }

    public void setListener(Listener listener) {
        this.listener = listener;
    }
}
