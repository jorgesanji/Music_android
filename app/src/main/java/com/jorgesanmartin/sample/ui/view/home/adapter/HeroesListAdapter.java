package com.jorgesanmartin.sample.ui.view.home.adapter;

import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.jorgesanmartin.sample.R;
import com.jorgesanmartin.sample.data.model.Heroe;
import com.jorgesanmartin.sample.ui.utils.IODataSource;
import com.jorgesanmartin.sample.ui.utils.ImageLoader;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by jorgesanmartin on 11/3/17.
 */

public class HeroesListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{

    private final IODataSource<Heroe> dataSource;

    public HeroesListAdapter(IODataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.heroe_item_lay, parent, false);
        return new HeroeViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ((HeroeViewHolder)holder).bind(dataSource.getItemAtPosition(position));
    }

    @Override
    public int getItemCount() {
        if (dataSource != null){
          return  dataSource.getCount();
        }
        return 0;
    }

    public class HeroeViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.heroe_imv)
        protected ImageView mheroeImv;
        @BindView(R.id.heroe_name_tv)
        protected TextView mheroNameTv;
        @BindView(R.id.heroe_description_tv)
        protected TextView mHeroeDescriptionTv;

        public HeroeViewHolder(final View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void bind(Heroe heroe){
            ImageLoader.loadRoundImage(mheroeImv, heroe.getImage());
            mheroNameTv.setText(heroe.getName());
            boolean hasDescription = heroe.getDescription() != null && !heroe.getDescription().isEmpty();
            mHeroeDescriptionTv.setVisibility(hasDescription ? View.VISIBLE : View.GONE);
            mHeroeDescriptionTv.setText(heroe.getDescription());
            mHeroeDescriptionTv.setEllipsize(TextUtils.TruncateAt.END);
            mHeroeDescriptionTv.setMaxLines(3);
        }
    }
}
