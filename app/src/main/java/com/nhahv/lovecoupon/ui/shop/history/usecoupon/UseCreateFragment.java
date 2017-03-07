package com.nhahv.lovecoupon.ui.shop.history.usecoupon;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.nhahv.lovecoupon.databinding.FragmentUseCreateBinding;

/**
 * A simple {@link Fragment} subclass.
 */
public class UseCreateFragment extends Fragment {
    private FragmentUseCreateBinding mBinding;

    public static UseCreateFragment newInstance() {
        return new UseCreateFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle state) {
        mBinding = FragmentUseCreateBinding.inflate(inflater, container, false);
        mBinding.setViewModel(new UseCreateViewModel(getActivity()));
        return mBinding.getRoot();
    }
}
