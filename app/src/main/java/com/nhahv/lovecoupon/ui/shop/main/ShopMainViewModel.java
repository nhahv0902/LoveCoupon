package com.nhahv.lovecoupon.ui.shop.main;

import android.content.Context;
import android.databinding.ObservableBoolean;

/**
 * Created by Nhahv0902 on 3/6/2017.
 * <></>
 */
public class ShopMainViewModel {
    private final Context mContext;
    private final IShopMain mIShopMain;
    private final ObservableBoolean mShowImagePlus = new ObservableBoolean(true);
    private final ObservableBoolean mIconDone = new ObservableBoolean(false);

    public ShopMainViewModel(Context context, IShopMain iShopMain) {
        mContext = context;
        mIShopMain = iShopMain;
    }

    public void clickImagePlus() {
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
}
