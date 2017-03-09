package com.nhahv.lovecoupon.networking.api;

import com.nhahv.lovecoupon.data.model.Notification;
import com.nhahv.lovecoupon.data.model.NotificationCustomer;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by Nhahv0902 on 3/8/2017.
 * <></>
 */
public interface NotificationService {
    /*
    * Notification Of Shop
    * */
    @GET("/get_news_by_company_id")
    Call<List<Notification>> getNotificationShop(@Query("company_id") String id);
    /*
    * Notification Of Customer
    * */
    @GET("/get_news_by_user_id")
    Call<List<NotificationCustomer>> getNotificationCustomer(@Query("user_id") String id);
    @GET("/get_news_more_by_user_id")
    Call<List<NotificationCustomer>> getOtherNotificationCustomer(@Query("user_id") String id,
                                                                  @Query("city") String city);
}
