package com.nhahv.lovecoupon.ui.customer.coupon;

import android.content.Context;
import android.databinding.BaseObservable;
import android.databinding.ObservableArrayList;
import android.databinding.ObservableBoolean;
import android.databinding.ObservableField;
import android.databinding.ObservableList;
import android.support.v7.widget.RecyclerView;

import com.nhahv.lovecoupon.R;
import com.nhahv.lovecoupon.data.model.CouponCustomer;
import com.nhahv.lovecoupon.data.source.Callback;
import com.nhahv.lovecoupon.data.source.remote.coupon.CouponRepository;
import com.nhahv.lovecoupon.ui.ViewModel;
import com.nhahv.lovecoupon.ui.customer.couponofshop.CouponOfShopActivity;
import com.nhahv.lovecoupon.util.ActivityUtil;
import com.nhahv.lovecoupon.util.SharePreferenceUtil;

import java.util.List;

/**
 * Created by Nhahv0902 on 3/6/2017.
 * <></>
 */
public class CouponViewModel extends BaseObservable implements ViewModel {
    private final Context mContext;
    private final ObservableList<CouponCustomer> mListCoupon = new ObservableArrayList<>();
    private final CouponRepository mRepository;
    private final SharePreferenceUtil mPreference;

    public CouponViewModel(Context context, CouponRepository repository) {
        mContext = context;
        mRepository = repository;
        mPreference = SharePreferenceUtil.getInstance(context);
        mAdapter.set(new CouponAdapter(mListCoupon, this));
        loadData();
    }

    @Override
    public void loadData() {
        if (mRepository == null) return;
        mRepository.getCouponCustomer("nhahv0902@gmail.com",
            new Callback<List<CouponCustomer>>() {
                @Override
                public void onSuccess(List<CouponCustomer> data) {
                    mListCoupon.clear();
                    mListCoupon.addAll(data);
                    mAdapter.get().notifyDataSetChanged();
                }

                @Override
                public void onError() {
                    loadError();
                }
            });
    }

    public void clickDetail(CouponCustomer coupon) {
        mContext.startActivity(CouponOfShopActivity.getIntent(mContext, coupon));
    }

    @Override
    public void loadError() {
        ActivityUtil.showMsg(mContext, R.string.msg_load_data_error);
    }

    @Override
    public ObservableBoolean getRefresh() {
        return mRefresh;
    }

    @Override
    public void setRefresh(boolean isRefresh) {
        mRefresh.set(isRefresh);
    }

    @Override
    public ObservableField<RecyclerView.Adapter> getAdapter() {
        return mAdapter;
    }
}
