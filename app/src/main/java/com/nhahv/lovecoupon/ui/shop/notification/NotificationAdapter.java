package com.nhahv.lovecoupon.ui.shop.notification;

import android.databinding.DataBindingUtil;
import android.databinding.ObservableArrayList;
import android.databinding.ObservableList;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.nhahv.lovecoupon.R;
import com.nhahv.lovecoupon.data.model.NotificationItem;
import com.nhahv.lovecoupon.databinding.ItemNotificationBinding;

import java.util.List;

/**
 * Created by Nhahv0902 on 3/6/2017.
 * <></>
 */
public class NotificationAdapter
    extends RecyclerView.Adapter<NotificationAdapter.NotificationHolder> {
    private LayoutInflater mInflater;
    private ObservableList<NotificationItem> mListNotification = new ObservableArrayList<>();

    public NotificationAdapter(ObservableList<NotificationItem> notification) {
        mListNotification.addAll(notification);
    }

    public void update(List<NotificationItem> notificationItems) {
        mListNotification.clear();
        mListNotification.addAll(notificationItems);
        notifyDataSetChanged();
    }

    @Override
    public NotificationHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (mInflater == null) mInflater = LayoutInflater.from(parent.getContext());
        ItemNotificationBinding binding =
            DataBindingUtil.inflate(mInflater, R.layout.item_notification, parent, false);
        return new NotificationHolder(binding);
    }

    @Override
    public void onBindViewHolder(NotificationHolder holder, int position) {
        NotificationItem item = mListNotification.get(position);
        if (item != null) holder.bind(item);
    }

    @Override
    public int getItemCount() {
        return mListNotification.size();
    }

    public class NotificationHolder extends RecyclerView.ViewHolder {
        private ItemNotificationBinding mBinding;

        public NotificationHolder(ItemNotificationBinding binding) {
            super(binding.getRoot());
            mBinding = binding;
        }

        private void bind(NotificationItem item) {
            mBinding.setNotification(item);
            mBinding.setUrl(
                "http://tophinhanhdep.net/wp-content/uploads/2015/12/anh-dep-mua-xuan-5.jpg");
            mBinding.setShopName("Coffee Ha Noi");
            mBinding.executePendingBindings();
        }
    }
}
