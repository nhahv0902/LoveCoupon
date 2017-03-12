package com.nhahv.lovecoupon.ui.shop.main;

import android.content.Context;
import android.databinding.ObservableBoolean;
import android.databinding.ObservableInt;

import com.facebook.login.LoginManager;
import com.nhahv.lovecoupon.data.model.ShopProfile;
import com.nhahv.lovecoupon.util.SharePreferenceUtil;

import static com.nhahv.lovecoupon.util.Constant.PreferenceConstant.PREF_IS_LOGIN;

/**
 * Created by Nhahv0902 on 3/6/2017.
 * <></>
 */
public class ShopMainViewModel {
    private final Context mContext;
    private final ObservableBoolean mShowImagePlus = new ObservableBoolean(true);
    private final ObservableBoolean mIconDone = new ObservableBoolean(false);
    private final ObservableInt mPosition = new ObservableInt();
    private final IShopMainHandler mHandler;
    private final ShopProfile mProfile;
    private final SharePreferenceUtil mPreference;

    public ShopMainViewModel(Context context, IShopMainHandler handler) {
        mContext = context;
        mHandler = handler;
        mPreference = SharePreferenceUtil.getInstance(context);
        mProfile = SharePreferenceUtil.getInstance(context).profileShop();
    }

    public void clickImagePlus() {
        switch (mPosition.get()) {
            case 0:
                mHandler.createCouponTemplate();
                break;
            case 1:
                mHandler.createNotification();
                break;
            case 2:
                mHandler.clickUpdateProfile();
                break;
            case 3:
                break;
            case 4:
                break;
            default:
                break;
        }
    }

    public void updateShowImagePlus(boolean isShow) {
        mShowImagePlus.set(isShow);
    }

    public void updateIconDone(boolean isDone) {
        mIconDone.set(isDone);
    }

    public ObservableBoolean getIconDone() {
        return mIconDone;
    }

    public ObservableBoolean getShowImagePlus() {
        return mShowImagePlus;
    }

    public ShopProfile getProfile() {
        return mProfile;
    }

    public ObservableInt getPosition() {
        return mPosition;
    }

    public void setPosition(int position) {
        mPosition.set(position);
    }

    public void startFragment(int position) {
        mHandler.addFragment(position);
    }

    public void logout() {
        LoginManager.getInstance().logOut();
        mPreference.writePreference(PREF_IS_LOGIN, false);
        mHandler.startUiFirstScreen();
    }
}
