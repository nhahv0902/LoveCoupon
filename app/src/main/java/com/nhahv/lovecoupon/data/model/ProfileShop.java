package com.nhahv.lovecoupon.data.model;

import android.databinding.BaseObservable;
import android.databinding.Bindable;

import com.google.gson.annotations.SerializedName;
import com.nhahv.lovecoupon.BR;

/**
 * Created by Nhahv0902 on 3/7/2017.
 * <></>
 */
public class ProfileShop extends BaseObservable {
    @SerializedName("company_id")
    private String mShopId;
    @SerializedName("name")
    private String mName;
    @SerializedName("address")
    private String mAddress;
    @SerializedName("logo")
    private String mLogo;
    @SerializedName("user_id")
    private String mUserId;
    @SerializedName("user1")
    private String mUser1;
    @SerializedName("pass1")
    private String mPassword1;
    @SerializedName("user1_admin")
    private String mUserAdmin1;
    @SerializedName("user2")
    private String mUser2;
    @SerializedName("pass2")
    private String mPassword2;
    @SerializedName("user2_admin")
    private String mUserAdmin2;
    @SerializedName("logo_link")
    private String mLogoLink;
    @SerializedName("city")
    private String mCity;
    @SerializedName("country_name")
    private String mCountryName;
    @SerializedName("web_token")
    private String mToken;

    public void setShopId(String shopId) {
        mShopId = shopId;
        notifyPropertyChanged(BR.shopId);
    }

    public void setName(String name) {
        mName = name;
        notifyPropertyChanged(BR.name);
    }

    public void setAddress(String address) {
        mAddress = address;
        notifyPropertyChanged(BR.address);
    }

    public void setLogo(String logo) {
        mLogo = logo;
        notifyPropertyChanged(BR.logo);
    }

    public void setUserId(String userId) {
        mUserId = userId;
        notifyPropertyChanged(BR.userId);
    }

    public void setUser1(String user1) {
        mUser1 = user1;
        notifyPropertyChanged(BR.user1);
    }

    public void setPassword1(String password1) {
        mPassword1 = password1;
        notifyPropertyChanged(BR.password1);
    }

    public void setUserAdmin1(String userAdmin1) {
        mUserAdmin1 = userAdmin1;
        notifyPropertyChanged(BR.userAdmin1);
    }

    public void setUser2(String user2) {
        mUser2 = user2;
        notifyPropertyChanged(BR.user2);
    }

    public void setPassword2(String password2) {
        mPassword2 = password2;
        notifyPropertyChanged(BR.password2);
    }

    public void setUserAdmin2(String userAdmin2) {
        mUserAdmin2 = userAdmin2;
        notifyPropertyChanged(BR.userAdmin2);
    }

    public void setLogoLink(String logoLink) {
        mLogoLink = logoLink;
        notifyPropertyChanged(BR.logoLink);
    }

    public void setCity(String city) {
        mCity = city;
        notifyPropertyChanged(BR.city);
    }

    public void setCountryName(String countryName) {
        mCountryName = countryName;
        notifyPropertyChanged(BR.countryName);
    }

    public void setToken(String token) {
        mToken = token;
        notifyPropertyChanged(BR.token);
    }

    @Bindable
    public String getShopId() {
        return mShopId;
    }

    @Bindable
    public String getName() {
        return mName;
    }

    @Bindable
    public String getAddress() {
        return mAddress;
    }

    @Bindable
    public String getLogo() {
        return mLogo;
    }

    @Bindable
    public String getUserId() {
        return mUserId;
    }

    @Bindable
    public String getUser1() {
        return mUser1;
    }

    @Bindable
    public String getPassword1() {
        return mPassword1;
    }

    @Bindable
    public String getUserAdmin1() {
        return mUserAdmin1;
    }

    @Bindable
    public String getUser2() {
        return mUser2;
    }

    @Bindable
    public String getPassword2() {
        return mPassword2;
    }

    @Bindable
    public String getUserAdmin2() {
        return mUserAdmin2;
    }

    @Bindable
    public String getLogoLink() {
        return mLogoLink;
    }

    @Bindable
    public String getCity() {
        return mCity;
    }

    @Bindable
    public String getCountryName() {
        return mCountryName;
    }

    @Bindable
    public String getToken() {
        return mToken;
    }
}
