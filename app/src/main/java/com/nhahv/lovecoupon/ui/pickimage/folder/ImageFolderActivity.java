package com.nhahv.lovecoupon.ui.pickimage.folder;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.nhahv.lovecoupon.R;
import com.nhahv.lovecoupon.databinding.ActivityImageFolderBinding;

public class ImageFolderActivity extends AppCompatActivity {
    private ActivityImageFolderBinding mBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_image_folder);
        mBinding.setViewModel(new ImageFolderViewModel(getApplicationContext()));
    }
}
