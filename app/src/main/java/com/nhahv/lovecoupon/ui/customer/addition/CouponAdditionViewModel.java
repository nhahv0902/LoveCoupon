package com.nhahv.lovecoupon.ui.customer.addition;

import android.content.Context;
import android.databinding.BaseObservable;
import android.support.annotation.NonNull;
import com.nhahv.lovecoupon.data.model.Coupon;
import com.nhahv.lovecoupon.data.model.CouponCustomer;
import com.nhahv.lovecoupon.data.model.LCProfile;
import com.nhahv.lovecoupon.data.source.Callback;
import com.nhahv.lovecoupon.data.source.remote.coupon.CouponRepository;
import com.nhahv.lovecoupon.util.SharePreferenceUtil;

/**
 * Created by Nhahv0902 on 3/14/2017.
 * <></>
 */
public class CouponAdditionViewModel extends BaseObservable {
    private final String TAG = getClass().getCanonicalName();
    private final LCProfile mProfile;
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
        Coupon coupon = new Coupon();
        coupon.setCouponId(text);
        coupon.setUserId(mProfile.getId());
        coupon.setLogoLink(mProfile.getLogoLink());
        coupon.setUserName(mProfile.getName());
        coupon.setUserSocial(mProfile.getSocial());
        String city = SharePreferenceUtil.getInstance(mContext).getCity();
        mRepository.addCoupon(city, coupon, new Callback<CouponCustomer>() {
            @Override
            public void onSuccess(CouponCustomer data) {
                if (mHandler == null) return;
                mHandler.addCouponSuccess(data);
            }

            @Override
            public void onError() {
                mHandler.addCouponError();
            }
        });
    }
}
