package com.nhahv.lovecoupon.ui.shop.notification;

import android.content.Intent;
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

import static android.app.Activity.RESULT_OK;
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
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && requestCode == REQUEST_NOTIFICATION) mViewModel.loadData();
    }

    @Override
    public void editNotification(@NonNull Notification notification) {
        startActivityForResult(NotificationCreationActivity
                .getNotificationIntent(getActivity(), notification, ActionNotificationType.EDIT),
            REQUEST_NOTIFICATION);
    }
}
