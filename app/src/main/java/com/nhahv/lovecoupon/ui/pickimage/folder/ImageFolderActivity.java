package com.nhahv.lovecoupon.ui.pickimage.folder;

import android.databinding.DataBindingUtil;
import android.os.Bundle;

import com.nhahv.lovecoupon.R;
import com.nhahv.lovecoupon.databinding.ActivityImageFolderBinding;
import com.nhahv.lovecoupon.ui.BaseActivity;

public class ImageFolderActivity extends BaseActivity {
    private ActivityImageFolderBinding mBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_image_folder);
        mBinding.setViewModel(new ImageFolderViewModel(getApplicationContext()));
        start();
    }

    @Override
    protected void start() {
        setSupportActionBar(mBinding.toolbar);
        if (getSupportActionBar() != null) getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setTitle(R.string.title_albums);
    }
}
