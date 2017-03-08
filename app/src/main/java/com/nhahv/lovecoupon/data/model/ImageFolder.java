package com.nhahv.lovecoupon.data.model;

import android.databinding.BaseObservable;
import android.databinding.Bindable;

import com.nhahv.lovecoupon.BR;

import java.util.ArrayList;
import java.util.List;

public class ImageFolder extends BaseObservable {
    private String mFolderName;
    private String mFolderPath;
    private List<ImagePickerItem> mListImage = new ArrayList<>();

    public ImageFolder() {
    }

    public ImageFolder(String folderName, String folderPath) {
        mFolderName = folderName;
        mFolderPath = folderPath;
    }

    public ImageFolder(String folderName, List<ImagePickerItem> images) {
        mFolderName = folderName;
        mListImage.addAll(images);
    }

    @Bindable
    public String getFolderName() {
        return mFolderName;
    }

    public void setFolderName(String folderName) {
        mFolderName = folderName;
        notifyPropertyChanged(BR.folderName);
    }

    @Bindable
    public String getFolderPath() {
        return mFolderPath;
    }

    public void setFolderPath(String folderPath) {
        mFolderPath = folderPath;
        notifyPropertyChanged(BR.folderPath);
    }

    @Bindable
    public List<ImagePickerItem> getListImage() {
        return mListImage;
    }

    public void setListImage(List<ImagePickerItem> listImage) {
        mListImage = listImage;
        notifyPropertyChanged(BR.listImage);
    }
}
