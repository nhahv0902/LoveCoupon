package com.nhahv.lovecoupon.ui.shop.notification;

import android.content.Context;
import android.databinding.ObservableArrayList;
import android.databinding.ObservableField;
import android.databinding.ObservableList;
import android.support.annotation.NonNull;

import com.nhahv.lovecoupon.data.model.NotificationItem;

/**
 * Created by Nhahv0902 on 3/6/2017.
 * <></>
 */
public class NotificationViewModel {
    private final Context mContext;
    private final IShopNotification mIShopNotification;
    private ObservableField<NotificationAdapter> mAdapter = new ObservableField<>();
    private ObservableList<NotificationItem> mListNotification = new ObservableArrayList<>();

    public NotificationViewModel(@NonNull Context context, IShopNotification iShopNotification) {
        mContext = context;
        mIShopNotification = iShopNotification;
        mAdapter.set(new NotificationAdapter(mListNotification));
        mListNotification.add(new NotificationItem());
        mListNotification.add(new NotificationItem());
        mListNotification.add(new NotificationItem());
        mListNotification.add(new NotificationItem());
        mAdapter.get().update(mListNotification);
    }

    public ObservableField<NotificationAdapter> getAdapter() {
        return mAdapter;
    }
}
