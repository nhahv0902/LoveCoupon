package com.nhahv.lovecoupon.data.source.remote.updateprofile;

import android.support.annotation.NonNull;

import com.nhahv.lovecoupon.data.model.ProfileShop;
import com.nhahv.lovecoupon.data.source.Callback;

/**
 * Created by Nhahv0902 on 3/9/2017.
 * <></>
 */
public class UpdateRepository implements UpdateDataSource {
    private static UpdateRepository sRepository;
    private UpdateDataSource mDataSource;

    private UpdateRepository() {
        mDataSource = UpdateRemoteDataSource.getInstance();
    }

    public static UpdateRepository getInstance() {
        if (sRepository == null) sRepository = new UpdateRepository();
        return sRepository;
    }

    @Override
    public void isUserExists(@NonNull String idShop, @NonNull String user,
                             @NonNull Callback<Boolean> callback) {
        mDataSource.isUserExists(idShop, user, new Callback<Boolean>() {
            @Override
            public void onSuccess(Boolean data) {
                callback.onSuccess(data);
            }

            @Override
            public void onError() {
                callback.onError();
            }
        });
    }

    @Override
    public void updateProfile(@NonNull String token, @NonNull ProfileShop profile,
                              @NonNull Callback<Boolean> callback) {
        mDataSource.updateProfile(token, profile, new Callback<Boolean>() {
            @Override
            public void onSuccess(Boolean data) {
                callback.onSuccess(data);
            }

            @Override
            public void onError() {
                callback.onError();
            }
        });
    }
}
