package com.nhahv.lovecoupon.ui.pickimage.preview;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.nhahv.lovecoupon.databinding.FragmentImagePreviewerBinding;

import static com.nhahv.lovecoupon.util.Constant.BundleConstant.BUNDLE_IMAGE;

/**
 * Created by Nhahv0902 on 3/9/2017.
 * <></>
 */
public class ImagePreviewerFragment extends Fragment {
    private FragmentImagePreviewerBinding mBinding;

    public static ImagePreviewerFragment getInstance(String urlImage) {
        ImagePreviewerFragment fragment = new ImagePreviewerFragment();
        Bundle bundle = new Bundle();
        bundle.putString(BUNDLE_IMAGE, urlImage);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        mBinding = FragmentImagePreviewerBinding.inflate(inflater, container, false);
        start();
        return mBinding.getRoot();
    }

    private void start() {
        if (getArguments() != null) {
            String path = getArguments().getString(BUNDLE_IMAGE);
            mBinding.setIcon(path);
            mBinding.executePendingBindings();
        }
    }
}
