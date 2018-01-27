package com.jorgesanmartin.sample.ui.view.home;

import android.content.Context;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.View;

import com.jorgesanmartin.sample.R;
import com.jorgesanmartin.sample.data.model.Heroe;
import com.jorgesanmartin.sample.data.utils.RecyclerItemClickListener;
import com.jorgesanmartin.sample.ui.utils.IODataSource;
import com.jorgesanmartin.sample.ui.view.base.BaseLinearLayout;
import com.jorgesanmartin.sample.ui.view.home.adapter.HeroesListAdapter;

import butterknife.BindView;

/**
 * Created by jorgesanmartin on 11/2/17.
 */

public class HomeScreen extends BaseLinearLayout implements RecyclerItemClickListener.OnItemClickListener {

    public interface Listener{
        void itemPressed(int position);
    }

    private LinearLayoutManager mLayoutManager;
    private HeroesListAdapter mAdapter;
    private Listener listener;

    @BindView(R.id.heroeslist_rv)
    protected RecyclerView mRvContainer;

    public HomeScreen(Context context) {
        super(context);
    }

    @Override
    public int getLayout() {
        return R.layout.home_lay;
    }

    @Override
    public void initUI(AttributeSet attributeSet) {
        initLayoutManager();
        initListeners();
    }

    private void initListeners(){
        mRvContainer.addOnItemTouchListener(new RecyclerItemClickListener(getContext(), mRvContainer,this));
    }

    private void initLayoutManager() {
        this.mLayoutManager = new LinearLayoutManager(getContext());
        mLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRvContainer.setLayoutManager(mLayoutManager);
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(mRvContainer.getContext(),
                mLayoutManager.getOrientation());
        mRvContainer.addItemDecoration(dividerItemDecoration);
    }

    public void setDataSource(IODataSource<Heroe> dataSource) {
        mAdapter = new HeroesListAdapter(dataSource);
        mRvContainer.setAdapter(mAdapter);
        mAdapter.notifyDataSetChanged();
    }

    public void setListener(Listener listener) {
        this.listener = listener;
    }

    @Override
    public void onItemClick(View view, int position) {
        if(listener != null){
            listener.itemPressed(position);
        }
    }

    @Override
    public void onLongItemClick(View view, int position) {

    }

    public void reloadData(){
        mAdapter.notifyDataSetChanged();
    }
}
