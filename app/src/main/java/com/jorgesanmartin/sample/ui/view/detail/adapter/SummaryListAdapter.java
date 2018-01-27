package com.jorgesanmartin.sample.ui.view.detail.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.jorgesanmartin.sample.R;
import com.jorgesanmartin.sample.data.model.Summary;
import com.jorgesanmartin.sample.ui.utils.IODataSource;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by jorgesanmartin on 11/3/17.
 */

public class SummaryListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{

    private final IODataSource<Summary> dataSource;

    public SummaryListAdapter(IODataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.summary_item_lay, parent, false);
        return new SummaryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ((SummaryViewHolder)holder).bind(dataSource.getItemAtPosition(position));
    }

    @Override
    public int getItemCount() {
        if (dataSource != null){
          return  dataSource.getCount();
        }
        return 0;
    }

    public class SummaryViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.summaryname_tv)
        protected TextView mHeroSummary_tv;

        public SummaryViewHolder(final View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void bind(Summary item){
            mHeroSummary_tv.setText(item.getName());
        }
    }
}
