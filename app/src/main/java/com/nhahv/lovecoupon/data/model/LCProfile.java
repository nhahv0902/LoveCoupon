package com.nhahv.lovecoupon.data.model;

import android.databinding.BaseObservable;
import android.databinding.Bindable;
import com.google.gson.annotations.SerializedName;
import com.nhahv.lovecoupon.BR;

/**
 * Created by Nhahv0902 on 3/24/2017.
 * <></>
 */
public class LCProfile extends BaseObservable {
    @SerializedName("company_id")
    private String mId;
    @SerializedName("name")
    private String mName;
    @SerializedName("logo_link")
    private String mLogoLink;
    private String mSocial;

    public LCProfile() {
    }

    public LCProfile(String id, String name, String logoLink, String social) {
        mId = id;
        mName = name;
        mLogoLink = logoLink;
        mSocial = social;
    }

    @Bindable
    public String getId() {
        return mId;
    }

    public void setId(String id) {
        mId = id;
        notifyPropertyChanged(BR.id);
    }

    @Bindable
    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
        notifyPropertyChanged(BR.name);
    }

    @Bindable
    public String getLogoLink() {
        return mLogoLink;
    }

    public void setLogoLink(String logoLink) {
        mLogoLink = logoLink;
        notifyPropertyChanged(BR.logoLink);
    }

    @Bindable
    public String getSocial() {
        return mSocial;
    }

    public void setSocial(String social) {
        mSocial = social;
        notifyPropertyChanged(BR.social);
    }
}
