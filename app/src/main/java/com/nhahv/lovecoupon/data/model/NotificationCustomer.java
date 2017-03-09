package com.nhahv.lovecoupon.data.model;

import android.databinding.Bindable;

import com.android.databinding.library.baseAdapters.BR;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Nhahv0902 on 3/10/2017.
 * <></>
 */
public class NotificationCustomer extends Notification {
    @SerializedName("name")
    private String mName;
    @SerializedName("logo_link")
    private String mLogo;

    @Bindable
    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
        notifyPropertyChanged(BR.name);
    }

    @Bindable
    public String getLogo() {
        return mLogo;
    }

    public void setLogo(String logo) {
        mLogo = logo;
        notifyPropertyChanged(BR.logo);
    }
}
