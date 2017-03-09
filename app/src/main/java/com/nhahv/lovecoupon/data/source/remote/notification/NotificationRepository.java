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
public class NotificationRepository implements NotificationDataSource {
    private static NotificationRepository sRepository;
    private NotificationDataSource mDataSource;

    private NotificationRepository() {
        mDataSource = NotificationRemoteDataSource.getInstance();
    }

    public static NotificationRepository getInstance() {
        if (sRepository == null) sRepository = new NotificationRepository();
        return sRepository;
    }

    @Override
    public void getNotificationShop(String idShop, @NonNull Callback<List<Notification>> callback) {
        if (mDataSource == null) return;
        mDataSource.getNotificationShop(idShop, new Callback<List<Notification>>() {
            @Override
            public void onSuccess(List<Notification> data) {
                callback.onSuccess(data);
            }

            @Override
            public void onError() {
                callback.onError();
            }
        });
    }

    @Override
    public void getNotificationCustomer(@NonNull String id,
                                        @NonNull Callback<List<NotificationCustomer>> callback) {
        if (mDataSource == null) return;
        mDataSource.getNotificationCustomer(id, new Callback<List<NotificationCustomer>>() {
            @Override
            public void onSuccess(List<NotificationCustomer> data) {
                callback.onSuccess(data);
            }

            @Override
            public void onError() {
                callback.onError();
            }
        });
    }

    @Override
    public void getOtherNotificationCustomer(@NonNull String id, @NonNull String city, @NonNull
        Callback<List<NotificationCustomer>> callback) {
        if (mDataSource == null) return;
        mDataSource
            .getOtherNotificationCustomer(id, city, new Callback<List<NotificationCustomer>>() {
                @Override
                public void onSuccess(List<NotificationCustomer> data) {
                    callback.onSuccess(data);
                }

                @Override
                public void onError() {
                    callback.onError();
                }
            });
    }
}
