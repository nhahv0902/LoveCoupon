package com.nhahv.lovecoupon.ui.customer.notification;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.nhahv.lovecoupon.databinding.FragmentCustomerNotificationBinding;

/**
 * A simple {@link Fragment} subclass.
 */
public class NotificationFragment extends Fragment {
    private FragmentCustomerNotificationBinding mBinding;
    private NotificationViewModel mViewModel;

    public static NotificationFragment newInstance() {
        return new NotificationFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle state) {
        mBinding = FragmentCustomerNotificationBinding.inflate(inflater, container, false);
        mViewModel = new NotificationViewModel(getActivity());
        mBinding.setViewModel(mViewModel);
        return mBinding.getRoot();
    }
}
