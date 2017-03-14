package com.nhahv.lovecoupon.ui.shop.notification;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.afollestad.materialdialogs.MaterialDialog;
import com.nhahv.lovecoupon.R;
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
        mViewModel = new NotificationViewModel(getActivity(), this, this);
        mBinding.setViewModel(mViewModel);
        return mBinding.getRoot();
    }

    @Override
    public void editNotification(@NonNull Notification notification) {
        startActivityForResult(NotificationCreationActivity
                .getNotificationIntent(getActivity(), notification, ActionNotificationType.EDIT),
            REQUEST_NOTIFICATION);
    }

    @Override
    public void showDialogDeleteNotification(Notification notification) {
        new MaterialDialog
            .Builder(getActivity())
            .content(R.string.title_delete_notification)
            .contentColor(ContextCompat.getColor(getActivity(), R.color.color_grey_700))
            .positiveText(R.string.agree)
            .positiveColor(ContextCompat.getColor(getActivity(), R.color.color_blue_600))
            .onPositive((dialog, which) -> mViewModel.deleteNotification(notification))
            .show();
    }

    public void loadData() {
        mViewModel.loadData();
    }
}
