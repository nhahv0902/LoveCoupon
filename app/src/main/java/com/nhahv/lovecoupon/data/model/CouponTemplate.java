package com.nhahv.lovecoupon.data.model;

import android.databinding.BaseObservable;
import android.databinding.Bindable;

import com.google.gson.annotations.SerializedName;
import com.nhahv.lovecoupon.BR;

/**
 * Created by Nhahv0902 on 3/8/2017.
 * <></>
 */
public class CouponTemplate extends BaseObservable {
    @SerializedName("coupon_template_id")
    public String mCouponTemplateId;
    @SerializedName("content")
    public String mContent = "";
    @SerializedName("created_date")
    public String mCreateDate;
    @SerializedName("company_id")
    public String mShopId;
    @SerializedName("value")
    public String mValue = "";
    @SerializedName("duration")
    public int mDuration;

    public void setCouponTemplateId(String couponTemplateId) {
        mCouponTemplateId = couponTemplateId;
        notifyPropertyChanged(BR.couponTemplateId);
    }

    public void setContent(String content) {
        mContent = content;
        notifyPropertyChanged(BR.content);
    }

    public void setCreateDate(String createDate) {
        mCreateDate = createDate;
        notifyPropertyChanged(BR.createDate);
    }

    public void setShopId(String shopId) {
        mShopId = shopId;
        notifyPropertyChanged(BR.shopId);
    }

    public void setValue(String value) {
        mValue = value;
        notifyPropertyChanged(BR.value);
    }

    public void setDuration(int duration) {
        mDuration = duration;
        notifyPropertyChanged(BR.duration);
    }

    @Bindable
    public String getCouponTemplateId() {
        return mCouponTemplateId;
    }

    @Bindable
    public String getContent() {
        return mContent;
    }

    @Bindable
    public String getCreateDate() {
        return mCreateDate;
    }

    @Bindable
    public String getShopId() {
        return mShopId;
    }

    @Bindable
    public String getValue() {
        return mValue;
    }

    @Bindable
    public int getDuration() {
        return mDuration;
    }
}
