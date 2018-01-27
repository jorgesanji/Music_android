package com.jorgesanmartin.sample.ui.utils;

import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;

import com.squareup.picasso.Transformation;

/**
 * Created by jorgesanmartin on 11/5/17.
 */

public class CircleTransform implements Transformation {

    private static final float TWO = 2;
    public static final float RADIUS = 20;
    private boolean round;

    public CircleTransform(boolean round) {
        this.round = round;
    }

    @Override
    public Bitmap transform(Bitmap source) {
        int size = Math.min(source.getWidth(), source.getHeight());
        int x = (int) ((source.getWidth() - size) / TWO);
        int y = (int) ((source.getHeight() - size) / TWO);
        Bitmap squaredBitmap = Bitmap.createBitmap(source, x, y, size, size);
        if (squaredBitmap != source) {
            source.recycle();
        }

        Bitmap bitmap = Bitmap.createBitmap(size, size, source.getConfig());
        Canvas canvas = new Canvas(bitmap);
        Paint paint = new Paint();
        BitmapShader shader = new BitmapShader(squaredBitmap,
                BitmapShader.TileMode.CLAMP, BitmapShader.TileMode.CLAMP);
        paint.setShader(shader);
        paint.setAntiAlias(true);

        if (round) {
            float radius = size / TWO;
            canvas.drawCircle(radius, radius, radius, paint);
        } else {
            final RectF rect = new RectF();
            rect.set(0, 0, squaredBitmap.getWidth(), squaredBitmap.getHeight());
            canvas.drawRoundRect(rect, RADIUS, RADIUS, paint);
        }

        squaredBitmap.recycle();

        return bitmap;
    }

    @Override
    public String key() {
        return CircleTransform.class.getName();
    }
}