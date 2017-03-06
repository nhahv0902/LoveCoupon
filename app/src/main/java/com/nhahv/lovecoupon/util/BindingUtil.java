package com.nhahv.lovecoupon.util;

import android.databinding.BindingAdapter;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

/**
 * Created by Nhahv0902 on 3/6/2017.
 * <></>
 */
public final class BindingUtil {
    /*
    * bind image path file and url internet
    * */
    @BindingAdapter({"bind:circleImage", "bind:error"})
    public static void setCircleImage(final ImageView view, String url, final Drawable error) {
        Glide.with(view.getContext())
            .load(url)
            .error(error)
            .placeholder(error)
            .thumbnail(0.5f)
            .into(view);
    }

    /*
    * bind adapter of recycler with linearLayout
    * */
    @BindingAdapter({"bind:adapter"})
    public static void setAdapter(RecyclerView view, RecyclerView.Adapter adapter) {
        view.setLayoutManager(new LinearLayoutManager(view.getContext()));
        view.setAdapter(adapter);
    }
}
