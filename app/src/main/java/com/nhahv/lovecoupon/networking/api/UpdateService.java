package com.nhahv.lovecoupon.networking.api;

import com.nhahv.lovecoupon.data.model.ProfileShop;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Created by Nhahv0902 on 3/9/2017.
 * <></>
 */
public interface UpdateService {
    @GET("/is_username_avaiable")
    Call<Integer> isUserExists(@Query("company_id") String company_id,
                               @Query("username") String username);
    @POST("/updateCompany")
    Call<Integer> updateProfile(@Header("Authorization") String token, @Body ProfileShop template);
}
