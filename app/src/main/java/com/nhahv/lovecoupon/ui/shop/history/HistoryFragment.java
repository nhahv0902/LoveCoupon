package com.nhahv.lovecoupon.ui.shop.history;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.nhahv.lovecoupon.databinding.FragmentHistoryBinding;

/**
 * A simple {@link Fragment} subclass.
 */
public class HistoryFragment extends Fragment implements IHistoryFragment {
    private FragmentHistoryBinding mBinding;

    public static HistoryFragment newInstance() {
        return new HistoryFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle state) {
        mBinding = FragmentHistoryBinding.inflate(inflater, container, false);
        mBinding.setViewModel(new HistoryViewModel(getActivity(), this, getChildFragmentManager()));
        return mBinding.getRoot();
    }
}
