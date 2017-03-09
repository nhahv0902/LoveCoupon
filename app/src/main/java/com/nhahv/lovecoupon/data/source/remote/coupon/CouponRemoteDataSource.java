package com.nhahv.lovecoupon.data.source.remote.coupon;

import android.support.annotation.NonNull;

import com.nhahv.lovecoupon.data.model.CouponCustomer;
import com.nhahv.lovecoupon.data.model.CouponItem;
import com.nhahv.lovecoupon.data.source.Callback;
import com.nhahv.lovecoupon.networking.ServiceGenerator;
import com.nhahv.lovecoupon.networking.api.CouponService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Response;

/**
 * Created by Nhahv0902 on 3/8/2017.
 * <></>
 */
public class CouponRemoteDataSource implements CouponDataSource {
    private static CouponDataSource sDataSource;
    private CouponService mService;

    private CouponRemoteDataSource() {
        mService = ServiceGenerator.createService(CouponService.class);
    }

    public static CouponDataSource getInstance() {
        if (sDataSource == null) sDataSource = new CouponRemoteDataSource();
        return sDataSource;
    }

    @Override
    public void getCreateCoupon(@NonNull String header, @NonNull String id, long utc1, long utc2,
                                @NonNull Callback<List<CouponItem>> callback) {
        mService.getCreatedCoupon(header, id, utc1, utc2).enqueue(
            new retrofit2.Callback<List<CouponItem>>() {
                @Override
                public void onResponse(Call<List<CouponItem>> call,
                                       Response<List<CouponItem>> response) {
                    if (response.body() != null) callback.onSuccess(response.body());
                    else callback.onError();
                }

                @Override
                public void onFailure(Call<List<CouponItem>> call, Throwable t) {
                    callback.onError();
                }
            });
    }

    @Override
    public void getUsedCoupon(@NonNull String header, @NonNull String id, long utc1, long utc2,
                              @NonNull Callback<List<CouponItem>> callback) {
        mService.getUsedCoupon(header, id, utc1, utc2).enqueue(
            new retrofit2.Callback<List<CouponItem>>() {
                @Override
                public void onResponse(Call<List<CouponItem>> call,
                                       Response<List<CouponItem>> response) {
                    if (response.body() != null) callback.onSuccess(response.body());
                    else callback.onError();
                }

                @Override
                public void onFailure(Call<List<CouponItem>> call, Throwable t) {
                    callback.onError();
                }
            });
    }

    @Override
    public void getCouponCustomer(@NonNull String idUser,
                                  @NonNull final Callback<List<CouponCustomer>> callback) {
        mService.getCouponOfCustomer(idUser).enqueue(
            new retrofit2.Callback<List<CouponCustomer>>() {
                @Override
                public void onResponse(Call<List<CouponCustomer>> call,
                                       Response<List<CouponCustomer>> response) {
                    if (response != null && response.body() != null) {
                        callback.onSuccess(response.body());
                    } else callback.onError();
                }

                @Override
                public void onFailure(Call<List<CouponCustomer>> call, Throwable t) {
                    callback.onError();
                }
            });
    }
}
