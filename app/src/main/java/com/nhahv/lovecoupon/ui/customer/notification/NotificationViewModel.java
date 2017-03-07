package com.nhahv.lovecoupon.ui.customer.notification;

import android.content.Context;
import android.databinding.BaseObservable;
import android.databinding.ObservableArrayList;
import android.databinding.ObservableField;
import android.databinding.ObservableList;
import android.support.annotation.NonNull;

import com.nhahv.lovecoupon.data.model.NotificationItem;

/**
 * Created by Nhahv0902 on 3/6/2017.
 * <></>
 */
public class NotificationViewModel extends BaseObservable {
    private final Context mContext;
    private ObservableField<NotificationAdapter> mAdapter = new ObservableField<>();
    private ObservableList<NotificationItem> mListNotification = new ObservableArrayList<>();

    public NotificationViewModel(@NonNull Context context) {
        mContext = context;
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
