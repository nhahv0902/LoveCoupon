package com.nhahv.lovecoupon.data.source.remote.notification;

import android.support.annotation.NonNull;

import com.nhahv.lovecoupon.data.model.Notification;
import com.nhahv.lovecoupon.data.model.NotificationCustomer;
import com.nhahv.lovecoupon.data.source.Callback;

import java.util.List;

/**
 * Created by Nhahv0902 on 3/8/2017.
 * <></>
 */
public interface NotificationDataSource {
    void getNotificationShop(@NonNull String idShop,
                             @NonNull Callback<List<Notification>> callback);
    void deleteNotification(@NonNull String token, @NonNull Notification notification,
                            @NonNull Callback<Boolean> callback);
    void getNotificationCustomer(@NonNull String id,
                                 @NonNull Callback<List<NotificationCustomer>> callback);
    void getOtherNotificationCustomer(@NonNull String id, @NonNull String city,
                                      @NonNull Callback<List<NotificationCustomer>> callback);
}
