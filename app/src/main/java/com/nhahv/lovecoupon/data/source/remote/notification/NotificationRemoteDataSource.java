package com.nhahv.lovecoupon.data.source.remote.notification;

import android.support.annotation.NonNull;

import com.nhahv.lovecoupon.data.model.Notification;
import com.nhahv.lovecoupon.data.source.Callback;
import com.nhahv.lovecoupon.networking.ServiceGenerator;
import com.nhahv.lovecoupon.networking.api.NotificationService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Response;

/**
 * Created by Nhahv0902 on 3/8/2017.
 * <></>
 */
public class NotificationRemoteDataSource implements NotificationDataSource {
    private static NotificationDataSource sDataSource;
    private NotificationService mService;

    private NotificationRemoteDataSource() {
        mService = ServiceGenerator.createService(NotificationService.class);
    }

    public static NotificationDataSource getInstance() {
        if (sDataSource == null) sDataSource = new NotificationRemoteDataSource();
        return sDataSource;
    }

    @Override
    public void getNotificationShop(String idShop, @NonNull Callback<List<Notification>> callback) {
        if (mService == null) return;
        mService.getNotificationShop(idShop).enqueue(new retrofit2.Callback<List<Notification>>() {
            @Override
            public void onResponse(Call<List<Notification>> call,
                                   Response<List<Notification>> response) {
                if (response.body() != null) callback.onSuccess(response.body());
                else callback.onError();
            }

            @Override
            public void onFailure(Call<List<Notification>> call, Throwable t) {
                callback.onError();
            }
        });
    }
}