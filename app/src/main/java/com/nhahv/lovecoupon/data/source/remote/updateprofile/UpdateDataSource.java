package com.nhahv.lovecoupon.data.source.remote.updateprofile;

import android.support.annotation.NonNull;

import com.nhahv.lovecoupon.data.model.ProfileShop;
import com.nhahv.lovecoupon.data.source.Callback;

/**
 * Created by Nhahv0902 on 3/9/2017.
 * <></>
 */
public interface UpdateDataSource {
    void isUserExists(@NonNull String idShop, @NonNull String user,
                      @NonNull Callback<Boolean> callback);
    void updateProfile(@NonNull String token, @NonNull ProfileShop profile,
                       @NonNull Callback<Boolean> callback);
}
