package com.nhahv.lovecoupon.networking.api;

import com.nhahv.lovecoupon.data.model.CouponTemplate;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by Nhahv0902 on 3/7/2017.
 * <></>
 */
public interface CouponTemplateService {
    @GET("get_coupon_template_by_company_id")
    Call<List<CouponTemplate>> getCoupon(@Query("company_id") String id);
}
