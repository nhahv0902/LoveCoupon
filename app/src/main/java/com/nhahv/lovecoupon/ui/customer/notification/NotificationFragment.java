package com.nhahv.lovecoupon.ui.customer.notification;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.nhahv.lovecoupon.databinding.FragmentNotificationBinding;

import org.parceler.Parcels;

import static com.nhahv.lovecoupon.util.Constant.BundleConstant.BUNDLE_NOTIFICATION;

/**
 * A simple {@link Fragment} subclass.
 */
public class NotificationFragment extends Fragment {
    private FragmentNotificationBinding mBinding;
    private NotificationViewModel mViewModel;

    public static NotificationFragment newInstance(NotificationType type) {
        NotificationFragment fragment = new NotificationFragment();
        Bundle bundle = new Bundle();
        bundle.putParcelable(BUNDLE_NOTIFICATION, Parcels.wrap(type));
        fragment.setArguments(bundle);
        return fragment;
    }

    private NotificationType getDataFromActivity() {
        Bundle bundle = getArguments();
        if (bundle == null || bundle.getParcelable(BUNDLE_NOTIFICATION) == null) {
            return NotificationType.NOTIFICATION;
        }
        return Parcels.unwrap(bundle.getParcelable(BUNDLE_NOTIFICATION));
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle state) {
        mBinding = FragmentNotificationBinding.inflate(inflater, container, false);
        mViewModel = new NotificationViewModel(getActivity(), this, getDataFromActivity());
        mBinding.setViewModel(mViewModel);
        return mBinding.getRoot();
    }
}
