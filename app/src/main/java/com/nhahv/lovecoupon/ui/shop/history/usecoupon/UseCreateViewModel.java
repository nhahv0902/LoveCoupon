package com.nhahv.lovecoupon.ui.shop.history.usecoupon;

import android.content.Context;
import android.databinding.BaseObservable;
import android.databinding.ObservableArrayList;
import android.databinding.ObservableBoolean;
import android.databinding.ObservableField;
import android.databinding.ObservableList;

import com.nhahv.lovecoupon.data.model.CouponItem;

/**
 * Created by Nhahv0902 on 3/7/2017.
 * <></>
 */
public class UseCreateViewModel extends BaseObservable {
    private Context mContext;
    private ObservableField<UseCreateAdapter> mAdapter = new ObservableField<>();
    private ObservableList<CouponItem> mListCoupon = new ObservableArrayList<>();
    private ObservableBoolean mShowData = new ObservableBoolean(true);

    public UseCreateViewModel(Context context) {
        mContext = context;
        mAdapter.set(new UseCreateAdapter(mListCoupon));
        mListCoupon.add(new CouponItem());
        mListCoupon.add(new CouponItem());
        mListCoupon.add(new CouponItem());
        mListCoupon.add(new CouponItem());
        mListCoupon.add(new CouponItem());
    }

    public ObservableBoolean getShowData() {
        return mShowData;
    }

    public ObservableField<UseCreateAdapter> getAdapter() {
        return mAdapter;
    }
}
