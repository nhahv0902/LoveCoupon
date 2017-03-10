package com.nhahv.lovecoupon.ui.shop.notificationcreation;

import android.content.Context;
import android.databinding.BaseObservable;
import android.support.annotation.NonNull;

import com.nhahv.lovecoupon.data.model.Notification;

/**
 * Created by Nhahv0902 on 3/10/2017.
 * <></>
 */
public class NotificationCreationViewModel extends BaseObservable {
    private final Context mContext;
    private final ActionNotificationType mType;
    private final Notification mNotification;

    public NotificationCreationViewModel(@NonNull Context context,
                                         @NonNull Notification notification,
                                         @NonNull ActionNotificationType type) {
        mContext = context;
        mNotification = notification;
        mType = type;
    }

    public void clickPickDate() {
    }

    public void clickPickImage() {
    }

    public void clickCreateNotification() {
    }

    public Notification getNotification() {
        return mNotification;
    }
}
