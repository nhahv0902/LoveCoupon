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
public interface CouponDataSource {
    /*
    * method of shop
    * */
    void getCreateCoupon(@NonNull String header, @NonNull String id, long utc1, long uct2,
                         @NonNull Callback<List<CouponItem>> callback);
    void getUsedCoupon(@NonNull String header, @NonNull String id, long utc1, long utc2,
                       @NonNull Callback<List<CouponItem>> callback);

    /*
    * method of customer
    * */
    void getCouponCustomer(@NonNull String idUser, @NonNull Callback<List<CouponCustomer>> callback);
}
