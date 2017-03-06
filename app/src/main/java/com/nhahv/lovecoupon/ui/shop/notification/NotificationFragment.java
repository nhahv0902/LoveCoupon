package com.nhahv.lovecoupon.ui.shop.notification;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.nhahv.lovecoupon.databinding.FragmentNotificationBinding;

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
}
