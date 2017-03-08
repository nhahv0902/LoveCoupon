package com.nhahv.lovecoupon.ui.shop.history.usecoupon;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.nhahv.lovecoupon.databinding.FragmentUseCreateBinding;
import com.nhahv.lovecoupon.ui.shop.history.UseCreateType;

import static com.nhahv.lovecoupon.util.Constant.BundleConstant.BUNDLE_ACCOUNT_TYPE;
import static com.nhahv.lovecoupon.util.Constant.BundleConstant.BUNDLE_USE_CREATE_TYPE;

/**
 * A simple {@link Fragment} subclass.
 */
public class UseCreateFragment extends Fragment {
    private FragmentUseCreateBinding mBinding;
    private UseCreateViewModel mViewModel;

    public static UseCreateFragment newInstance(UseCreateType type) {
        UseCreateFragment fragment = new UseCreateFragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable(BUNDLE_USE_CREATE_TYPE, type);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle state) {
        mBinding = FragmentUseCreateBinding.inflate(inflater, container, false);
        mViewModel = new UseCreateViewModel(getActivity(), getDataFromActivity());
        mBinding.setViewModel(mViewModel);
        return mBinding.getRoot();
    }

    private UseCreateType getDataFromActivity() {
        Bundle bundle = getArguments();
        if (bundle == null) return UseCreateType.CREATE;
        return (UseCreateType) bundle.getSerializable(BUNDLE_ACCOUNT_TYPE);
    }

    public void onDateChange(long time) {
        mViewModel.updateTime(time);
    }
}
