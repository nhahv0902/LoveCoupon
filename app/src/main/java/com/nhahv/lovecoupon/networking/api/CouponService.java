package com.nhahv.lovecoupon.networking.api;

import com.nhahv.lovecoupon.data.model.Coupon;
import com.nhahv.lovecoupon.data.model.CouponCustomer;
import java.util.List;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Created by Nhahv0902 on 3/8/2017.
 * <></>
 */
public interface CouponService {
    /*
    * Coupon of shop
    * */
    @GET("/get_created_coupon_by_company_id")
    Call<List<Coupon>> getCreatedCoupon(@Header("Authorization") String token,
            @Query("company_id") String company_id, @Query("utc1") long utc1,
            @Query("utc2") long utc2);

    @GET("/get_used_coupon_by_company_id")
    Call<List<Coupon>> getUsedCoupon(@Header("Authorization") String token,
            @Query("company_id") String company_id, @Query("utc1") long utc1,
            @Query("utc2") long utc2);

    /*
    * coupon of customer
    * */
    @GET("/get_companies_by_user_id")
    Call<List<CouponCustomer>> getCouponOfCustomer(@Query("user_id") String id);

    @POST("/useCoupon")
    Call<Integer> useCoupon(@Body Coupon coupon);

    @POST("/update_user_coupon")
    Call<List<CouponCustomer>> addCoupon(@Header("city") String city, @Body Coupon coupon);
}
