package com.nhahv.lovecoupon.data.source.remote.coupon;

import android.support.annotation.NonNull;

import com.nhahv.lovecoupon.data.model.Coupon;
import com.nhahv.lovecoupon.data.model.CouponCustomer;
import com.nhahv.lovecoupon.data.source.Callback;
import com.nhahv.lovecoupon.networking.ServiceGenerator;
import com.nhahv.lovecoupon.networking.api.CouponService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Response;

import static com.nhahv.lovecoupon.util.Constant.DataConstant.SUCCESS;

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
                                @NonNull Callback<List<Coupon>> callback) {
        if (mService == null) return;
        mService.getCreatedCoupon(header, id, utc1, utc2).enqueue(
            new retrofit2.Callback<List<Coupon>>() {
                @Override
                public void onResponse(Call<List<Coupon>> call,
                                       Response<List<Coupon>> response) {
                    if (response.body() != null) callback.onSuccess(response.body());
                    else callback.onError();
                }

                @Override
                public void onFailure(Call<List<Coupon>> call, Throwable t) {
                    callback.onError();
                }
            });
    }

    @Override
    public void getUsedCoupon(@NonNull String header, @NonNull String id, long utc1, long utc2,
                              @NonNull Callback<List<Coupon>> callback) {
        if (mService == null) return;
        mService.getUsedCoupon(header, id, utc1, utc2).enqueue(
            new retrofit2.Callback<List<Coupon>>() {
                @Override
                public void onResponse(Call<List<Coupon>> call,
                                       Response<List<Coupon>> response) {
                    if (response.body() != null) callback.onSuccess(response.body());
                    else callback.onError();
                }

                @Override
                public void onFailure(Call<List<Coupon>> call, Throwable t) {
                    callback.onError();
                }
            });
    }

    @Override
    public void getCouponCustomer(@NonNull String idUser,
                                  @NonNull final Callback<List<CouponCustomer>> callback) {
        if (mService == null) return;
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

    @Override
    public void useCoupon(@NonNull Coupon coupon, @NonNull Callback<Boolean> callback) {
        if (mService == null) return;
        mService.useCoupon(coupon).enqueue(new retrofit2.Callback<Integer>() {
            @Override
            public void onResponse(Call<Integer> call, Response<Integer> response) {
                if (response == null || response.body() == null || response.body() != SUCCESS) {
                    callback.onError();
                } else callback.onSuccess(true);
            }

            @Override
            public void onFailure(Call<Integer> call, Throwable t) {
                callback.onError();
            }
        });
    }

    @Override
    public void addCoupon(@NonNull String city, @NonNull Coupon coupon,
                          @NonNull Callback<List<CouponCustomer>> callback) {
        if (mService == null) return;
        mService.addCoupon(city, coupon).enqueue(new retrofit2.Callback<List<CouponCustomer>>() {
            @Override
            public void onResponse(Call<List<CouponCustomer>> call,
                                   Response<List<CouponCustomer>> response) {
                if (response.isSuccessful() && response.body() != null) {
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
