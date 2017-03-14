package com.nhahv.lovecoupon.ui.shop.notification;

import android.databinding.ObservableArrayList;
import android.databinding.ObservableList;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.nhahv.lovecoupon.data.model.Notification;
import com.nhahv.lovecoupon.data.model.ShopProfile;
import com.nhahv.lovecoupon.databinding.ItemNotificationBinding;
import com.nhahv.lovecoupon.ui.shop.notificationcreation.NotificationCreationAdapter;

import java.util.Collections;

/**
 * Created by Nhahv0902 on 3/6/2017.
 * <></>
 */
public class NotificationAdapter
    extends RecyclerView.Adapter<NotificationAdapter.NotificationHolder> {
    private LayoutInflater mInflater;
    private final ObservableList<Notification> mListNotification;
    private final NotificationViewModel mViewModel;
    private final ShopProfile mProfile;

    public NotificationAdapter(@NonNull NotificationViewModel viewModel,
                               @NonNull ObservableList<Notification> notification,
                               @NonNull ShopProfile profile) {
        mViewModel = viewModel;
        mProfile = profile;
        mListNotification = notification;
    }

    @Override
    public NotificationHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (mInflater == null) mInflater = LayoutInflater.from(parent.getContext());
        ItemNotificationBinding binding = ItemNotificationBinding.inflate(mInflater, parent, false);
        binding.setViewModel(mViewModel);
        return new NotificationHolder(binding);
    }

    @Override
    public void onBindViewHolder(NotificationHolder holder, int position) {
        Notification item = mListNotification.get(position);
        if (item != null) holder.bind(item, position);
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

        private void bind(Notification item, int position) {
            ObservableList<String> listImages = new ObservableArrayList<>();
            if (item.getLinkImage() != null) {
                String[] listStrImages = item.getLinkImage().split(";");
                Collections.addAll(listImages, listStrImages);
            }
            NotificationCreationAdapter adapter =
                new NotificationCreationAdapter(mViewModel, listImages, false);
            mBinding.setAdapter(adapter);
            mBinding.setNotification(item);
            mBinding.setPosition(position);
            mBinding.setUrl(mProfile.getLogoLink());
            mBinding.setShopName(mProfile.getName());
            mBinding.executePendingBindings();
        }
    }
}
