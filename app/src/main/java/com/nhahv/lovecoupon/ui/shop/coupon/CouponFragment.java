package com.nhahv.lovecoupon.ui.shop.coupon;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.nhahv.lovecoupon.databinding.FragmentNotificationBinding;

import static android.app.Activity.RESULT_OK;
import static com.nhahv.lovecoupon.util.Constant.RequestConstant.REQUEST_TEMPLATE;

/**
 * A simple {@link Fragment} subclass.
 */
public class CouponFragment extends Fragment {
    private FragmentNotificationBinding mBinding;
    private CouponViewModel mViewModel;

    public static Fragment newInstance() {
        return new CouponFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup view, Bundle savedInstanceState) {
        mBinding = FragmentNotificationBinding.inflate(inflater, view, false);
        mViewModel = new CouponViewModel(getActivity());
        mBinding.setViewModel(mViewModel);
        return mBinding.getRoot();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode != RESULT_OK && requestCode == REQUEST_TEMPLATE) mViewModel.loadData();
    }
}
