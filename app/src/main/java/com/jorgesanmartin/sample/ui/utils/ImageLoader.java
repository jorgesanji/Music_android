package com.jorgesanmartin.sample.ui.utils;

import android.widget.ImageView;

import com.squareup.picasso.Picasso;
import com.squareup.picasso.RequestCreator;

/**
 * Created by jorgesanmartin on 11/5/17.
 */
public class ImageLoader {

    public static void loadRoundImage(ImageView imageView, String filePath) {
        loadImage(imageView,filePath, true);
    }

    public static void loadImage(ImageView imageView, String filePath) {
        loadImage(imageView,filePath, false);
    }

    public static void loadImage(ImageView imageView, String imagePath, boolean customRound) {
        RequestCreator creator = Picasso.with(imageView.getContext()).load(imagePath);
        if (customRound){
            creator.transform(new CircleTransform(customRound));
        }
        creator.fit().centerCrop();
        creator.centerCrop();
        creator.into(imageView);
    }
}
