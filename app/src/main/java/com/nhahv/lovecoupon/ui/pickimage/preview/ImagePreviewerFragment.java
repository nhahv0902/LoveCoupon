package com.nhahv.lovecoupon.ui.pickimage.preview;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import com.nhahv.lovecoupon.databinding.FragmentImagePreviewerBinding;

import uk.co.senab.photoview.PhotoViewAttacher;

import static com.nhahv.lovecoupon.util.Constant.BundleConstant.BUNDLE_IMAGE;

/**
 * Created by Nhahv0902 on 3/9/2017.
 * <></>
 */
public class ImagePreviewerFragment extends Fragment {
    private FragmentImagePreviewerBinding mBinding;
    private PhotoViewAttacher mAttacher;

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
            mAttacher = new PhotoViewAttacher(mBinding.imagePreview);
            Glide.with(this)
                .load(path)
                .asBitmap()
                .thumbnail(0.5f)
                .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                .fitCenter()
                .into(new SimpleTarget<Bitmap>(480, 800) {
                    @Override
                    public void onResourceReady(Bitmap resource,
                                                GlideAnimation<? super Bitmap> glideAnimation) {
                        mBinding.imagePreview.setImageBitmap(resource);
                        mAttacher.update();
                    }
                });
        }
    }
}
