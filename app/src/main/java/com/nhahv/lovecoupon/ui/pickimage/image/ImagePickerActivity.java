package com.nhahv.lovecoupon.ui.pickimage.image;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.MenuItem;

import com.nhahv.lovecoupon.R;
import com.nhahv.lovecoupon.data.model.ImagePickerItem;
import com.nhahv.lovecoupon.databinding.ActivityImagePickerBinding;
import com.nhahv.lovecoupon.ui.BaseActivity;

import java.util.ArrayList;
import java.util.List;

import static com.nhahv.lovecoupon.util.Constant.BundleConstant.BUNDLE_FOLDER_NAME;
import static com.nhahv.lovecoupon.util.Constant.BundleConstant.BUNDLE_IMAGE;

public class ImagePickerActivity extends BaseActivity implements IImagePicker {
    private ActivityImagePickerBinding mBinding;

    public static Intent getImagePickerIntent(@NonNull Context context,
                                              @NonNull List<ImagePickerItem> imagePicker,
                                              @NonNull String folderName) {
        Intent intent = new Intent(context, ImagePickerActivity.class);
        Bundle bundle = new Bundle();
        bundle.putParcelableArrayList(BUNDLE_IMAGE, (ArrayList<ImagePickerItem>) imagePicker);
        bundle.putString(BUNDLE_FOLDER_NAME, folderName);
        intent.putExtras(bundle);
        return intent;
    }

    private List<ImagePickerItem> getDataFromIntent() {
        Bundle bundle = getIntent().getExtras();
        if (bundle == null) return new ArrayList<>();
        setTitle(bundle.getString(BUNDLE_FOLDER_NAME));
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
        setSupportActionBar(mBinding.toolbar);
        if (getSupportActionBar() != null) getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) onBackPressed();
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void pickImageDone(List<ImagePickerItem> imagePickerItems) {
        Intent intent = getIntent();
        Bundle bundle = new Bundle();
        bundle.putParcelableArrayList(BUNDLE_IMAGE, (ArrayList<ImagePickerItem>) imagePickerItems);
        intent.putExtras(bundle);
        setResult(RESULT_OK, intent);
        finish();
    }
}
