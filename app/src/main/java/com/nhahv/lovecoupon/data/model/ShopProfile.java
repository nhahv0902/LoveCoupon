package com.nhahv.lovecoupon.data.model;

import android.databinding.Bindable;
import com.google.gson.annotations.SerializedName;
import com.nhahv.lovecoupon.BR;

/**
 * Created by Nhahv0902 on 3/7/2017.
 * <></>
 */
public class ShopProfile extends LCProfile {
    @SerializedName("address")
    private String mAddress;
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
    @SerializedName("city")
    private String mCity;
    @SerializedName("country_name")
    private String mCountryName;
    @SerializedName("web_token")
    private String mToken;

    @Bindable
    public String getAddress() {
        return mAddress;
    }

    public void setAddress(String address) {
        mAddress = address;
        notifyPropertyChanged(BR.address);
    }

    @Bindable
    public String getUserId() {
        return mUserId;
    }

    public void setUserId(String userId) {
        mUserId = userId;
        notifyPropertyChanged(BR.userId);
    }

    @Bindable
    public String getUser1() {
        return mUser1;
    }

    public void setUser1(String user1) {
        mUser1 = user1;
        notifyPropertyChanged(BR.user1);
    }

    @Bindable
    public String getPassword1() {
        return mPassword1;
    }

    public void setPassword1(String password1) {
        mPassword1 = password1;
        notifyPropertyChanged(BR.password1);
    }

    @Bindable
    public String getUserAdmin1() {
        return mUserAdmin1;
    }

    public void setUserAdmin1(String userAdmin1) {
        mUserAdmin1 = userAdmin1;
        notifyPropertyChanged(BR.userAdmin1);
    }

    @Bindable
    public String getUser2() {
        return mUser2;
    }

    public void setUser2(String user2) {
        mUser2 = user2;
        notifyPropertyChanged(BR.user2);
    }

    @Bindable
    public String getPassword2() {
        return mPassword2;
    }

    public void setPassword2(String password2) {
        mPassword2 = password2;
        notifyPropertyChanged(BR.password2);
    }

    @Bindable
    public String getUserAdmin2() {
        return mUserAdmin2;
    }

    public void setUserAdmin2(String userAdmin2) {
        mUserAdmin2 = userAdmin2;
        notifyPropertyChanged(BR.userAdmin2);
    }

    @Bindable
    public String getCity() {
        return mCity;
    }

    public void setCity(String city) {
        mCity = city;
        notifyPropertyChanged(BR.city);
    }

    @Bindable
    public String getCountryName() {
        return mCountryName;
    }

    public void setCountryName(String countryName) {
        mCountryName = countryName;
        notifyPropertyChanged(BR.countryName);
    }

    @Bindable
    public String getToken() {
        return mToken;
    }

    public void setToken(String token) {
        mToken = token;
        notifyPropertyChanged(BR.token);
    }
}
