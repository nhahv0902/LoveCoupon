package com.nhahv.lovecoupon.data.source.remote.coupontemplate;

import android.support.annotation.NonNull;

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
    public void getCoupon(@NonNull String shopId,
                          @NonNull Callback<List<CouponTemplate>> callback) {
        mDataSource.getCoupon(shopId, new Callback<List<CouponTemplate>>() {
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
}