package com.nhahv.lovecoupon.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import com.google.gson.Gson;
import com.nhahv.lovecoupon.data.model.LCProfile;
import com.nhahv.lovecoupon.data.model.ShopProfile;

import static com.nhahv.lovecoupon.util.Constant.PreferenceConstant.PREF_CITY;
import static com.nhahv.lovecoupon.util.Constant.PreferenceConstant.PREF_EMAIL;
import static com.nhahv.lovecoupon.util.Constant.PreferenceConstant.PREF_PASSWORD;

/**
 * Created by Nhahv0902 on 3/7/2017.
 * <></>
 */
public class SharePreferenceUtil {
    private static final String SHARED_PREFERENCE = "LoveCoupon";
    private static final String PREF_PROFILE_SHOP = "PREF_PROFILE_SHOP";
    private static final String PREF_PROFILE_CUSTOMER = "PREF_PROFILE_CUSTOMER";
    private static SharePreferenceUtil sInstance;
    private SharedPreferences mPreferences;

    private SharePreferenceUtil(Context context) {
        mPreferences = context.getSharedPreferences(SHARED_PREFERENCE, Context.MODE_PRIVATE);
    }

    public static SharePreferenceUtil getInstance(Context context) {
        if (sInstance == null) sInstance = new SharePreferenceUtil(context);
        return sInstance;
    }

    public void writePreference(String key, String value) {
        mPreferences.edit().putString(key, value).apply();
    }

    public void writePreference(String key, boolean value) {
        mPreferences.edit().putBoolean(key, value).apply();
    }

    public void writeProfileShop(@NonNull ShopProfile profile) {
        mPreferences.edit().putString(PREF_PROFILE_SHOP, new Gson().toJson(profile)).apply();
    }

    public ShopProfile profileShop() {
        return new Gson().fromJson(mPreferences.getString(PREF_PROFILE_SHOP, ""),
                ShopProfile.class);
    }

    public LCProfile profileCustomer() {
        return new Gson().fromJson(mPreferences.getString(PREF_PROFILE_CUSTOMER, ""),
                LCProfile.class);
    }

    public void writeProfile(@NonNull LCProfile profile) {
        mPreferences.edit().putString(PREF_PROFILE_CUSTOMER, new Gson().toJson(profile)).apply();
    }

    public String getString(String key) {
        return mPreferences.getString(key, "");
    }

    public boolean getBoolean(String key) {
        return mPreferences.getBoolean(key, false);
    }

    public void writeCity(String city) {
        mPreferences.edit().putString(PREF_CITY, city).apply();
    }

    public String getCity() {
        return mPreferences.getString(PREF_CITY, null);
    }

    public void writeEmailPassword(String email, String password) {
        mPreferences.edit().putString(PREF_EMAIL, email).putString(PREF_PASSWORD, password).apply();
    }

    public String getEmail() {
        return mPreferences.getString(PREF_EMAIL, null);
    }

    public String getPassword() {
        return mPreferences.getString(PREF_PASSWORD, null);
    }
}
