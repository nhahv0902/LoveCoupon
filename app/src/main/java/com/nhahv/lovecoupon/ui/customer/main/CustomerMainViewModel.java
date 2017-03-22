package com.nhahv.lovecoupon.ui.customer.main;

import android.app.Activity;
import android.databinding.BaseObservable;
import android.databinding.ObservableBoolean;
import android.databinding.ObservableField;
import com.facebook.login.LoginManager;
import com.nhahv.lovecoupon.R;
import com.nhahv.lovecoupon.data.model.LCProfile;
import com.nhahv.lovecoupon.ui.MainViewModel;
import com.nhahv.lovecoupon.util.SharePreferenceUtil;

import static com.nhahv.lovecoupon.util.Constant.PreferenceConstant.PREF_IS_LOGIN;

/**
 * Created by Nhahv0902 on 3/7/2017.
 * <></>
 */
public class CustomerMainViewModel extends BaseObservable implements MainViewModel {
    private final Activity mContext;
    private final ObservableField<LCProfile> mProfile = new ObservableField<>();
    private final CustomerMainHandler mHandler;
    private final SharePreferenceUtil mPreference;

    public CustomerMainViewModel(Activity context, CustomerMainHandler handler) {
        mContext = context;
        mHandler = handler;
        mPreference = SharePreferenceUtil.getInstance(context);
        mProfile.set(mPreference.profileCustomer());
    }

    @Override
    public void onNavigationListener(int itemId) {
        int position = 0;
        switch (itemId) {
            case R.id.action_coupon:
                position = 0;
                setIsShopPlus(true);
                break;
            case R.id.action_notification:
                position = 1;
                setIsShopPlus(false);
                break;
            case R.id.action_other_notification:
                position = 2;
                setIsShopPlus(false);
                break;
            case R.id.action_share:
                position = 3;
                setIsShopPlus(false);
                break;
            case R.id.action_exit:
                logout();
                break;
            default:
                break;
        }
        addFragment(position);
    }

    @Override
    public void clickImagePlus() {
        if (mHandler == null) return;
        mHandler.startUiCouponAddition();
    }

    public void logout() {
        LoginManager.getInstance().logOut();
        //        LCGoogle.getInstance(mContext).logout();
        setIsShopPlus(false);
        mPreference.writePreference(PREF_IS_LOGIN, false);
        mHandler.startUiFirstScreen();
    }

    public void addFragment(int position) {
        if (mHandler == null) return;
        mHandler.addFragment(position);
    }

    @Override
    public ObservableBoolean getIsShopPlus() {
        return mIsShopPlus;
    }

    @Override
    public void setIsShopPlus(boolean isShow) {
        mIsShopPlus.set(isShow);
    }

    @Override
    public ObservableField<LCProfile> getProfile() {
        return mProfile;
    }
}
