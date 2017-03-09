package com.nhahv.lovecoupon.ui.shop.main;

import android.content.Context;
import android.databinding.ObservableBoolean;
import android.databinding.ObservableInt;

/**
 * Created by Nhahv0902 on 3/6/2017.
 * <></>
 */
public class ShopMainViewModel {
    private final Context mContext;
    private final ObservableBoolean mShowImagePlus = new ObservableBoolean(true);
    private final ObservableBoolean mIconDone = new ObservableBoolean(false);
    private final ObservableInt mPosition;
    private final IShopMainHandler mHandler;

    public ShopMainViewModel(Context context, ObservableInt position, IShopMainHandler handler) {
        mContext = context;
        mPosition = position;
        mHandler = handler;
    }

    public void clickImagePlus() {
        switch (mPosition.get()) {
            case 0:
                break;
            case 1:
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
}
