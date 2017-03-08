package com.nhahv.lovecoupon.util;

import android.databinding.BindingAdapter;
import android.databinding.ObservableBoolean;
import android.graphics.drawable.Drawable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.AppCompatCheckBox;
import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.method.PasswordTransformationMethod;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.nhahv.lovecoupon.data.model.ProfileShop;
import com.nhahv.lovecoupon.ui.ViewModel;
import com.nhahv.lovecoupon.ui.shop.setting.UserType;

import static com.nhahv.lovecoupon.util.Constant.DataConstant.DATA_ADMIN;
import static com.nhahv.lovecoupon.util.Constant.DataConstant.URL_IMAGE;

/**
 * Created by Nhahv0902 on 3/6/2017.
 * <></>
 */
public final class DataBindingUtils {
    /*
    * bind image path file and url internet
    * */
    @BindingAdapter({"bind:circleImage", "bind:error"})
    public static void setCircleImage(final ImageView view, String url, final Drawable error) {
        Glide.with(view.getContext())
            .load(URL_IMAGE)
            .centerCrop()
            .diskCacheStrategy(DiskCacheStrategy.SOURCE)
            .placeholder(error)
            .dontAnimate()
            .into(view);
    }

    /*
    * bind SwipeRefreshLayout refresh
    * */

    @BindingAdapter({"bind:onRefresh", "bind:refresh"})
    public static void onRefresh(SwipeRefreshLayout view, ViewModel viewModel,
                                 ObservableBoolean isShow) {
        /*
        * true is show refresh
        * false is hide refresh
        * */
        view.setRefreshing(isShow.get());
        view.setOnRefreshListener(() -> {
            isShow.set(true);
            viewModel.loadData();
        });
    }

    /*
    * bind adapter of recycler with linearLayout
    * */
    @BindingAdapter({"bind:adapter"})
    public static void setAdapter(RecyclerView view, RecyclerView.Adapter adapter) {
        view.setLayoutManager(new LinearLayoutManager(view.getContext()));
        view.setAdapter(adapter);
        adapter.notifyDataSetChanged();
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
    public static void onCheckedAdmin(AppCompatCheckBox view, ProfileShop profile, UserType type) {
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

    /*
    * HistoryFragment
    * */
    /*
    * viewpager
    * */

    @BindingAdapter({"bind:adapterViewPager"})
    public static void setUpViewPager(ViewPager view, FragmentPagerAdapter adapter) {
        view.setAdapter(adapter);
    }

    /*
    * view pager of tab layout
     */
    @BindingAdapter({"bind:viewPagerTabLayout"})
    public static void setUpTabLayout(TabLayout view, ViewPager viewPager) {
        view.setupWithViewPager(viewPager);
    }
}
