package com.nhahv.lovecoupon.data.source.remote.notification;

import android.support.annotation.NonNull;

import com.nhahv.lovecoupon.data.model.Notification;
import com.nhahv.lovecoupon.data.source.Callback;

import java.util.List;

/**
 * Created by Nhahv0902 on 3/8/2017.
 * <></>
 */
public interface NotificationDataSource {
    void getNotificationShop(String idShop, @NonNull Callback<List<Notification>> callback);
}
