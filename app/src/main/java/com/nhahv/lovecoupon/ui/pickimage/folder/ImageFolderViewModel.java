package com.nhahv.lovecoupon.ui.pickimage.folder;

import android.content.Context;
import android.databinding.BaseObservable;
import android.databinding.ObservableArrayList;
import android.databinding.ObservableField;
import android.databinding.ObservableList;

import com.nhahv.lovecoupon.data.model.ImageFolder;
import com.nhahv.lovecoupon.ui.pickimage.image.ImagePickerActivity;

/**
 * Created by Nhahv0902 on 3/9/2017.
 * <></>
 */
public class ImageFolderViewModel extends BaseObservable {
    private final Context mContext;
    private final ObservableField<ImageFolderAdapter> mAdapter = new ObservableField<>();
    private final ObservableList<ImageFolder> mListFolder = new ObservableArrayList<>();

    public ImageFolderViewModel(Context context) {
        mContext = context;
        mAdapter.set(new ImageFolderAdapter(this, mListFolder));
        mListFolder.add(new ImageFolder());
        mListFolder.add(new ImageFolder());
        mListFolder.add(new ImageFolder());
        mListFolder.add(new ImageFolder());
        mListFolder.add(new ImageFolder());
    }

    public ObservableField<ImageFolderAdapter> getAdapter() {
        return mAdapter;
    }

    public void clickStartImagePicker(ImageFolder folder) {
        mContext.startActivity(ImagePickerActivity.getImagePickerIntent(mContext));
    }
}
