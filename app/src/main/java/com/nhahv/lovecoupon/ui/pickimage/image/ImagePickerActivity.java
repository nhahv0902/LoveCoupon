package com.nhahv.lovecoupon.ui.pickimage.image;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.nhahv.lovecoupon.R;
import com.nhahv.lovecoupon.databinding.ActivityImagePickerBinding;

public class ImagePickerActivity extends AppCompatActivity {
    private ActivityImagePickerBinding mBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_image_picker);
        mBinding.setViewModel(new ImagePickerViewModel(getApplicationContext()));
    }
}
