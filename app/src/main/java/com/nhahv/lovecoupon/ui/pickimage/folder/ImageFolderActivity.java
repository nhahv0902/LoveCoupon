package com.nhahv.lovecoupon.ui.pickimage.folder;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;

import com.nhahv.lovecoupon.R;
import com.nhahv.lovecoupon.data.model.ImageFolder;
import com.nhahv.lovecoupon.data.model.ImagePickerItem;
import com.nhahv.lovecoupon.databinding.ActivityImageFolderBinding;
import com.nhahv.lovecoupon.ui.BaseActivity;
import com.nhahv.lovecoupon.ui.pickimage.image.ImagePickerActivity;

import java.util.List;

import static com.nhahv.lovecoupon.util.Constant.BundleConstant.BUNDLE_IMAGE;
import static com.nhahv.lovecoupon.util.Constant.RequestConstant.REQUEST_PICK_IMAGE;

public class ImageFolderActivity extends BaseActivity implements IImageFolder {
    private ActivityImageFolderBinding mBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_image_folder);
        mBinding.setViewModel(new ImageFolderViewModel(getApplicationContext(), this));
        start();
    }

    @Override
    protected void start() {
        setSupportActionBar(mBinding.toolbar);
        if (getSupportActionBar() != null) getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setTitle(R.string.title_albums);
    }

    @Override
    public void openImagePicker(ImageFolder folder) {
        startActivityForResult(
            ImagePickerActivity.getImagePickerIntent(this, folder.getListImage()),
            REQUEST_PICK_IMAGE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && requestCode == REQUEST_PICK_IMAGE) {
            Bundle bundle = data.getExtras();
            if (bundle == null) return;
            List<ImagePickerItem> imagePickerItems = bundle.getParcelableArrayList(BUNDLE_IMAGE);
            if (imagePickerItems == null) return;
            for (ImagePickerItem item : imagePickerItems) {
                // TODO: 3/9/2017 result intnet
            }
        }
    }
}
