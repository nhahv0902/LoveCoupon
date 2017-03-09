package com.nhahv.lovecoupon.ui.shop.notification;

import android.content.Context;
import android.databinding.ObservableArrayList;
import android.databinding.ObservableBoolean;
import android.databinding.ObservableField;
import android.databinding.ObservableList;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;

import com.nhahv.lovecoupon.R;
import com.nhahv.lovecoupon.data.model.Notification;
import com.nhahv.lovecoupon.data.source.Callback;
import com.nhahv.lovecoupon.data.source.remote.notification.NotificationRepository;
import com.nhahv.lovecoupon.ui.ViewModel;
import com.nhahv.lovecoupon.util.ActivityUtil;

import java.util.List;

import static com.nhahv.lovecoupon.util.Constant.DataConstant.DATA_ID_SHOP;

/**
 * Created by Nhahv0902 on 3/6/2017.
 * <></>
 */
public class NotificationViewModel implements ViewModel {
    private final Context mContext;
    private final IShopNotification mIShopNotification;
    private final NotificationRepository mRepository;
    private final ObservableList<Notification> mListNotification = new ObservableArrayList<>();

    public NotificationViewModel(@NonNull Context context, IShopNotification iShopNotification) {
        mContext = context;
        mIShopNotification = iShopNotification;
        mRepository = NotificationRepository.getInstance();
        mAdapter.set(new NotificationAdapter(mListNotification));
        loadData();
    }

    @Override
    public void loadData() {
        if (mRepository == null) return;
        mRepository.getNotificationShop(DATA_ID_SHOP, new Callback<List<Notification>>() {
            @Override
            public void onSuccess(List<Notification> data) {
                mListNotification.clear();
                mListNotification.addAll(data);
                mAdapter.get().notifyDataSetChanged();
                setRefresh(false);
            }

            @Override
            public void onError() {
                loadError();
                setRefresh(false);
            }
        });
    }

    @Override
    public void loadError() {
        ActivityUtil.showMsg(mContext, R.string.msg_load_data_error);
    }

    public ObservableBoolean getRefresh() {
        return mRefresh;
    }

    @Override
    public void setRefresh(boolean isRefresh) {
        mRefresh.set(isRefresh);
    }

    @Override
    public ObservableField<RecyclerView.Adapter> getAdapter() {
        return mAdapter;
    }
}
