package com.nhahv.lovecoupon.data.model;

public class LocalImage {
    private String mFileName;
    private String mFolderName;
    private String mPathImage;
    private String mFolderPath;

    public LocalImage() {
    }

    public LocalImage(String name, String folder, String path, String folderPath) {
        mFileName = name;
        mFolderName = folder;
        mPathImage = path;
        mFolderPath = folderPath;
    }

    public String getFileName() {
        return mFileName;
    }

    public void setFileName(String fileName) {
        mFileName = fileName;
    }

    public String getFolderName() {
        return mFolderName;
    }

    public void setFolderName(String folderName) {
        mFolderName = folderName;
    }

    public String getPathImage() {
        return mPathImage;
    }

    public void setPathImage(String pathImage) {
        mPathImage = pathImage;
    }

    public String getFolderPath() {
        return mFolderPath;
    }

    public void setFolderPath(String folderPath) {
        mFolderPath = folderPath;
    }
}
