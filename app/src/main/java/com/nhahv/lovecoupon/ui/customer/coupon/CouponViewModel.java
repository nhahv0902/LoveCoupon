package com.nhahv.lovecoupon.ui.customer.coupon;

import android.content.Context;
import android.databinding.ObservableArrayList;
import android.databinding.ObservableField;
import android.databinding.ObservableList;

import com.nhahv.lovecoupon.data.model.CouponCustomer;

/**
 * Created by Nhahv0902 on 3/6/2017.
 * <></>
 */
public class CouponViewModel {
    private Context mContext;
    private ObservableList<CouponCustomer> mListCoupon = new ObservableArrayList<>();
    private ObservableField<CouponAdapter> mAdapter = new ObservableField<>();

    public CouponViewModel(Context context) {
        mContext = context;
        mAdapter.set(new CouponAdapter(mListCoupon, this));
        mListCoupon.add(new CouponCustomer());
        mListCoupon.add(new CouponCustomer());
        mListCoupon.add(new CouponCustomer());
        mListCoupon.add(new CouponCustomer());
    }

    public ObservableList<CouponCustomer> getListCoupon() {
        return mListCoupon;
    }

    public ObservableField<CouponAdapter> getAdapter() {
        return mAdapter;
    }
}
