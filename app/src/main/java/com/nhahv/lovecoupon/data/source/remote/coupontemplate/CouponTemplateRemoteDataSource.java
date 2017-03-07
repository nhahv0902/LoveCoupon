package com.nhahv.lovecoupon.data.source.remote.coupontemplate;

import android.support.annotation.NonNull;

import com.nhahv.lovecoupon.data.model.CouponTemplate;
import com.nhahv.lovecoupon.data.source.Callback;
import com.nhahv.lovecoupon.networking.ServiceGenerator;
import com.nhahv.lovecoupon.networking.api.CouponTemplateService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Response;

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
    public void getCoupon( @NonNull String shopId, @NonNull Callback<List<CouponTemplate>> callback) {
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
}
