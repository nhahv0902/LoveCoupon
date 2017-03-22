package com.nhahv.lovecoupon.ui.firstscreen;

import android.content.Context;
import android.databinding.BaseObservable;
import android.support.annotation.NonNull;
import com.nhahv.lovecoupon.util.SharePreferenceUtil;

import static com.nhahv.lovecoupon.util.Constant.PreferenceConstant.PREF_ACCOUNT_TYPE;
import static com.nhahv.lovecoupon.util.Constant.PreferenceConstant.PREF_IS_LOGIN;

/**
 * Created by Nhahv0902 on 3/5/2017.
 * <></>
 */
public class FirstScreenViewModel extends BaseObservable {
    private final Context mContext;
    private final FirstScreenHandler mFirstScreenHandler;

    public FirstScreenViewModel(@NonNull Context context,
            @NonNull FirstScreenHandler firstScreenHandler) {
        mContext = context;
        mFirstScreenHandler = firstScreenHandler;
        start();
    }

    private void start() {
        if (mFirstScreenHandler == null) return;
        SharePreferenceUtil preference = SharePreferenceUtil.getInstance(mContext);
        boolean isLogin = preference.getBoolean(PREF_IS_LOGIN);
        if (isLogin) {
            AccountType type = AccountType.toAccountType(preference.getString(PREF_ACCOUNT_TYPE));
            switch (type) {
                case SHOP:
                    mFirstScreenHandler.startUiMainShop();
                    break;
                case CUSTOMER:
                    mFirstScreenHandler.startUiMainCustomer();
                    break;
                case NORMAL:
                default:
                    break;
            }
        }
    }

    public void clickStartUiLogin(AccountType type) {
        if (mFirstScreenHandler == null) return;
        switch (type) {
            case SHOP:
                mFirstScreenHandler.startUiLogin(AccountType.SHOP);
                break;
            case CUSTOMER:
                mFirstScreenHandler.startUiLogin(AccountType.CUSTOMER);
                break;
            default:
                break;
        }
    }
}
