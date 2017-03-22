package com.nhahv.lovecoupon.ui.shop.main;

import android.content.Context;
import android.databinding.BaseObservable;
import android.databinding.ObservableBoolean;
import android.databinding.ObservableField;
import android.databinding.ObservableInt;
import com.facebook.login.LoginManager;
import com.nhahv.lovecoupon.R;
import com.nhahv.lovecoupon.data.model.LCProfile;
import com.nhahv.lovecoupon.data.model.ShopProfile;
import com.nhahv.lovecoupon.ui.MainViewModel;
import com.nhahv.lovecoupon.util.SharePreferenceUtil;

import static com.nhahv.lovecoupon.util.Constant.PreferenceConstant.PREF_IS_LOGIN;

/**
 * Created by Nhahv0902 on 3/6/2017.
 * <></>
 */
public class ShopMainViewModel extends BaseObservable implements MainViewModel {
    private final Context mContext;
    private final ObservableBoolean mIconDone = new ObservableBoolean(false);
    private final ObservableInt mPosition = new ObservableInt();
    private final IShopMainHandler mHandler;
    private final SharePreferenceUtil mPreference;
    private final ObservableField<LCProfile> mProfile = new ObservableField<>();

    public ShopMainViewModel(Context context, IShopMainHandler handler) {
        mContext = context;
        mHandler = handler;
        mPreference = SharePreferenceUtil.getInstance(context);
        mProfile.set(SharePreferenceUtil.getInstance(context).profileShop());
        setIsShopPlus(true);
    }

    @Override
    public void onNavigationListener(int itemId) {
        int position = 0;
        switch (itemId) {
            case R.id.action_coupon:
                position = 0;
                break;
            case R.id.action_notification:
                position = 1;
                break;
            case R.id.action_settings:
                position = 2;
                break;
            case R.id.action_history:
                position = 3;
                break;
            case R.id.action_share:
                position = 4;
                break;
            case R.id.action_exit:
                logout();
                break;
            default:
                break;
        }
        setPosition(position);
        startFragment(position);
    }

    @Override
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

    public void updateIconDone(boolean isDone) {
        mIconDone.set(isDone);
    }

    public ObservableBoolean getIconDone() {
        return mIconDone;
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

    public void updateUiProfile(ShopProfile profile) {
        mProfile.set(profile);
        mPreference.writeProfileShop(profile);
        mProfile.notifyChange();
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
