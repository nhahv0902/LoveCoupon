package com.nhahv.lovecoupon.data.source.remote.coupontemplate;

import android.support.annotation.NonNull;

import com.nhahv.lovecoupon.data.model.Coupon;
import com.nhahv.lovecoupon.data.model.CouponTemplate;
import com.nhahv.lovecoupon.data.source.Callback;

import java.util.List;

/**
 * Created by Nhahv0902 on 3/8/2017.
 * <></>
 */
public class CouponTemplateRepository implements CouponTemplateDataSource {
    private static CouponTemplateRepository sRepository;
    private CouponTemplateDataSource mDataSource;

    private CouponTemplateRepository() {
        mDataSource = CouponTemplateRemoteDataSource.getInstance();
    }

    public static CouponTemplateRepository getInstance() {
        if (sRepository == null) sRepository = new CouponTemplateRepository();
        return sRepository;
    }

    @Override
    public void getCouponTemplate(@NonNull String shopId,
                                  @NonNull Callback<List<CouponTemplate>> callback) {
        if (mDataSource == null) return;
        mDataSource.getCouponTemplate(shopId, new Callback<List<CouponTemplate>>() {
            @Override
            public void onSuccess(List<CouponTemplate> data) {
                callback.onSuccess(data);
            }

            @Override
            public void onError() {
                callback.onError();
            }
        });
    }

    @Override
    public void createCouponTemplate(@NonNull String token, @NonNull CouponTemplate template,
                                     @NonNull Callback<Boolean> callback) {
        if (mDataSource == null) return;
        mDataSource.createCouponTemplate(token, template, new Callback<Boolean>() {
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
    public void generateCoupon(@NonNull String token, @NonNull Coupon coupon,
                               @NonNull Callback<Boolean> callback) {
        if (mDataSource == null) return;
        mDataSource.generateCoupon(token, coupon, new Callback<Boolean>() {
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
    public void deleteCouponTemplate(@NonNull String token, @NonNull CouponTemplate template,
                                     @NonNull Callback<Boolean> callback) {
        if (mDataSource == null) return;
        mDataSource.deleteCouponTemplate(token, template, new Callback<Boolean>() {
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
