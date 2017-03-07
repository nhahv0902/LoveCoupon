package com.nhahv.lovecoupon.service;

import android.util.Log;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;
import com.nhahv.lovecoupon.util.SharePreferenceUtil;

import static com.nhahv.lovecoupon.util.Constant.PreferenceConstant.PREF_TOKEN;

public class FireBaseInstanceIDService extends FirebaseInstanceIdService {
    private static final String TAG = "FireBaseToken";

    @Override
    public void onTokenRefresh() {
        String token = FirebaseInstanceId.getInstance().getToken();
        Log.d(TAG, "Token : " + token);
        SharePreferenceUtil.getInstance(this).writePreference(PREF_TOKEN, token);
    }
}