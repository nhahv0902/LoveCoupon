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
public interface CouponDataSource {
    /*
    * method of shop
    * */
    void getCreateCoupon(@NonNull String header, @NonNull String id, long utc1, long uct2,
            @NonNull Callback<List<Coupon>> callback);

    void getUsedCoupon(@NonNull String header, @NonNull String id, long utc1, long utc2,
            @NonNull Callback<List<Coupon>> callback);

    /*
    * method of customer
    * */
    void getCouponCustomer(@NonNull String idUser,
            @NonNull Callback<List<CouponCustomer>> callback);

    void useCoupon(@NonNull Coupon coupon, @NonNull Callback<Boolean> callback);

    void addCoupon(@NonNull String city, @NonNull Coupon coupon,
            @NonNull Callback<CouponCustomer> callback);
}
