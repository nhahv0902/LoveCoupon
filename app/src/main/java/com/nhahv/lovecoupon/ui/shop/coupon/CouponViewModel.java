package com.nhahv.lovecoupon.ui.shop.coupon;

import android.content.Context;
import android.databinding.ObservableArrayList;
import android.databinding.ObservableField;
import android.databinding.ObservableList;

import com.nhahv.lovecoupon.data.model.CouponItem;

/**
 * Created by Nhahv0902 on 3/6/2017.
 * <></>
 */
public class CouponViewModel {
    private Context mContext;
    private ICouponTemplate mICoupon;
    private ObservableList<CouponItem> mListCoupon = new ObservableArrayList<>();
    private ObservableField<CouponAdapter> mAdapter = new ObservableField<>();

    public CouponViewModel(Context context, ICouponTemplate iCoupon) {
        mContext = context;
        mICoupon = iCoupon;
        mAdapter.set(new CouponAdapter(mListCoupon, this));
        mListCoupon.add(new CouponItem());
        mListCoupon.add(new CouponItem());
        mListCoupon.add(new CouponItem());
        mListCoupon.add(new CouponItem());
    }

    public ObservableList<CouponItem> getListCoupon() {
        return mListCoupon;
    }

    public ObservableField<CouponAdapter> getAdapter() {
        return mAdapter;
    }
}
