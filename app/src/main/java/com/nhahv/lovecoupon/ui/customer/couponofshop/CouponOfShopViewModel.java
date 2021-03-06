package com.nhahv.lovecoupon.ui.customer.couponofshop;

import android.content.Context;
import android.databinding.BaseObservable;
import android.databinding.ObservableArrayList;
import android.databinding.ObservableField;
import android.databinding.ObservableList;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatDelegate;
import com.nhahv.lovecoupon.R;
import com.nhahv.lovecoupon.data.model.Coupon;
import com.nhahv.lovecoupon.data.model.CouponCustomer;
import com.nhahv.lovecoupon.data.source.Callback;
import com.nhahv.lovecoupon.data.source.remote.coupon.CouponRepository;
import com.nhahv.lovecoupon.util.ActivityUtil;

/**
 * Created by Nhahv0902 on 3/11/2017.
 * <></>
 */
public class CouponOfShopViewModel extends BaseObservable {
    static {
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true);
    }

    private final String TAG = getClass().getSimpleName();
    private final Context mContext;
    private final CouponCustomer mCouponCustomer;
    private final ObservableList<Coupon> mListCoupon = new ObservableArrayList<>();
    private final ObservableField<CouponOfShopAdapter> mAdapter = new ObservableField<>();
    private final CouponRepository mRepository;
    private final CouponOfShopHandler mHandler;

    public CouponOfShopViewModel(@NonNull Context context, @NonNull CouponOfShopHandler handler,
            @NonNull CouponCustomer couponCustomer) {
        mContext = context;
        mHandler = handler;
        mCouponCustomer = couponCustomer;
        mRepository = CouponRepository.getInstance();
        mAdapter.set(new CouponOfShopAdapter(this, mListCoupon, mCouponCustomer.getLogoLink(),
                mCouponCustomer.getName()));
        mListCoupon.addAll(mCouponCustomer.getListCoupon());
    }

    public void clickBackPress() {
        if (mHandler != null) mHandler.backPress();
    }

    public void clickUseCoupon(Coupon coupon, int position) {
        if (mRepository == null || !ActivityUtil.isNetworkConnected(mContext)) return;
        mHandler.showDialog(coupon, position);
    }

    public void deleteCoupon(Coupon coupon, int position) {
        mRepository.useCoupon(coupon, new Callback<Boolean>() {
            @Override
            public void onSuccess(Boolean data) {
                mListCoupon.remove(position);
                ActivityUtil.showMsg(mContext, R.string.msg_delete_success);
                mAdapter.get().notifyDataSetChanged();
            }

            @Override
            public void onError() {
                ActivityUtil.showMsg(mContext, R.string.msg_delete_error);
            }
        });
    }

    public CouponCustomer getCouponCustomer() {
        return mCouponCustomer;
    }

    public ObservableField<CouponOfShopAdapter> getAdapter() {
        return mAdapter;
    }
}
