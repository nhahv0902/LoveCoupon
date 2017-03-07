package com.nhahv.lovecoupon.data.source.remote;

import android.support.annotation.NonNull;

import com.nhahv.lovecoupon.data.model.ShopProfile;
import com.nhahv.lovecoupon.data.source.Callback;
import com.nhahv.lovecoupon.networking.ServiceGenerator;
import com.nhahv.lovecoupon.networking.api.AuthorizationService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Response;

/**
 * Created by Nhahv0902 on 3/7/2017.
 * <></>
 */
public class AuthorizationRemoteDataSource implements AuthorizationDataSource {
    private static AuthorizationDataSource sDataSource;

    private AuthorizationRemoteDataSource() {
    }

    public static AuthorizationDataSource getInstance() {
        if (sDataSource == null) sDataSource = new AuthorizationRemoteDataSource();
        return sDataSource;
    }

    @Override
    public void loginNormalShop(@NonNull String user, @NonNull String password,
                                @NonNull Callback<ShopProfile> callback) {
        AuthorizationService.LoginShopBody body = new AuthorizationService.LoginShopBody();
        body.setNormal(user, password);
        ServiceGenerator.createService(AuthorizationService.LoginService.class).loginShop(body)
            .enqueue(new retrofit2.Callback<List<ShopProfile>>() {
                @Override
                public void onResponse(Call<List<ShopProfile>> call,
                                       Response<List<ShopProfile>> response) {
                    if (response == null) {
                        callback.onError();
                        return;
                    }
                    ShopProfile profile = response.body().get(0);
                    if (profile == null) {
                        callback.onError();
                        return;
                    }
                    callback.onSuccess(profile);
                }

                @Override
                public void onFailure(Call<List<ShopProfile>> call, Throwable t) {
                    callback.onError();
                }
            });
    }

    @Override
    public void loginSocialShop(@NonNull String id, @NonNull String social, @NonNull String token,
                                @NonNull Callback<ShopProfile> callback) {
        AuthorizationService.LoginShopBody body = new AuthorizationService.LoginShopBody();
        body.setSocial(id, social, token);
        ServiceGenerator.createService(AuthorizationService.LoginService.class).loginShop(body)
            .enqueue(new retrofit2.Callback<List<ShopProfile>>() {
                @Override
                public void onResponse(Call<List<ShopProfile>> call,
                                       Response<List<ShopProfile>> response) {
                    if (response == null) {
                        callback.onError();
                        return;
                    }
                    ShopProfile profile = response.body().get(0);
                    if (profile == null) {
                        callback.onError();
                        return;
                    }
                    callback.onSuccess(profile);
                }

                @Override
                public void onFailure(Call<List<ShopProfile>> call, Throwable t) {
                    callback.onError();
                }
            });
    }
}
