package com.jorgesanmartin.sample.ui.view.detail;

import android.content.Context;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.jorgesanmartin.sample.R;
import com.jorgesanmartin.sample.data.model.Summary;
import com.jorgesanmartin.sample.ui.utils.IODataSource;
import com.jorgesanmartin.sample.ui.utils.ImageLoader;
import com.jorgesanmartin.sample.ui.view.base.BaseLinearLayout;
import com.jorgesanmartin.sample.ui.view.detail.adapter.SummaryListAdapter;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by jorgesanmartin on 11/2/17.
 */

public class DetailHeroeScreen extends BaseLinearLayout {

    public interface Listener{
        void wikiPressed();
        void detailPressed();
        void comicPressed();
        void comicListPressed();
        void eventListPressed();
    }

    private Listener listener;
    private LinearLayoutManager mLayoutManager;
    private SummaryListAdapter mAdapter;

    @BindView(R.id.heroe_imv)
    protected ImageView mheroeImv;
    @BindView(R.id.heroe_name_tv)
    protected TextView mheroNameTv;
    @BindView(R.id.heroe_description_tv)
    protected TextView mHeroeDescriptionTv;
    @BindView(R.id.links_tv)
    protected TextView links_tv;
    @BindView(R.id.details_bt)
    protected Button mDetails_bt;
    @BindView(R.id.wiki_bt)
    protected Button mWiki_bt;
    @BindView(R.id.comics_bt)
    protected Button mComincs_bt;
    @BindView(R.id.list_rv)
    protected RecyclerView mlist_rv;
    @BindView(R.id.comics_list_bt)
    protected Button mCominclist_bt;
    @BindView(R.id.events_list_bt)
    protected Button mEventsList_bt;

    public DetailHeroeScreen(Context context) {
        super(context);
    }

    @Override
    public int getLayout() {
        return R.layout.detail_lay;
    }

    @Override
    public void initUI(AttributeSet attributeSet) {
        setOrientation(VERTICAL);
        setGravity(Gravity.CENTER|Gravity.TOP);
        mDetails_bt.setVisibility(GONE);
        mWiki_bt.setVisibility(GONE);
        mComincs_bt.setVisibility(GONE);
        links_tv.setVisibility(GONE);
        setBackGroundButton(mCominclist_bt, true);
        setBackGroundButton(mEventsList_bt, false);
        initLayoutManager();
    }

    private void initLayoutManager() {
        this.mLayoutManager = new LinearLayoutManager(getContext());
        mLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mlist_rv.setLayoutManager(mLayoutManager);
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(mlist_rv.getContext(),
                mLayoutManager.getOrientation());
        mlist_rv.addItemDecoration(dividerItemDecoration);
    }

    private void setBackGroundButton(Button button, boolean enable){
        button.setBackgroundResource(enable
                ? R.drawable.background_selector_state_selected
                : R.drawable.background_selector_state_normal);
    }

    private void showButton(Button button){
        links_tv.setVisibility(VISIBLE);
        button.setVisibility(VISIBLE);
    }

    public void setListener(Listener listener) {
        this.listener = listener;
    }

    public void setHeroeName(String name){
        mheroNameTv.setText(name);
    }

    public void setHeroeDescription(String description){
        boolean hasDescription = description != null && !description.isEmpty();
        mHeroeDescriptionTv.setVisibility(hasDescription ? View.VISIBLE:View.GONE);
        mHeroeDescriptionTv.setText(description);
    }

    public void setHeroeImage(String image){
        ImageLoader.loadRoundImage(mheroeImv,image);
    }

    public void showWikiButton(){
        showButton(mWiki_bt);
    }

    public void showComicsButton(){
        showButton(mComincs_bt);
    }

    public void showDetailsButton(){
        showButton(mDetails_bt);
    }

    @OnClick(R.id.details_bt)
    protected void detailPressed(){
        listener.detailPressed();
    }

    @OnClick(R.id.wiki_bt)
    protected void wikiPressed(){
        listener.wikiPressed();
    }

    @OnClick(R.id.comics_bt)
    protected void comicPressed(){
        listener.comicPressed();
    }

    @OnClick(R.id.comics_list_bt)
    protected void comicListPressed(){
        listener.comicListPressed();
        setBackGroundButton(mCominclist_bt, true);
        setBackGroundButton(mEventsList_bt, false);
    }

    @OnClick(R.id.events_list_bt)
    protected void eventListPressed(){
        listener.eventListPressed();
        setBackGroundButton(mCominclist_bt, false);
        setBackGroundButton(mEventsList_bt, true);
    }

    public void reloadData(){
        if (mAdapter != null) {
            mAdapter.notifyDataSetChanged();
        }
    }

    public void setDataSource(IODataSource<Summary> dataSource) {
        mAdapter = new SummaryListAdapter(dataSource);
        mlist_rv.setAdapter(mAdapter);
        mAdapter.notifyDataSetChanged();
    }

    public void setTotalComics(String totalComics) {
        mCominclist_bt.setText(totalComics);
    }

    public void setTotalEvents(String totalEvents) {
        mEventsList_bt.setText(totalEvents);
    }
}
