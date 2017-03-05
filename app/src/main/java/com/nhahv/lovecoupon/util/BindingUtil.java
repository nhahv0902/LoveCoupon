package com.nhahv.lovecoupon.util;

import android.databinding.BindingAdapter;
import android.graphics.drawable.Drawable;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

/**
 * Created by Nhahv0902 on 3/6/2017.
 * <></>
 */
public final class BindingUtil {
    @BindingAdapter({"bind:circleImage", "bind:error"})
    public static void setCircleImage(ImageView view, String url, Drawable error) {
        Glide.with(view.getContext()).load(url).placeholder(error).error(error).into(view);
    }
}
