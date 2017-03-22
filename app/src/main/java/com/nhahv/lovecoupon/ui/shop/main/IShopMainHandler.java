package com.nhahv.lovecoupon.ui.shop.main;

import com.nhahv.lovecoupon.data.model.ShopProfile;

/**
 * Created by Nhahv0902 on 3/6/2017.
 * <></>
 */
public interface IShopMainHandler {
    void clickUpdateProfile();

    void createNotification();

    void createCouponTemplate();

    void startUiFirstScreen();

    void addFragment(int position);

    void updateProfile(ShopProfile profile);
}
