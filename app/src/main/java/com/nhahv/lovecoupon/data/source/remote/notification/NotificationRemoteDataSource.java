package com.nhahv.lovecoupon.data.source.remote.notification;

import android.support.annotation.NonNull;

import com.nhahv.lovecoupon.data.model.Notification;
import com.nhahv.lovecoupon.data.model.NotificationCustomer;
import com.nhahv.lovecoupon.data.source.Callback;
import com.nhahv.lovecoupon.networking.ServiceGenerator;
import com.nhahv.lovecoupon.networking.api.NotificationService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Response;

import static com.nhahv.lovecoupon.util.Constant.DataConstant.SUCCESS;

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
    public void getNotificationShop(@NonNull String idShop,
                                    @NonNull Callback<List<Notification>> callback) {
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

    @Override
    public void deleteNotification(@NonNull String token, @NonNull Notification notification,
                                   @NonNull Callback<Boolean> callback) {
        if (mService == null) return;
        mService.deleteNotification(token, notification).enqueue(new retrofit2.Callback<Integer>() {
            @Override
            public void onResponse(Call<Integer> call, Response<Integer> response) {
                if (response == null || response.body() == null || response.body() != SUCCESS) {
                    callback.onError();
                } else callback.onSuccess(true);
            }

            @Override
            public void onFailure(Call<Integer> call, Throwable t) {
                callback.onError();
            }
        });
    }

    @Override
    public void getNotificationCustomer(@NonNull String id,
                                        @NonNull Callback<List<NotificationCustomer>> callback) {
        if (mService == null) return;
        mService.getNotificationCustomer(id).enqueue(
            new retrofit2.Callback<List<NotificationCustomer>>() {
                @Override
                public void onResponse(Call<List<NotificationCustomer>> call,
                                       Response<List<NotificationCustomer>> response) {
                    if (response == null || response.body() == null) callback.onError();
                    else callback.onSuccess(response.body());
                }

                @Override
                public void onFailure(Call<List<NotificationCustomer>> call, Throwable t) {
                    callback.onError();
                }
            });
    }

    @Override
    public void getOtherNotificationCustomer(@NonNull String id, @NonNull String city, @NonNull
        Callback<List<NotificationCustomer>> callback) {
        if (mService == null) return;
        mService.getOtherNotificationCustomer(id, city).enqueue(
            new retrofit2.Callback<List<NotificationCustomer>>() {
                @Override
                public void onResponse(Call<List<NotificationCustomer>> call,
                                       Response<List<NotificationCustomer>> response) {
                    if (response == null || response.body() == null) callback.onError();
                    else callback.onSuccess(response.body());
                }

                @Override
                public void onFailure(Call<List<NotificationCustomer>> call, Throwable t) {
                    callback.onError();
                }
            });
    }

    @Override
    public void createNotification(@NonNull String token, @NonNull Notification notification,
                                   @NonNull Callback<Boolean> callback) {
        if (mService == null) return;
        mService.createNotification(token, notification).enqueue(new retrofit2.Callback<Integer>() {
            @Override
            public void onResponse(Call<Integer> call, Response<Integer> response) {
                if (response == null || response.body() == null || response.body() != SUCCESS) {
                    callback.onError();
                } else callback.onSuccess(true);
            }

            @Override
            public void onFailure(Call<Integer> call, Throwable t) {
                callback.onError();
            }
        });
    }
}
