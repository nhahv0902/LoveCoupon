package com.nhahv.lovecoupon.data.model;

import android.databinding.BaseObservable;
import android.databinding.Bindable;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Nhahv0902 on 3/6/2017.
 * <></>
 */
public class CouponItem extends BaseObservable {
    @SerializedName("coupon_id")
    public String mCouponId;
    @SerializedName("user_id")
    public String mUserId;
    @SerializedName("coupon_template_id")
    public String mCouponTemplateId;
    @SerializedName("created_date")
    public long mCreateDate;
    @SerializedName("used_date")
    public long mUseDate;
    @SerializedName("company_id")
    public String mShopId;
    @SerializedName("value")
    public String mValue;
    @SerializedName("duration")
    public int mDuration;
    @SerializedName("user_name")
    public String mUserName;
    @SerializedName("user_social")
    public String mUserSocial;
    @SerializedName("user_image_link")
    public String mLogoLink;
    @SerializedName("content")
    public String mContent;

    public void setCouponId(String couponId) {
        mCouponId = couponId;
    }

    public void setUserId(String userId) {
        mUserId = userId;
    }

    public void setCouponTemplateId(String couponTemplateId) {
        mCouponTemplateId = couponTemplateId;
    }

    public void setCreateDate(long createDate) {
        mCreateDate = createDate;
    }

    public void setUseDate(long useDate) {
        mUseDate = useDate;
    }

    public void setShopId(String shopId) {
        mShopId = shopId;
    }

    public void setValue(String value) {
        mValue = value;
    }

    public void setDuration(int duration) {
        mDuration = duration;
    }

    public void setUserName(String userName) {
        mUserName = userName;
    }

    public void setUserSocial(String userSocial) {
        mUserSocial = userSocial;
    }

    public void setLogoLink(String logoLink) {
        mLogoLink = logoLink;
    }

    public void setContent(String content) {
        mContent = content;
    }

    @Bindable
    public String getCouponId() {
        return mCouponId;
    }

    @Bindable
    public String getUserId() {
        return mUserId;
    }

    @Bindable
    public String getCouponTemplateId() {
        return mCouponTemplateId;
    }

    @Bindable
    public long getCreateDate() {
        return mCreateDate;
    }

    @Bindable
    public long getUseDate() {
        return mUseDate;
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

    @Bindable
    public String getUserName() {
        return mUserName;
    }

    @Bindable
    public String getUserSocial() {
        return mUserSocial;
    }

    @Bindable
    public String getLogoLink() {
        return mLogoLink;
    }

    @Bindable
    public String getContent() {
        return mContent;
    }
}
