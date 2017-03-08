package com.nhahv.lovecoupon.networking.api;

import com.nhahv.lovecoupon.data.model.Notification;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by Nhahv0902 on 3/8/2017.
 * <></>
 */
public interface NotificationService {
    @GET("/get_news_by_company_id")
    Call<List<Notification>> getNotificationShop(@Query("company_id") String id);
}
