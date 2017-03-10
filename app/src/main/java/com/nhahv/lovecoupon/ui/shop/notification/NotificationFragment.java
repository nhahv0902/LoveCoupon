package com.nhahv.lovecoupon.ui.shop.notification;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.nhahv.lovecoupon.data.model.Notification;
import com.nhahv.lovecoupon.databinding.FragmentNotificationBinding;
import com.nhahv.lovecoupon.ui.shop.notificationcreation.ActionNotificationType;
import com.nhahv.lovecoupon.ui.shop.notificationcreation.NotificationCreationActivity;

import static com.nhahv.lovecoupon.util.Constant.RequestConstant.REQUEST_NOTIFICATION;

/**
 * A simple {@link Fragment} subclass.
 */
public class NotificationFragment extends Fragment implements IShopNotification {
    private FragmentNotificationBinding mBinding;
    private NotificationViewModel mViewModel;

    public static NotificationFragment newInstance() {
        return new NotificationFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle state) {
        mBinding = FragmentNotificationBinding.inflate(inflater, container, false);
        mViewModel = new NotificationViewModel(getActivity(), this);
        mBinding.setViewModel(mViewModel);
        return mBinding.getRoot();
    }

    @Override
    public void editNotification(@NonNull Notification notification) {
        startActivityForResult(NotificationCreationActivity
                .getNotificationIntent(getActivity(), notification, ActionNotificationType.EDIT),
            REQUEST_NOTIFICATION);
    }

    public void loadData() {
        mViewModel.loadData();
    }
}
