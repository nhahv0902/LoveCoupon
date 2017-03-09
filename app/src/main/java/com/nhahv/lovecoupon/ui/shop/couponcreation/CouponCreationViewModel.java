package com.nhahv.lovecoupon.ui.shop.couponcreation;

import android.content.Context;
import android.databinding.BaseObservable;
import android.databinding.ObservableField;
import android.support.annotation.NonNull;

import com.nhahv.lovecoupon.R;
import com.nhahv.lovecoupon.data.model.CouponItem;
import com.nhahv.lovecoupon.data.model.CouponTemplate;
import com.nhahv.lovecoupon.data.model.ProfileShop;
import com.nhahv.lovecoupon.data.source.Callback;
import com.nhahv.lovecoupon.data.source.remote.coupontemplate.CouponTemplateRepository;
import com.nhahv.lovecoupon.util.ActivityUtil;
import com.nhahv.lovecoupon.util.SharePreferenceUtil;

/**
 * Created by Nhahv0902 on 3/10/2017.
 * <></>
 */
public class CouponCreationViewModel extends BaseObservable {
    private final Context mContext;
    private final CouponTemplate mCouponTemplate;
    private final ProfileShop mProfile;
    private final CouponTemplateRepository mRepository;
    private final ObservableField<String> mCouponId = new ObservableField<>();

    public CouponCreationViewModel(@NonNull Context context, @NonNull CouponTemplate template) {
        mContext = context;
        mCouponTemplate = template;
        mRepository = CouponTemplateRepository.getInstance();
        mProfile = SharePreferenceUtil.getInstance(context).profileShop();
        mCouponId.set(ActivityUtil.randomId());
        generateCoupon();
    }

    private void generateCoupon() {
        if (mRepository == null || !ActivityUtil.isNetworkConnected(mContext)) return;
        CouponItem coupon = new CouponItem();
        coupon.setShopId(mProfile.getShopId());
        String couponId = ActivityUtil.randomId();
        coupon.setCouponId(couponId);
        coupon.setValue(mCouponTemplate.getValue());
        coupon.setCouponTemplateId(mCouponTemplate.getCouponTemplateId());
        coupon.setDuration(mCouponTemplate.getDuration());
        coupon.setContent(mCouponTemplate.getContent());
        mRepository.generateCoupon(mProfile.getToken(), coupon, new Callback<Boolean>() {
            @Override
            public void onSuccess(Boolean data) {
                mCouponId.set(couponId);
            }

            @Override
            public void onError() {
                loadError();
            }
        });
    }

    public void clickOtherCode() {
        generateCoupon();
    }

    private void loadError() {
        ActivityUtil.showMsg(mContext, R.string.action_create_coupon_error);
    }

    public ObservableField<String> getCouponId() {
        return mCouponId;
    }
}
