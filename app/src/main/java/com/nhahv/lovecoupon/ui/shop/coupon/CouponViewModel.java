package com.nhahv.lovecoupon.ui.shop.coupon;

import android.content.Context;
import android.databinding.BaseObservable;
import android.databinding.ObservableArrayList;
import android.databinding.ObservableBoolean;
import android.databinding.ObservableField;
import android.databinding.ObservableList;

import com.nhahv.lovecoupon.R;
import com.nhahv.lovecoupon.data.model.CouponTemplate;
import com.nhahv.lovecoupon.data.model.ProfileShop;
import com.nhahv.lovecoupon.data.source.Callback;
import com.nhahv.lovecoupon.data.source.remote.coupontemplate.CouponTemplateRepository;
import com.nhahv.lovecoupon.ui.ViewModel;
import com.nhahv.lovecoupon.util.ActivityUtil;

import java.util.List;

import static com.nhahv.lovecoupon.util.Constant.DataConstant.DATA_ID_SHOP;

/**
 * Created by Nhahv0902 on 3/6/2017.
 * <></>
 */
public class CouponViewModel extends BaseObservable implements ViewModel {
    private final String TAG = getClass().getSimpleName();
    private final Context mContext;
    private final ICouponTemplate mICoupon;
    private final ObservableList<CouponTemplate> mListCoupon = new ObservableArrayList<>();
    private final ObservableField<CouponAdapter> mAdapter = new ObservableField<>();
    private final ObservableField<ProfileShop> mProfile = new ObservableField<>();
    private ObservableBoolean mRefresh = new ObservableBoolean(false);
    private final CouponTemplateRepository mRepository;

    public CouponViewModel(Context context, ICouponTemplate iCoupon) {
        mContext = context;
        mICoupon = iCoupon;
        mRepository = CouponTemplateRepository.getInstance();
        mAdapter.set(new CouponAdapter(mListCoupon, this));
        loadData();
    }

    @Override
    public void loadData() {
        if (mRepository == null) return;
        mRepository.getCoupon(DATA_ID_SHOP, new Callback<List<CouponTemplate>>() {
            @Override
            public void onSuccess(List<CouponTemplate> data) {
                mListCoupon.clear();
                mListCoupon.addAll(data);
                mAdapter.get().notifyDataSetChanged();
                mRefresh.set(false);
            }

            @Override
            public void onError() {
                loadError();
                mRefresh.set(false);
            }
        });
    }

    @Override
    public void loadError() {
        ActivityUtil.showMsg(mContext, R.string.msg_load_data_error);
    }

    public ObservableBoolean getRefresh() {
        return mRefresh;
    }

    public ObservableField<ProfileShop> getProfile() {
        return mProfile;
    }

    public ObservableField<CouponAdapter> getAdapter() {
        return mAdapter;
    }
}
