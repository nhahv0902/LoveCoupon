package com.nhahv.lovecoupon.ui.pickimage.image;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.nhahv.lovecoupon.R;
import com.nhahv.lovecoupon.databinding.ActivityImagePickerBinding;

import static android.content.Intent.FLAG_ACTIVITY_NEW_TASK;

public class ImagePickerActivity extends AppCompatActivity {
    private ActivityImagePickerBinding mBinding;

    public static Intent getImagePickerIntent(Context context) {
        Intent intent = new Intent(context, ImagePickerActivity.class);
        intent.addFlags(FLAG_ACTIVITY_NEW_TASK);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_image_picker);
        mBinding.setViewModel(new ImagePickerViewModel(getApplicationContext()));
    }
}
