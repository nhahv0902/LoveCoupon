package com.nhahv.lovecoupon.ui.pickimage.image;

import android.content.Context;
import android.databinding.BaseObservable;
import android.databinding.ObservableArrayList;
import android.databinding.ObservableField;
import android.databinding.ObservableList;

import com.nhahv.lovecoupon.data.model.ImagePickerItem;

import static com.nhahv.lovecoupon.util.Constant.DataConstant.URL_IMAGE;

public class ImagePickerViewModel extends BaseObservable {
    private final Context mContext;
    private final ObservableField<ImagePickerAdapter> mAdapter = new ObservableField<>();
    private final ObservableList<ImagePickerItem> mListImage = new ObservableArrayList<>();

    public ImagePickerViewModel(Context context) {
        mContext = context;
        mAdapter.set(new ImagePickerAdapter(this,mListImage));
        mListImage.add(new ImagePickerItem(URL_IMAGE));
        mListImage.add(new ImagePickerItem(URL_IMAGE));
        mListImage.add(new ImagePickerItem(URL_IMAGE));
        mListImage.add(new ImagePickerItem(URL_IMAGE));
        mListImage.add(new ImagePickerItem(URL_IMAGE));
        mListImage.add(new ImagePickerItem(URL_IMAGE));
        mListImage.add(new ImagePickerItem(URL_IMAGE));
    }

    public ObservableField<ImagePickerAdapter> getAdapter() {
        return mAdapter;
    }
}
