package com.nhahv.lovecoupon.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;

import com.google.gson.Gson;
import com.nhahv.lovecoupon.data.model.CustomerProfile;
import com.nhahv.lovecoupon.data.model.ShopProfile;

/**
 * Created by Nhahv0902 on 3/7/2017.
 * <></>
 */
public class SharePreferenceUtil {
    private static final String SHARED_PREFERENCE = "LoveCoupon";
    private static final String PREF_PROFILE_SHOP = "PREF_PROFILE_SHOP";
    private static final String PREF_PROFILE_CUSTOMER = "PREF_PROFILE_CUSTOMER";
    private SharedPreferences mPreferences;
    private static SharePreferenceUtil sInstance;

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
        return new Gson()
            .fromJson(mPreferences.getString(PREF_PROFILE_SHOP, ""), ShopProfile.class);
    }

    public CustomerProfile profileCustomer() {
        return new Gson()
            .fromJson(mPreferences.getString(PREF_PROFILE_CUSTOMER, ""), CustomerProfile.class);
    }

    public void writeProfileCustomer(@NonNull CustomerProfile profile) {
        mPreferences.edit().putString(PREF_PROFILE_CUSTOMER, new Gson().toJson(profile)).apply();
    }

    public String getString(String key) {
        return mPreferences.getString(key, "");
    }

    public boolean getBoolean(String key) {
        return mPreferences.getBoolean(key, false);
    }
}
