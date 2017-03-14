package com.nhahv.lovecoupon.ui.customer.addition;

import android.content.Context;
import android.databinding.BaseObservable;
import android.support.annotation.NonNull;
import android.util.Log;

import com.nhahv.lovecoupon.data.model.Coupon;
import com.nhahv.lovecoupon.data.model.CouponCustomer;
import com.nhahv.lovecoupon.data.model.CustomerProfile;
import com.nhahv.lovecoupon.data.source.Callback;
import com.nhahv.lovecoupon.data.source.remote.coupon.CouponRepository;
import com.nhahv.lovecoupon.util.SharePreferenceUtil;

import java.util.List;

/**
 * Created by Nhahv0902 on 3/14/2017.
 * <></>
 */
public class CouponAdditionViewModel extends BaseObservable {
    private final String TAG = getClass().getCanonicalName();
    private final CustomerProfile mProfile;
    private final Context mContext;
    private final CouponRepository mRepository;
    private final CouponAdditionHandler mHandler;

    public CouponAdditionViewModel(@NonNull Context context,
                                   @NonNull CouponAdditionHandler handler) {
        mContext = context;
        mHandler = handler;
        mProfile = SharePreferenceUtil.getInstance(context).profileCustomer();
        mRepository = CouponRepository.getInstance();
    }

    public void addCoupon(@NonNull String text) {
        if (mRepository == null) return;
        Log.d(TAG, "token = " + text);
        Coupon coupon = new Coupon();
        coupon.setCouponId(text);
        coupon.setUserId(mProfile.getId());
        coupon.setLogoLink(mProfile.getAvatar());
        coupon.setUserName(mProfile.getName());
        coupon.setUserSocial(mProfile.getSocial());
        String city = SharePreferenceUtil.getInstance(mContext).getCity();
        city = "HaNoi";
        mRepository.addCoupon(city, coupon, new Callback<List<CouponCustomer>>() {
            @Override
            public void onSuccess(List<CouponCustomer> data) {
                if (mHandler != null) mHandler.addCouponSuccess();
            }

            @Override
            public void onError() {
                if (mHandler != null) mHandler.addCouponError();
            }
        });
    }
}