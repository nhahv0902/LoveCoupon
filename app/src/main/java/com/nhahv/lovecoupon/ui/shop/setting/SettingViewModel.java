package com.nhahv.lovecoupon.ui.shop.setting;

import android.content.Context;
import android.databinding.ObservableBoolean;

/**
 * Created by Nhahv0902 on 3/6/2017.
 * <></>
 */
public class SettingViewModel {
    private final Context mContext;
    private ObservableBoolean mShopNoData = new ObservableBoolean();

    public SettingViewModel(Context context) {
        mContext = context;
    }

    public ObservableBoolean getShopNoData() {
        return mShopNoData;
    }

    public void clickPickLogo(){
        // TODO: 3/7/2017 click pick image open gallery
    }
}
