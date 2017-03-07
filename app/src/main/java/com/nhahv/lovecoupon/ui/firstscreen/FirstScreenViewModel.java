package com.nhahv.lovecoupon.ui.firstscreen;

import android.content.Context;
import android.databinding.BaseObservable;

import com.nhahv.lovecoupon.util.SharePreferenceUtil;

import static com.nhahv.lovecoupon.util.Constant.PreferenceConstant.PREF_ACCOUNT_TYPE;
import static com.nhahv.lovecoupon.util.Constant.PreferenceConstant.PREF_IS_LOGIN;

/**
 * Created by Nhahv0902 on 3/5/2017.
 * <></>
 */
public class FirstScreenViewModel extends BaseObservable {
    private Context mContext;
    private IFirstScreen mIFirstScreen;

    public FirstScreenViewModel(Context context, IFirstScreen iFirstScreen) {
        mContext = context;
        mIFirstScreen = iFirstScreen;
        start();
    }

    private void start() {
        SharePreferenceUtil preference = SharePreferenceUtil.getInstance(mContext);
        boolean isLogin = preference.getBoolean(PREF_IS_LOGIN);
        if (isLogin) {
            AccountType type = AccountType.toAccountType(preference.getString(PREF_ACCOUNT_TYPE));
            switch (type) {
                case SHOP:
                    mIFirstScreen.startUiMainShop();
                    break;
                case CUSTOMER:
                    mIFirstScreen.startUiMainCustomer();
                    break;
                case NORMAL:
                default:
                    break;
            }
        }
    }

    public void clickStartUiLogin(AccountType type) {
        if (mIFirstScreen == null) return;
        switch (type) {
            case SHOP:
                mIFirstScreen.startUiLogin(AccountType.SHOP);
                break;
            case CUSTOMER:
                mIFirstScreen.startUiLogin(AccountType.CUSTOMER);
                break;
            default:
                break;
        }
    }
}
