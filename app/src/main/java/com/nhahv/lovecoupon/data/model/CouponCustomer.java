package com.nhahv.lovecoupon.data.model;

import android.databinding.BaseObservable;
import android.databinding.Bindable;
import com.android.databinding.library.baseAdapters.BR;
import com.google.gson.annotations.SerializedName;
import java.util.ArrayList;
import java.util.List;
import org.parceler.Parcel;

/**
 * Created by Nhahv0902 on 3/7/2017.
 * <></>
 */
@Parcel
public class CouponCustomer extends BaseObservable {
    @SerializedName("company_id")
    public String mShopId;
    @SerializedName("logo_link")
    public String mLogoLink;
    @SerializedName("name")
    public String mName;
    @SerializedName("address")
    public String mAddress;
    @SerializedName("coupon")
    public List<Coupon> mListCoupon = new ArrayList<>();

    public CouponCustomer() {
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
    public String getLogoLink() {
        return mLogoLink;
    }

    public void setLogoLink(String logoLink) {
        mLogoLink = logoLink;
        notifyPropertyChanged(BR.logoLink);
    }

    @Bindable
    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
        notifyPropertyChanged(BR.name);
    }

    @Bindable
    public String getAddress() {
        return mAddress;
    }

    public void setAddress(String address) {
        mAddress = address;
        notifyPropertyChanged(BR.address);
    }

    @Bindable
    public List<Coupon> getListCoupon() {
        return mListCoupon;
    }

    public void setListCoupon(List<Coupon> listCoupon) {
        mListCoupon = listCoupon;
        notifyPropertyChanged(BR.listCoupon);
    }
}
