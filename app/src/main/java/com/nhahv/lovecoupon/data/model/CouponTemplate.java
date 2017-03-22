package com.nhahv.lovecoupon.data.model;

import android.databinding.BaseObservable;
import android.databinding.Bindable;
import com.google.gson.annotations.SerializedName;
import com.nhahv.lovecoupon.BR;
import java.io.Serializable;

/**
 * Created by Nhahv0902 on 3/8/2017.
 * <></>
 */
public class CouponTemplate extends BaseObservable implements Serializable {
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

    @Bindable
    public String getCouponTemplateId() {
        return mCouponTemplateId;
    }

    public void setCouponTemplateId(String couponTemplateId) {
        mCouponTemplateId = couponTemplateId;
        notifyPropertyChanged(BR.couponTemplateId);
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
    public String getCreateDate() {
        return mCreateDate;
    }

    public void setCreateDate(String createDate) {
        mCreateDate = createDate;
        notifyPropertyChanged(BR.createDate);
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
    public String getValue() {
        return mValue;
    }

    public void setValue(String value) {
        mValue = value;
        notifyPropertyChanged(BR.value);
    }

    @Bindable
    public int getDuration() {
        return mDuration;
    }

    public void setDuration(int duration) {
        mDuration = duration;
        notifyPropertyChanged(BR.duration);
    }
}
