package com.nhahv.lovecoupon.data.source.remote.authorization;

import android.support.annotation.NonNull;
import com.nhahv.lovecoupon.data.model.ShopProfile;
import com.nhahv.lovecoupon.data.source.Callback;

/**
 * Created by Nhahv0902 on 3/7/2017.
 * <></>
 */
public interface AuthorizationDataSource {
    void loginNormalShop(@NonNull String user, @NonNull String password,
            @NonNull Callback<ShopProfile> callback);

    void loginSocialShop(@NonNull String id, @NonNull String social, @NonNull String token,
            @NonNull Callback<ShopProfile> callback);

    void loginCustomer(@NonNull String name, String password, @NonNull String token,
            @NonNull Callback<Integer> callback);

    void resetPassword(@NonNull String email, @NonNull Callback<String> callback);
}
