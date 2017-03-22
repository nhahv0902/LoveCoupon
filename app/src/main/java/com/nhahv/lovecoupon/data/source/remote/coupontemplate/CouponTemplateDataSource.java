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
public interface CouponTemplateDataSource {
    void getCouponTemplate(@NonNull String shopId,
            @NonNull Callback<List<CouponTemplate>> callback);

    void createCouponTemplate(@NonNull String token, @NonNull CouponTemplate template,
            @NonNull Callback<Boolean> callback);

    void generateCoupon(@NonNull String token, @NonNull Coupon coupon,
            @NonNull Callback<Boolean> callback);

    void deleteCouponTemplate(@NonNull String token, @NonNull CouponTemplate template,
            @NonNull Callback<Boolean> callback);
}
