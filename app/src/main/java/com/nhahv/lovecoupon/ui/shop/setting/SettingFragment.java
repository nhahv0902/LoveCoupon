package com.nhahv.lovecoupon.ui.shop.setting;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.nhahv.lovecoupon.databinding.FragmentSettingBinding;
import com.nhahv.lovecoupon.ui.shop.main.IShopMainHandler;

import static com.nhahv.lovecoupon.util.Constant.DataConstant.DATA_PICK_LOGO;
import static com.nhahv.lovecoupon.util.Constant.RequestConstant.REQUEST_OPEN_GALLERY;

public class SettingFragment extends Fragment implements ISettingFragment {
    private FragmentSettingBinding mBinding;
    private SettingViewModel mViewModel;
    private IShopMainHandler mListener;

    public static SettingFragment newInstance(@NonNull IShopMainHandler handler) {
        SettingFragment fragment = new SettingFragment();
        fragment.setShopMainListener(handler);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        mBinding = FragmentSettingBinding.inflate(inflater, container, false);
        mViewModel = new SettingViewModel(getActivity(), this, mListener);
        mBinding.setViewModel(mViewModel);
        return mBinding.getRoot();
    }

    @Override
    public void openGallery() {
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("image/*");
        startActivityForResult(Intent.createChooser(intent, DATA_PICK_LOGO), REQUEST_OPEN_GALLERY);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        mViewModel.handlerResult(requestCode, resultCode, data, this);
    }

    public void updateProfile() {
        mViewModel.updateProfileShop();
    }

    public void setShopMainListener(@NonNull IShopMainHandler shopMainListener) {
        mListener = shopMainListener;
    }
}
