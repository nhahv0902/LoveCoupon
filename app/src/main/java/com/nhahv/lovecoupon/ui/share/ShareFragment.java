package com.nhahv.lovecoupon.ui.share;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.nhahv.lovecoupon.databinding.FragmentShareBinding;

/**
 * A simple {@link Fragment} subclass.
 */
public class ShareFragment extends Fragment {
    private FragmentShareBinding mBinding;

    public static Fragment newInstance() {
        return new ShareFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mBinding = FragmentShareBinding.inflate(inflater, container, false);
        mBinding.setViewModel(new ShareViewModel(getActivity()));
        return mBinding.getRoot();
    }
}
