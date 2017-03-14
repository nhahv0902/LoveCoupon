package com.nhahv.lovecoupon.ui.customer.main;

import android.app.Activity;
import android.databinding.BaseObservable;
import android.databinding.ObservableBoolean;

import com.facebook.login.LoginManager;
import com.nhahv.lovecoupon.data.model.CustomerProfile;
import com.nhahv.lovecoupon.util.SharePreferenceUtil;

import static com.nhahv.lovecoupon.util.Constant.PreferenceConstant.PREF_IS_LOGIN;

/**
 * Created by Nhahv0902 on 3/7/2017.
 * <></>
 */
public class CustomerMainViewModel extends BaseObservable {
    private final Activity mContext;
    private final ObservableBoolean mShopImagePlus = new ObservableBoolean(true);
    private final CustomerProfile mProfile;
    private final CustomerMainHandler mHandler;
    private final SharePreferenceUtil mPreference;

    public CustomerMainViewModel(Activity context, CustomerMainHandler handler) {
        mContext = context;
        mHandler = handler;
        mPreference = SharePreferenceUtil.getInstance(context);
        mProfile = mPreference.profileCustomer();
    }

    public void clickImagePlus() {
        if (mHandler == null) return;
        mHandler.startUiCouponAddition();
    }

    public void setShowImagePlus(boolean showImagePlus) {
        mShopImagePlus.set(showImagePlus);
    }

    public ObservableBoolean getShopImagePlus() {
        return mShopImagePlus;
    }

    public CustomerProfile getProfile() {
        return mProfile;
    }

    public void logout() {
        LoginManager.getInstance().logOut();
//        LCGoogle.getInstance(mContext).logout();
        setShowImagePlus(false);
        mPreference.writePreference(PREF_IS_LOGIN, false);
        mHandler.startUiFirstScreen();
    }

    public void addFragment(int position) {
        if (mHandler == null) return;
        mHandler.addFragment(position);
    }
}
