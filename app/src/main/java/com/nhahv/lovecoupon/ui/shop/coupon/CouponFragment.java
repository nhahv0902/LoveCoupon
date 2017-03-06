package com.nhahv.lovecoupon.ui.shop.coupon;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.nhahv.lovecoupon.databinding.FragmentCouponBinding;

/**
 * A simple {@link Fragment} subclass.
 */
public class CouponFragment extends Fragment implements ICoupon {
    private FragmentCouponBinding mBinding;
    private CouponViewModel mViewModel;

    public static Fragment newInstance() {
        return new CouponFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup view, Bundle savedInstanceState) {
        mBinding = FragmentCouponBinding.inflate(inflater, view, false);
        mViewModel = new CouponViewModel(getActivity(), this);
        mBinding.setViewModel(mViewModel);
        return mBinding.getRoot();
    }
}
