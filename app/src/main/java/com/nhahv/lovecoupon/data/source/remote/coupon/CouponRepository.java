package com.nhahv.lovecoupon.data.source.remote.coupon;

import android.support.annotation.NonNull;

import com.nhahv.lovecoupon.data.model.CouponCustomer;
import com.nhahv.lovecoupon.data.model.CouponItem;
import com.nhahv.lovecoupon.data.source.Callback;

import java.util.List;

/**
 * Created by Nhahv0902 on 3/8/2017.
 * <></>
 */
public class CouponRepository implements CouponDataSource {
    private static CouponDataSource sRepository;
    private CouponDataSource mDataSource;

    private CouponRepository() {
    }

    public static CouponDataSource getInstance() {
        if (sRepository == null) sRepository = new CouponRepository();
        return sRepository;
    }

    @Override
    public void getCreateCoupon(@NonNull String header, @NonNull String id, long utc1, long utc2,
                                @NonNull Callback<List<CouponItem>> callback) {
        mDataSource.getCreateCoupon(header, id, utc1, utc2, new Callback<List<CouponItem>>() {
            @Override
            public void onSuccess(List<CouponItem> data) {
                callback.onSuccess(data);
            }

            @Override
            public void onError() {
                callback.onError();
            }
        });
    }

    @Override
    public void getUsedCoupon(@NonNull String header, @NonNull String id, long utc1, long utc2,
                              @NonNull Callback<List<CouponItem>> callback) {
        mDataSource.getUsedCoupon(header, id, utc1, utc2, new Callback<List<CouponItem>>() {
            @Override
            public void onSuccess(List<CouponItem> data) {
                callback.onSuccess(data);
            }

            @Override
            public void onError() {
                callback.onError();
            }
        });
    }

    @Override
    public void getCouponCustomer(@NonNull String idUser,
                                  @NonNull Callback<List<CouponCustomer>> callback) {
        mDataSource.getCouponCustomer(idUser, new Callback<List<CouponCustomer>>() {
            @Override
            public void onSuccess(List<CouponCustomer> data) {
                callback.onSuccess(data);
            }

            @Override
            public void onError() {
                callback.onError();
            }
        });
    }
}
