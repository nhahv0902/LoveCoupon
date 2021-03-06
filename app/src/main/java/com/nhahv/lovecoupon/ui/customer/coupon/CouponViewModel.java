package com.nhahv.lovecoupon.ui.customer.coupon;

import android.content.Context;
import android.databinding.BaseObservable;
import android.databinding.ObservableArrayList;
import android.databinding.ObservableBoolean;
import android.databinding.ObservableField;
import android.databinding.ObservableList;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import com.nhahv.lovecoupon.R;
import com.nhahv.lovecoupon.data.model.CouponCustomer;
import com.nhahv.lovecoupon.data.model.LCProfile;
import com.nhahv.lovecoupon.data.source.Callback;
import com.nhahv.lovecoupon.data.source.remote.coupon.CouponRepository;
import com.nhahv.lovecoupon.ui.ViewModel;
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
    private final ObservableBoolean mRefresh = new ObservableBoolean(false);
    private final ObservableField<RecyclerView.Adapter> mAdapter = new ObservableField<>();
    private final CouponRepository mRepository;
    private final LCProfile mProfile;
    private final CouponHandler mHandler;

    public CouponViewModel(@NonNull Context context, @NonNull CouponHandler handler) {
        mContext = context;
        mHandler = handler;
        mRepository = CouponRepository.getInstance();
        mProfile = SharePreferenceUtil.getInstance(context).profileCustomer();
        mAdapter.set(new CouponAdapter(mListCoupon, this));
        loadData();
    }

    @Override
    public void loadData() {
        if (mRepository == null) return;
        mRepository.getCouponCustomer(mProfile.getId(), new Callback<List<CouponCustomer>>() {
            //        mRepository.getCouponCustomer("nhahv0902@gmail.com", new
            // Callback<List<CouponCustomer>>() {
            @Override
            public void onSuccess(List<CouponCustomer> data) {
                mListCoupon.clear();
                mListCoupon.addAll(data);
                mAdapter.get().notifyDataSetChanged();
                setRefresh(false);
            }

            @Override
            public void onError() {
                loadError();
            }
        });
    }

    public void clickDetail(CouponCustomer coupon) {
        mHandler.startUiCouponOfShop(coupon);
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
