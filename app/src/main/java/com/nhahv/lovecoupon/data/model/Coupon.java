package com.nhahv.lovecoupon.data.model;

import android.databinding.BaseObservable;
import android.databinding.Bindable;
import com.google.gson.annotations.SerializedName;
import org.parceler.Parcel;

/**
 * Created by Nhahv0902 on 3/6/2017.
 * <></>
 */
@Parcel
public class Coupon extends BaseObservable {
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

    public Coupon() {
    }

    public Coupon(String couponId, String userId, String couponTemplateId, long createDate,
            long useDate, String shopId, String value, int duration, String userName,
            String userSocial, String logoLink, String content) {
        mCouponId = couponId;
        mUserId = userId;
        mCouponTemplateId = couponTemplateId;
        mCreateDate = createDate;
        mUseDate = useDate;
        mShopId = shopId;
        mValue = value;
        mDuration = duration;
        mUserName = userName;
        mUserSocial = userSocial;
        mLogoLink = logoLink;
        mContent = content;
    }

    @Bindable
    public String getCouponId() {
        return mCouponId;
    }

    public void setCouponId(String couponId) {
        mCouponId = couponId;
    }

    @Bindable
    public String getUserId() {
        return mUserId;
    }

    public void setUserId(String userId) {
        mUserId = userId;
    }

    @Bindable
    public String getCouponTemplateId() {
        return mCouponTemplateId;
    }

    public void setCouponTemplateId(String couponTemplateId) {
        mCouponTemplateId = couponTemplateId;
    }

    @Bindable
    public long getCreateDate() {
        return mCreateDate;
    }

    public void setCreateDate(long createDate) {
        mCreateDate = createDate;
    }

    @Bindable
    public long getUseDate() {
        return mUseDate;
    }

    public void setUseDate(long useDate) {
        mUseDate = useDate;
    }

    @Bindable
    public String getShopId() {
        return mShopId;
    }

    public void setShopId(String shopId) {
        mShopId = shopId;
    }

    @Bindable
    public String getValue() {
        return mValue;
    }

    public void setValue(String value) {
        mValue = value;
    }

    @Bindable
    public int getDuration() {
        return mDuration;
    }

    public void setDuration(int duration) {
        mDuration = duration;
    }

    @Bindable
    public String getUserName() {
        return mUserName;
    }

    public void setUserName(String userName) {
        mUserName = userName;
    }

    @Bindable
    public String getUserSocial() {
        return mUserSocial;
    }

    public void setUserSocial(String userSocial) {
        mUserSocial = userSocial;
    }

    @Bindable
    public String getLogoLink() {
        return mLogoLink;
    }

    public void setLogoLink(String logoLink) {
        mLogoLink = logoLink;
    }

    @Bindable
    public String getContent() {
        return mContent;
    }

    public void setContent(String content) {
        mContent = content;
    }
}
