package com.nhahv.lovecoupon.ui.pickimage.image;

import android.content.Context;
import android.databinding.BaseObservable;
import android.databinding.ObservableArrayList;
import android.databinding.ObservableField;
import android.databinding.ObservableList;

import com.nhahv.lovecoupon.data.model.ImagePickerItem;

import java.util.List;

public class ImagePickerViewModel extends BaseObservable {
    private final Context mContext;
    private final ObservableField<ImagePickerAdapter> mAdapter = new ObservableField<>();
    private final ObservableList<ImagePickerItem> mListImage = new ObservableArrayList<>();

    public ImagePickerViewModel(Context context, List<ImagePickerItem> imagePickerItems) {
        mContext = context;
        mAdapter.set(new ImagePickerAdapter(this, mListImage));
        mListImage.addAll(imagePickerItems);
    }

    public ObservableField<ImagePickerAdapter> getAdapter() {
        return mAdapter;
    }
}
