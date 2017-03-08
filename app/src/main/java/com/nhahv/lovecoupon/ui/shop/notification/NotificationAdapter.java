package com.nhahv.lovecoupon.ui.shop.notification;

import android.databinding.DataBindingUtil;
import android.databinding.ObservableList;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.nhahv.lovecoupon.R;
import com.nhahv.lovecoupon.data.model.Notification;
import com.nhahv.lovecoupon.databinding.ItemNotificationBinding;

/**
 * Created by Nhahv0902 on 3/6/2017.
 * <></>
 */
public class NotificationAdapter
    extends RecyclerView.Adapter<NotificationAdapter.NotificationHolder> {
    private LayoutInflater mInflater;
    private ObservableList<Notification> mListNotification;

    public NotificationAdapter(ObservableList<Notification> notification) {
        mListNotification = notification;
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
        Notification item = mListNotification.get(position);
        if (item != null) holder.bind(item);
    }

    @Override
    public int getItemCount() {
        return mListNotification == null ? 0 : mListNotification.size();
    }

    public class NotificationHolder extends RecyclerView.ViewHolder {
        private ItemNotificationBinding mBinding;

        public NotificationHolder(ItemNotificationBinding binding) {
            super(binding.getRoot());
            mBinding = binding;
        }

        private void bind(Notification item) {
            mBinding.setNotification(item);
            mBinding.setUrl(
                "http://tophinhanhdep.net/wp-content/uploads/2015/12/anh-dep-mua-xuan-5.jpg");
            mBinding.setShopName("Coffee Ha Noi");
            mBinding.executePendingBindings();
        }
    }
}
