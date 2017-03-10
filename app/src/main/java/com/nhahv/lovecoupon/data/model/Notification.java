package com.nhahv.lovecoupon.data.model;

import android.databinding.BaseObservable;
import android.databinding.Bindable;

import com.android.databinding.library.baseAdapters.BR;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by Nhahv0902 on 3/6/2017.
 * <.
 */
public class Notification extends BaseObservable implements Serializable {
    @SerializedName("message_id")
    public String mNotificationId;
    @SerializedName("content")
    public String mContent;
    @SerializedName("created_date")
    public long mDateCreate;
    @SerializedName("company_id")
    public String mShopId;
    @SerializedName("last_date")
    public long mLastDate;
    @SerializedName("title")
    public String mTitle;
    @SerializedName("link")
    public String mLink;
    @SerializedName("images_link")
    public String mLinkImage;

    public Notification() {
    }

    public Notification(String notificationId) {
        mNotificationId = notificationId;
    }

    @Bindable
    public String getNotificationId() {
        return mNotificationId;
    }

    public void setNotificationId(String notificationId) {
        mNotificationId = notificationId;
        notifyPropertyChanged(BR.notificationId);
    }

    @Bindable
    public String getContent() {
        return mContent;
    }

    public void setContent(String content) {
        mContent = content;
        notifyPropertyChanged(BR.content);
    }

    @Bindable
    public long getDateCreate() {
        return mDateCreate;
    }

    public void setDateCreate(long dateCreate) {
        mDateCreate = dateCreate;
        notifyPropertyChanged(BR.dateCreate);
    }

    @Bindable
    public String getShopId() {
        return mShopId;
    }

    public void setShopId(String shopId) {
        mShopId = shopId;
        notifyPropertyChanged(BR.shopId);
    }

    @Bindable
    public long getLastDate() {
        return mLastDate;
    }

    public void setLastDate(long lastDate) {
        mLastDate = lastDate;
        notifyPropertyChanged(BR.lastDate);
    }

    @Bindable
    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String title) {
        mTitle = title;
        notifyPropertyChanged(BR.title);
    }

    @Bindable
    public String getLink() {
        return mLink;
    }

    public void setLink(String link) {
        mLink = link;
        notifyPropertyChanged(BR.link);
    }

    @Bindable
    public String getLinkImage() {
        return mLinkImage;
    }

    public void setLinkImage(String linkImage) {
        mLinkImage = linkImage;
        notifyPropertyChanged(BR.linkImage);
    }
}
