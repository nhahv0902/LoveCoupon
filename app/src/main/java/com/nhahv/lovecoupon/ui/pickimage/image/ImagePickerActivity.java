package com.nhahv.lovecoupon.ui.pickimage.image;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.util.Log;

import com.nhahv.lovecoupon.R;
import com.nhahv.lovecoupon.data.model.ImagePickerItem;
import com.nhahv.lovecoupon.databinding.ActivityImagePickerBinding;
import com.nhahv.lovecoupon.ui.BaseActivity;

import java.util.ArrayList;
import java.util.List;

import static android.content.Intent.FLAG_ACTIVITY_NEW_TASK;
import static com.nhahv.lovecoupon.util.Constant.BundleConstant.BUNDLE_IMAGE;

public class ImagePickerActivity extends BaseActivity implements IImagePicker {
    private ActivityImagePickerBinding mBinding;

    public static Intent getImagePickerIntent(Context context, List<ImagePickerItem> imagePicker) {
        Intent intent = new Intent(context, ImagePickerActivity.class);
        Bundle bundle = new Bundle();
        bundle.putParcelableArrayList(BUNDLE_IMAGE, (ArrayList<ImagePickerItem>) imagePicker);
        intent.putExtras(bundle);
        return intent;
    }

    private List<ImagePickerItem> getDataFromIntent() {
        Bundle bundle = getIntent().getExtras();
        if (bundle == null) return new ArrayList<>();
        return bundle.getParcelableArrayList(BUNDLE_IMAGE);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_image_picker);
        mBinding.setViewModel(
            new ImagePickerViewModel(getApplicationContext(), getDataFromIntent(), this));
        start();
    }

    @Override
    protected void start() {
    }

    @Override
    public void pickImageDone(List<ImagePickerItem> imagePickerItems) {
        Log.d("TAG", "size picker = " + imagePickerItems.size() + "");
        Intent intent = getIntent();
        Bundle bundle = new Bundle();
        bundle.putParcelableArrayList(BUNDLE_IMAGE, (ArrayList<ImagePickerItem>) imagePickerItems);
        intent.putExtras(bundle);
        setResult(RESULT_OK, intent);
        finish();
    }
}
