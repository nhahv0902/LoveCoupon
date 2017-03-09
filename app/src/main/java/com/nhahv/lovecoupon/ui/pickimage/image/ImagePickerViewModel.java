package com.nhahv.lovecoupon.ui.pickimage.image;

import android.content.Context;
import android.databinding.BaseObservable;
import android.databinding.ObservableArrayList;
import android.databinding.ObservableField;
import android.databinding.ObservableInt;
import android.databinding.ObservableList;

import com.nhahv.lovecoupon.data.model.ImagePickerItem;
import com.nhahv.lovecoupon.ui.pickimage.preview.ImagePreviewerActivity;

import java.util.ArrayList;
import java.util.List;

public class ImagePickerViewModel extends BaseObservable {
    private final String TAG = getClass().getSimpleName();
    private final Context mContext;
    private final IImagePicker mImagePicker;
    private final ObservableField<ImagePickerAdapter> mAdapter = new ObservableField<>();
    private final ObservableList<ImagePickerItem> mListImage = new ObservableArrayList<>();
    private final ObservableInt mNumberImage = new ObservableInt();

    public ImagePickerViewModel(Context context, List<ImagePickerItem> imagePickerItems,
                                IImagePicker imagePicker) {
        mContext = context;
        mImagePicker = imagePicker;
        mAdapter.set(new ImagePickerAdapter(this, mListImage));
        mListImage.addAll(imagePickerItems);
    }

    public void clickPreviewImage(int position) {
        mContext.startActivity(
            ImagePreviewerActivity.getImagePreviewIntent(mContext, position, mListImage));
    }

    public void updateNumberImage() {
        int numberImage = 0;
        for (ImagePickerItem item : mListImage) {
            if (item.isChecked()) numberImage++;
        }
        mNumberImage.set(numberImage);
    }

    public void clickPickerDone() {
        List<ImagePickerItem> imagePickerItems = new ArrayList<>();
        for (ImagePickerItem item : mListImage) {
            if (item.isChecked()) imagePickerItems.add(item);
        }
        mImagePicker.pickImageDone(imagePickerItems);
    }

    public ObservableField<ImagePickerAdapter> getAdapter() {
        return mAdapter;
    }

    public ObservableInt getNumberImage() {
        return mNumberImage;
    }
}
