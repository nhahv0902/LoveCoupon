package com.nhahv.lovecoupon.ui;

import android.databinding.ObservableBoolean;
import android.databinding.ObservableField;
import com.nhahv.lovecoupon.data.model.LCProfile;

/**
 * Created by Nhahv0902 on 3/27/2017.
 * <></>
 */
public interface MainViewModel {
    ObservableBoolean mIsShopPlus = new ObservableBoolean(true);

    void clickImagePlus();

    void onNavigationListener(int itemId);

    ObservableBoolean getIsShopPlus();

    void setIsShopPlus(boolean isShow);

    ObservableField<LCProfile> getProfile();
}
