package com.nhahv.lovecoupon.ui.pickimage.preview;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.nhahv.lovecoupon.R;
import com.nhahv.lovecoupon.data.model.ImagePickerItem;
import com.nhahv.lovecoupon.databinding.ActivityImagePreviewerBinding;

import java.util.ArrayList;
import java.util.List;

import static com.nhahv.lovecoupon.util.Constant.BundleConstant.BUNDLE_IMAGE;
import static com.nhahv.lovecoupon.util.Constant.BundleConstant.BUNDLE_POSITION;

public class ImagePreviewerActivity extends AppCompatActivity {
    private ActivityImagePreviewerBinding mBinding;
    private ImagePreviewerViewModel mViewModel;

    public static Intent getImagePreviewIntent(Context context, int position,
                                               List<ImagePickerItem> imagePickerItems) {
        Intent intent = new Intent(context, ImagePreviewerActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        Bundle bundle = new Bundle();
        bundle.putInt(BUNDLE_POSITION, position);
        bundle.putParcelableArrayList(BUNDLE_IMAGE, (ArrayList<ImagePickerItem>) imagePickerItems);
        intent.putExtras(bundle);
        return intent;
    }

    private void getDataFromIntent() {
        int position = 0;
        List<ImagePickerItem> imagePickerItems = new ArrayList<>();
        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            position = bundle.getInt(BUNDLE_POSITION);
            imagePickerItems = bundle.getParcelableArrayList(BUNDLE_IMAGE);
        }
        mViewModel.update(getSupportFragmentManager(), imagePickerItems, position);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_image_previewer);
        mViewModel = new ImagePreviewerViewModel(getApplicationContext());
        mBinding.setViewModel(mViewModel);
        getDataFromIntent();
    }
}
