package com.nhahv.lovecoupon.data.model;

import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.os.Parcel;
import android.os.Parcelable;

import com.nhahv.lovecoupon.BR;

/**
 * Created by Nhahv0902 on 3/9/2017.
 * <></>
 */
public class ImagePickerItem extends BaseObservable implements Parcelable {
    private String mPathImage;
    private boolean mChecked;

    public ImagePickerItem(String pathImage) {
        mPathImage = pathImage;
        mChecked = false;
    }

    protected ImagePickerItem(Parcel in) {
        mPathImage = in.readString();
        mChecked = in.readByte() != 0;
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

    public static final Creator<ImagePickerItem> CREATOR = new Creator<ImagePickerItem>() {
        @Override
        public ImagePickerItem createFromParcel(Parcel in) {
            return new ImagePickerItem(in);
        }

        @Override
        public ImagePickerItem[] newArray(int size) {
            return new ImagePickerItem[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(mPathImage);
        dest.writeByte((byte) (mChecked ? 1 : 0));
    }
}
