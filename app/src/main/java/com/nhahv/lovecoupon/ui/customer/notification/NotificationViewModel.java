package com.nhahv.lovecoupon.ui.customer.notification;

import android.content.Context;
import android.databinding.BaseObservable;
import android.databinding.ObservableArrayList;
import android.databinding.ObservableBoolean;
import android.databinding.ObservableField;
import android.databinding.ObservableList;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.nhahv.lovecoupon.R;
import com.nhahv.lovecoupon.data.model.Notification;
import com.nhahv.lovecoupon.data.model.NotificationCustomer;
import com.nhahv.lovecoupon.data.source.Callback;
import com.nhahv.lovecoupon.data.source.remote.notification.NotificationRepository;
import com.nhahv.lovecoupon.ui.INotificationViewModel;
import com.nhahv.lovecoupon.ui.ViewModel;
import com.nhahv.lovecoupon.util.ActivityUtil;

import java.util.List;

/**
 * Created by Nhahv0902 on 3/6/2017.
 * <></>
 */
public class NotificationViewModel extends BaseObservable
    implements ViewModel, INotificationViewModel {
    private final Context mContext;
    private final ObservableList<NotificationCustomer> mListNotification =
        new ObservableArrayList<>();
    private final NotificationType mType;
    private final NotificationRepository mRepository;

    public NotificationViewModel(@NonNull Context context, @NonNull NotificationType type) {
        mContext = context;
        mType = type;
        mRepository = NotificationRepository.getInstance();
        mAdapter.set(new NotificationAdapter(this, mListNotification));
        loadData();
    }

    @Override
    public void loadData() {
        if (mRepository == null) return;
        switch (mType) {
            case NOTIFICATION:
                mRepository.getNotificationCustomer("nhahv0902@gmail.com",
                    new Callback<List<NotificationCustomer>>() {
                        @Override
                        public void onSuccess(List<NotificationCustomer> data) {
                            mListNotification.clear();
                            mListNotification.addAll(data);
                            mAdapter.get().notifyDataSetChanged();
                            setRefresh(false);
                        }

                        @Override
                        public void onError() {
                            loadError();
                        }
                    });
                break;
            case NOTIFICATION_OTHER:
                mRepository.getOtherNotificationCustomer("nhahv0902@gmail.com", "HaNoi",
                    new Callback<List<NotificationCustomer>>() {
                        @Override
                        public void onSuccess(List<NotificationCustomer> data) {
                            mListNotification.clear();
                            mListNotification.addAll(data);
                            mAdapter.get().notifyDataSetChanged();
                            setRefresh(false);
                        }

                        @Override
                        public void onError() {
                            loadError();
                        }
                    });
                break;
            default:
                break;
        }
    }

    @Override
    public void loadError() {
        ActivityUtil.showMsg(mContext, R.string.msg_load_data_error);
    }

    @Override
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

    @Override
    public void preview(List<String> images, int position) {
    }

    @Override
    public void clickDelete(int position) {
    }

    @Override
    public void clickMore(View view, Notification notification) {
    }

    @Override
    public void clickShare(Notification notification) {
    }
}
