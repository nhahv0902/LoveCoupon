package com.nhahv.lovecoupon.ui.shop.setting;

import android.content.Context;
import android.databinding.BaseObservable;
import android.databinding.ObservableBoolean;
import android.util.Log;

import com.nhahv.lovecoupon.data.model.ProfileShop;
import com.nhahv.lovecoupon.util.SharePreferenceUtil;

/**
 * Created by Nhahv0902 on 3/6/2017.
 * <></>
 */
public class SettingViewModel extends BaseObservable {
    private final String TAG = getClass().getSimpleName();
    private final Context mContext;
    private final ObservableBoolean mShopNoData = new ObservableBoolean();
    private ProfileShop mProfile;
    private final SharePreferenceUtil mPreference;

    public SettingViewModel(Context context) {
        mContext = context;
        mPreference = SharePreferenceUtil.getInstance(context);
        mProfile = mPreference.profileShop();
    }

    public ObservableBoolean getShopNoData() {
        return mShopNoData;
    }

    public void clickPickLogo() {
        // TODO: 3/7/2017 click pick image open gallery
        Log.d(TAG, "admin1 = " + mProfile.getUserAdmin1());
        Log.d(TAG, "admin2 = " + mProfile.getUserAdmin2());
    }

    public ProfileShop getProfile() {
        return mProfile;
    }
}
