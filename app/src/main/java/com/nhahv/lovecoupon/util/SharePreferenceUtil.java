package com.nhahv.lovecoupon.util;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by Nhahv0902 on 3/7/2017.
 * <></>
 */
public class SharePreferenceUtil {
    private static final String SHARED_PREFERENCE = "LoveCoupon";
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

    public String getString(String key) {
        return mPreferences.getString(key, null);
    }
}
