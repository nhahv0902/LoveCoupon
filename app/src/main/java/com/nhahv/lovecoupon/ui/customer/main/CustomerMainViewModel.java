package com.nhahv.lovecoupon.ui.customer.main;

import android.content.Context;
import android.databinding.BaseObservable;
import android.databinding.ObservableBoolean;

/**
 * Created by Nhahv0902 on 3/7/2017.
 * <></>
 */
public class CustomerMainViewModel extends BaseObservable {
    private final Context mContext;
    private final ObservableBoolean mShopImagePlus = new ObservableBoolean();

    public CustomerMainViewModel(Context context) {
        mContext = context;
    }

    public void clickImagePlus() {
    }

    public void setShowImagePlus(boolean showImagePlus) {
        mShopImagePlus.set(showImagePlus);
    }

    public ObservableBoolean getShopImagePlus() {
        return mShopImagePlus;
    }
}
