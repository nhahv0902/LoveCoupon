package com.nhahv.lovecoupon.data.source.remote.coupontemplate;

import android.support.annotation.NonNull;

import com.nhahv.lovecoupon.data.model.Coupon;
import com.nhahv.lovecoupon.data.model.CouponTemplate;
import com.nhahv.lovecoupon.data.source.Callback;
import com.nhahv.lovecoupon.networking.ServiceGenerator;
import com.nhahv.lovecoupon.networking.api.CouponTemplateService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Response;

import static com.nhahv.lovecoupon.util.Constant.DataConstant.SUCCESS;

/**
 * Created by Nhahv0902 on 3/8/2017.
 * <></>
 */
public class CouponTemplateRemoteDataSource implements CouponTemplateDataSource {
    private static CouponTemplateDataSource sDataSource;
    private CouponTemplateService mService;

    private CouponTemplateRemoteDataSource() {
        mService = ServiceGenerator.createService(CouponTemplateService.class);
    }

    public static CouponTemplateDataSource getInstance() {
        if (sDataSource == null) sDataSource = new CouponTemplateRemoteDataSource();
        return sDataSource;
    }

    @Override
    public void getCouponTemplate(@NonNull String shopId,
                                  @NonNull Callback<List<CouponTemplate>> callback) {
        if (mService == null) return;
        mService.getCoupon(shopId).enqueue(new retrofit2.Callback<List<CouponTemplate>>() {
            @Override
            public void onResponse(Call<List<CouponTemplate>> call,
                                   Response<List<CouponTemplate>> response) {
                if (response.body() != null) callback.onSuccess(response.body());
                else callback.onError();
            }

            @Override
            public void onFailure(Call<List<CouponTemplate>> call, Throwable t) {
                callback.onError();
            }
        });
    }

    @Override
    public void createCouponTemplate(@NonNull String token, @NonNull CouponTemplate template,
                                     @NonNull Callback<Boolean> callback) {
        if (mService == null) return;
        mService.createCouponTemplate(token, template).enqueue(new retrofit2.Callback<Integer>() {
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
    public void generateCoupon(@NonNull String token, @NonNull Coupon coupon,
                               @NonNull Callback<Boolean> callback) {
        if (mService == null) return;
        mService.generateCoupon(token, coupon).enqueue(new retrofit2.Callback<Integer>() {
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
    public void deleteCouponTemplate(@NonNull String token, @NonNull CouponTemplate template,
                                     @NonNull Callback<Boolean> callback) {
        if (mService == null) return;
        mService.deleteCouponTemplate(token, template).enqueue(new retrofit2.Callback<Integer>() {
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
}
