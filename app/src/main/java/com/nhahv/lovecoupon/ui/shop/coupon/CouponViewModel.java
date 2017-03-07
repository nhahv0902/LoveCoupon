package com.nhahv.lovecoupon.ui.shop.coupon;

import android.content.Context;
import android.databinding.BaseObservable;
import android.databinding.ObservableArrayList;
import android.databinding.ObservableField;
import android.databinding.ObservableList;

import com.nhahv.lovecoupon.R;
import com.nhahv.lovecoupon.data.model.CouponTemplate;
import com.nhahv.lovecoupon.data.model.ProfileShop;
import com.nhahv.lovecoupon.data.source.Callback;
import com.nhahv.lovecoupon.data.source.remote.coupontemplate.CouponTemplateRepository;
import com.nhahv.lovecoupon.util.ActivityUtil;

import java.util.List;

/**
 * Created by Nhahv0902 on 3/6/2017.
 * <></>
 */
public class CouponViewModel extends BaseObservable {
    private Context mContext;
    private ICouponTemplate mICoupon;
    private ObservableList<CouponTemplate> mListCoupon = new ObservableArrayList<>();
    private ObservableField<CouponAdapter> mAdapter = new ObservableField<>();
    private ObservableField<ProfileShop> mProfile = new ObservableField<>();
    private CouponTemplateRepository mRepository;

    public CouponViewModel(Context context, ICouponTemplate iCoupon) {
        mContext = context;
        mICoupon = iCoupon;
        mRepository = CouponTemplateRepository.getInstance();
        mAdapter.set(new CouponAdapter(mListCoupon, this));
        start();
    }

    private void start() {
        mRepository.getCoupon("qqIHubd8BF3N3Cf", new Callback<List<CouponTemplate>>() {
            @Override
            public void onSuccess(List<CouponTemplate> data) {
                mListCoupon.clear();
                mListCoupon.addAll(data);
                mAdapter.get().update();
            }

            @Override
            public void onError() {
                loadError();
            }
        });
    }

    private void loadError() {
        ActivityUtil.showMsg(mContext, R.string.msg_load_data_error);
    }

    public ObservableField<ProfileShop> getProfile() {
        return mProfile;
    }

    public ObservableField<CouponAdapter> getAdapter() {
        return mAdapter;
    }
}
