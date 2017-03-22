package com.nhahv.lovecoupon.data.source.remote.updateprofile;

import android.support.annotation.NonNull;
import com.nhahv.lovecoupon.data.model.ShopProfile;
import com.nhahv.lovecoupon.data.source.Callback;
import com.nhahv.lovecoupon.networking.ServiceGenerator;
import com.nhahv.lovecoupon.networking.api.UpdateService;
import retrofit2.Call;
import retrofit2.Response;

import static com.nhahv.lovecoupon.util.Constant.DataConstant.SUCCESS;

/**
 * Created by Nhahv0902 on 3/9/2017.
 * <></>
 */
public class UpdateRemoteDataSource implements UpdateDataSource {
    private static UpdateDataSource sDataSource;
    private UpdateService mService;

    private UpdateRemoteDataSource() {
        mService = ServiceGenerator.createService(UpdateService.class);
    }

    public static UpdateDataSource getInstance() {
        if (sDataSource == null) sDataSource = new UpdateRemoteDataSource();
        return sDataSource;
    }

    @Override
    public void isUserExists(@NonNull String idShop, @NonNull String user,
            @NonNull Callback<Boolean> callback) {
        mService.isUserExists(idShop, user).enqueue(new retrofit2.Callback<Integer>() {
            @Override
            public void onResponse(Call<Integer> call, Response<Integer> response) {
                if (response.body() == SUCCESS) {
                    callback.onSuccess(true);
                } else {
                    callback.onError();
                }
            }

            @Override
            public void onFailure(Call<Integer> call, Throwable t) {
                callback.onError();
            }
        });
    }

    @Override
    public void updateProfile(@NonNull String token, @NonNull ShopProfile profile,
            @NonNull Callback<Boolean> callback) {
        mService.updateProfile(token, profile).enqueue(new retrofit2.Callback<Integer>() {
            @Override
            public void onResponse(Call<Integer> call, Response<Integer> response) {
                if (response.body() == SUCCESS) {
                    callback.onSuccess(true);
                } else {
                    callback.onError();
                }
            }

            @Override
            public void onFailure(Call<Integer> call, Throwable t) {
                callback.onError();
            }
        });
    }
}
