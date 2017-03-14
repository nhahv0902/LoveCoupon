package com.nhahv.lovecoupon.data.source.remote.coupon;

import android.support.annotation.NonNull;

import com.nhahv.lovecoupon.data.model.Coupon;
import com.nhahv.lovecoupon.data.model.CouponCustomer;
import com.nhahv.lovecoupon.data.source.Callback;

import java.util.List;

/**
 * Created by Nhahv0902 on 3/8/2017.
 * <></>
 */
public class CouponRepository implements CouponDataSource {
    private static CouponRepository sRepository;
    private CouponDataSource mDataSource;

    private CouponRepository() {
        mDataSource = CouponRemoteDataSource.getInstance();
    }

    public static CouponRepository getInstance() {
        if (sRepository == null) sRepository = new CouponRepository();
        return sRepository;
    }

    @Override
    public void getCreateCoupon(@NonNull String header, @NonNull String id, long utc1, long utc2,
                                @NonNull Callback<List<Coupon>> callback) {
        if (mDataSource == null) return;
        mDataSource.getCreateCoupon(header, id, utc1, utc2, new Callback<List<Coupon>>() {
            @Override
            public void onSuccess(List<Coupon> data) {
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
                              @NonNull Callback<List<Coupon>> callback) {
        if (mDataSource == null) return;
        mDataSource.getUsedCoupon(header, id, utc1, utc2, new Callback<List<Coupon>>() {
            @Override
            public void onSuccess(List<Coupon> data) {
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
        if (mDataSource == null) return;
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

    @Override
    public void useCoupon(@NonNull Coupon coupon, @NonNull Callback<Boolean> callback) {
        if (mDataSource == null) return;
        mDataSource.useCoupon(coupon, new Callback<Boolean>() {
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
    public void addCoupon(@NonNull String city, @NonNull Coupon coupon,
                          @NonNull Callback<List<CouponCustomer>> callback) {
        if (mDataSource == null) return;
        mDataSource.addCoupon(city, coupon, new Callback<List<CouponCustomer>>() {
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
