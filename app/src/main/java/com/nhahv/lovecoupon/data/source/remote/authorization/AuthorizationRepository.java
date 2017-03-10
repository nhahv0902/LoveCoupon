package com.nhahv.lovecoupon.data.source.remote.authorization;

import android.support.annotation.NonNull;

import com.nhahv.lovecoupon.data.model.ProfileShop;
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
                                @NonNull Callback<ProfileShop> callback) {
        if (mDataSource == null) return;
        mDataSource.loginNormalShop(user, password, new Callback<ProfileShop>() {
            @Override
            public void onSuccess(ProfileShop data) {
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
                                @NonNull Callback<ProfileShop> callback) {
        if (mDataSource == null) return;
        mDataSource.loginSocialShop(id, social, token, new Callback<ProfileShop>() {
            @Override
            public void onSuccess(ProfileShop data) {
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
        if (mDataSource == null) return;
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

    @Override
    public void resetPassword(@NonNull String email, @NonNull Callback<String> callback) {
        if (mDataSource == null) return;
        mDataSource.resetPassword(email, new Callback<String>() {
            @Override
            public void onSuccess(String data) {
                callback.onSuccess(data);
            }

            @Override
            public void onError() {
                callback.onError();
            }
        });
    }
}
