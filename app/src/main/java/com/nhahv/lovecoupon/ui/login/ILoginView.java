package com.nhahv.lovecoupon.ui.login;

import com.google.android.gms.common.api.GoogleApiClient;

/**
 * Created by Nhahv0902 on 3/6/2017.
 * <></>
 */
public interface ILoginView {
    void startUiShopMain();
    void startUiCustomer();
    void startUiMain();
    void loginFacebook();
    void loginGoogle(GoogleApiClient client);
}
