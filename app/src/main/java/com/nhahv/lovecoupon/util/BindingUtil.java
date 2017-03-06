package com.nhahv.lovecoupon.util;

import android.databinding.BindingAdapter;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.AppCompatCheckBox;
import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.method.PasswordTransformationMethod;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.nhahv.lovecoupon.data.model.ShopProfile;
import com.nhahv.lovecoupon.ui.shop.setting.UserType;

import static com.nhahv.lovecoupon.util.Constant.DataConstant.DATA_ADMIN;

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

    /*
    * SettingFragment
    * */

    @BindingAdapter({"bind:showPassword"})
    public static void setShowPassword(AppCompatImageView view, AppCompatEditText edit) {
        edit.setSelection(edit.getText().length());
        view.setOnClickListener(v -> {
            if (edit.getTransformationMethod() != null) edit.setTransformationMethod(null);
            else edit.setTransformationMethod(new PasswordTransformationMethod());
            edit.setSelection(edit.getText().length());
        });
    }

    /*
    * check admin setting fragment
    * */
    @BindingAdapter({"bind:profile", "bind:type"})
    public static void onCheckedAdmin(AppCompatCheckBox view, ShopProfile profile, UserType type) {
        switch (type) {
            case USER1:
                if (profile.getUserAdmin1() == null) view.setChecked(false);
                else view.setChecked(profile.getUserAdmin1().equals(DATA_ADMIN));
                break;
            case USER2:
                if (profile.getUserAdmin2() == null) view.setChecked(false);
                else view.setChecked(profile.getUserAdmin2().equals(DATA_ADMIN));
                break;
            default:
                break;
        }
        view.setOnCheckedChangeListener((buttonView, isChecked) -> {
            switch (type) {
                case USER1:
                    profile.setUserAdmin1(isChecked ? DATA_ADMIN : null);
                    break;
                case USER2:
                    profile.setUserAdmin2(isChecked ? DATA_ADMIN : null);
                    break;
                default:
                    break;
            }
        });
    }
}
