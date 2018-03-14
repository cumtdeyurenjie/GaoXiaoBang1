package com.example.yrj.gaoxiaobang1;

import android.content.Context;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;
import com.youth.banner.loader.ImageLoader;

/**
 * Created by YRJ on 2018/3/10.
 */

public class GlideImageLoader extends ImageLoader{
    @Override
    public void displayImage(Context context, Object path, ImageView imageView) {
        Picasso.with(context).load((Integer) path).into(imageView);
    }
}
