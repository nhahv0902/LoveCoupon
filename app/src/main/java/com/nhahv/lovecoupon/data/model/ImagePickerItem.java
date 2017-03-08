package com.nhahv.lovecoupon.data.model;

import android.databinding.BaseObservable;
import android.databinding.Bindable;

import com.nhahv.lovecoupon.BR;

/**
 * Created by Nhahv0902 on 3/9/2017.
 * <></>
 */
public class ImagePickerItem extends BaseObservable {
    private String mPathImage;
    private boolean mChecked;

    public ImagePickerItem(String pathImage) {
        mPathImage = pathImage;
        mChecked = false;
    }

    @Bindable
    public String getPathImage() {
        return mPathImage;
    }

    public void setPathImage(String pathImage) {
        mPathImage = pathImage;
        notifyPropertyChanged(BR.pathImage);
    }

    @Bindable
    public boolean isChecked() {
        return mChecked;
    }

    public void setChecked(boolean checked) {
        mChecked = checked;
        notifyPropertyChanged(BR.checked);
    }
}
