package com.nhahv.lovecoupon.ui.firstscreen;

import android.content.Context;
import android.databinding.BaseObservable;

/**
 * Created by Nhahv0902 on 3/5/2017.
 * <></>
 */
public class FirstScreenViewModel  extends BaseObservable{
    private Context mContext;
    private IFirstScreen mIFirstScreen;

    public FirstScreenViewModel(Context context, IFirstScreen iFirstScreen) {
        mContext = context;
        mIFirstScreen = iFirstScreen;
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
