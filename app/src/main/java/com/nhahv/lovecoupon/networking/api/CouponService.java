package com.nhahv.lovecoupon.networking.api;

import com.nhahv.lovecoupon.data.model.CouponItem;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Query;

/**
 * Created by Nhahv0902 on 3/8/2017.
 * <></>
 */
public interface CouponService {
    @GET("/get_created_coupon_by_company_id")
    Call<List<CouponItem>> getCreatedCoupon(@Header("Authorization") String token,
                                            @Query("company_id") String company_id,
                                            @Query("utc1") long utc1, @Query("utc2") long utc2);
    @GET("/get_used_coupon_by_company_id")
    Call<List<CouponItem>> getUsedCoupon(@Header("Authorization") String token,
                                         @Query("company_id") String company_id,
                                         @Query("utc1") long utc1, @Query("utc2") long utc2);
}
