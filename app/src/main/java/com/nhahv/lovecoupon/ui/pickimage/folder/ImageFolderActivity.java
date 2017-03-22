package com.nhahv.lovecoupon.ui.pickimage.folder;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import com.nhahv.lovecoupon.R;
import com.nhahv.lovecoupon.data.model.ImageFolder;
import com.nhahv.lovecoupon.databinding.ActivityImageFolderBinding;
import com.nhahv.lovecoupon.ui.BaseActivity;
import com.nhahv.lovecoupon.ui.pickimage.image.ImagePickerActivity;

import static com.nhahv.lovecoupon.util.Constant.RequestConstant.REQUEST_PICK_IMAGE;

public class ImageFolderActivity extends BaseActivity implements IImageFolder {
    private ActivityImageFolderBinding mBinding;

    public static Intent getIntent(Context context) {
        return new Intent(context, ImageFolderActivity.class);
    }

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
        startActivityForResult(ImagePickerActivity.getImagePickerIntent(this, folder.getListImage(),
                folder.getFolderName()), REQUEST_PICK_IMAGE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && requestCode == REQUEST_PICK_IMAGE) {
            setResult(RESULT_OK, data);
            finish();
        }
    }
}
