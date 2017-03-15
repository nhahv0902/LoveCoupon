package com.nhahv.lovecoupon.ui.customer.coupon;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.nhahv.lovecoupon.data.model.CouponCustomer;
import com.nhahv.lovecoupon.databinding.FragmentNotificationBinding;
import com.nhahv.lovecoupon.ui.customer.couponofshop.CouponOfShopActivity;

import static android.app.Activity.RESULT_OK;
import static com.nhahv.lovecoupon.util.Constant.RequestConstant.REQUEST_UI_COUPON_OF_SHOP;

/**
 * A simple {@link Fragment} subclass.
 */
public class CouponFragment extends Fragment implements CouponHandler {
    private FragmentNotificationBinding mBinding;
    private CouponViewModel mViewModel;

    public static Fragment newInstance() {
        return new CouponFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup view, Bundle savedInstanceState) {
        mBinding = FragmentNotificationBinding.inflate(inflater, view, false);
        mViewModel = new CouponViewModel(getActivity(), this);
        mBinding.setViewModel(mViewModel);
        return mBinding.getRoot();
    }

    @Override
    public void startUiCouponOfShop(CouponCustomer coupon) {
        startActivityForResult(CouponOfShopActivity.getIntent(getActivity(), coupon),
            REQUEST_UI_COUPON_OF_SHOP);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && requestCode == REQUEST_UI_COUPON_OF_SHOP) {
            mViewModel.loadData();
        }
    }

    @Override
    public void loadData() {
        mViewModel.loadData();
    }
}
