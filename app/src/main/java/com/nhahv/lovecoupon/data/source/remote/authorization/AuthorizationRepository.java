package com.nhahv.lovecoupon.data.source.remote.authorization;

import android.support.annotation.NonNull;

import com.nhahv.lovecoupon.data.model.ShopProfile;
import com.nhahv.lovecoupon.data.source.Callback;

/**
 * Created by Nhahv0902 on 3/7/2017.
 * <></>
 */
public class AuthorizationRepository implements AuthorizationDataSource {
    private static AuthorizationRepository sRepository;
    private AuthorizationDataSource mDataSource;

    private AuthorizationRepository() {
        mDataSource = AuthorizationRemoteDataSource.getInstance();
    }

    public static AuthorizationRepository getInstance() {
        if (sRepository == null) sRepository = new AuthorizationRepository();
        return sRepository;
    }

    @Override
    public void loginNormalShop(@NonNull String user, @NonNull String password,
                                @NonNull Callback<ShopProfile> callback) {
        mDataSource.loginNormalShop(user, password, new Callback<ShopProfile>() {
            @Override
            public void onSuccess(ShopProfile data) {
                callback.onSuccess(data);
            }

            @Override
            public void onError() {
                callback.onError();
            }
        });
    }

    @Override
    public void loginSocialShop(@NonNull String id, @NonNull String social, @NonNull String token,
                                @NonNull Callback<ShopProfile> callback) {
        mDataSource.loginSocialShop(id, social, token, new Callback<ShopProfile>() {
            @Override
            public void onSuccess(ShopProfile data) {
                callback.onSuccess(data);
            }

            @Override
            public void onError() {
                callback.onError();
            }
        });
    }

    @Override
    public void loginCustomer(@NonNull String name, String password, @NonNull String token,
                              @NonNull Callback<Integer> callback) {
        mDataSource.loginCustomer(name, password, token, new Callback<Integer>() {
            @Override
            public void onSuccess(Integer data) {
                callback.onSuccess(data);
            }

            @Override
            public void onError() {
                callback.onError();
            }
        });
    }
}
