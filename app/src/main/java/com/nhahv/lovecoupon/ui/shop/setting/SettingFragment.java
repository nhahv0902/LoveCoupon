package com.nhahv.lovecoupon.ui.shop.setting;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.nhahv.lovecoupon.data.source.remote.updateprofile.UpdateRepository;
import com.nhahv.lovecoupon.databinding.FragmentSettingBinding;

import static com.nhahv.lovecoupon.util.Constant.DataConstant.DATA_PICK_LOGO;
import static com.nhahv.lovecoupon.util.Constant.RequestConstant.REQUEST_OPEN_GALLERY;

public class SettingFragment extends Fragment implements ISettingFragment {
    private FragmentSettingBinding mBinding;
    private SettingViewModel mViewModel;

    public static SettingFragment newInstance() {
        return new SettingFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mBinding = FragmentSettingBinding.inflate(inflater, container, false);
        mViewModel = new SettingViewModel(getActivity(), this, UpdateRepository.getInstance());
        mBinding.setViewModel(mViewModel);
        return mBinding.getRoot();
    }

    @Override
    public void openGallery() {
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("image/*");
        Intent pickIntent = new Intent(Intent.ACTION_PICK,
            android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        pickIntent.setType("image/*");
        Intent chooserIntent = Intent.createChooser(intent, DATA_PICK_LOGO);
        chooserIntent.putExtra(Intent.EXTRA_INITIAL_INTENTS, new Intent[]{pickIntent});
        startActivityForResult(chooserIntent, REQUEST_OPEN_GALLERY);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        mViewModel.handlerResult(requestCode, resultCode, data, this);
    }

    public void updateProfile() {
        mViewModel.updateProfileShop();
    }
}
