package com.nhahv.lovecoupon.ui.shop.templatecreation;

import android.content.Context;
import android.databinding.BaseObservable;
import android.databinding.ObservableField;
import android.support.annotation.NonNull;

import com.nhahv.lovecoupon.R;
import com.nhahv.lovecoupon.data.model.CouponTemplate;
import com.nhahv.lovecoupon.data.model.ShopProfile;
import com.nhahv.lovecoupon.data.source.Callback;
import com.nhahv.lovecoupon.data.source.remote.coupontemplate.CouponTemplateRepository;
import com.nhahv.lovecoupon.util.ActivityUtil;
import com.nhahv.lovecoupon.util.SharePreferenceUtil;

public class TemplateCreationViewModel extends BaseObservable {
    private final String TAG = getClass().getSimpleName();
    private final Context mContext;
    private final CouponTemplateRepository mRepository;
    private final ObservableField<CouponTemplate> mTemplate = new ObservableField<>();
    private final ITemplateCreationHandler mHandler;
    private final ShopProfile mProfile;

    public TemplateCreationViewModel(@NonNull Context context,
                                     @NonNull ITemplateCreationHandler handler) {
        mContext = context;
        mHandler = handler;
        mProfile = SharePreferenceUtil.getInstance(context).profileShop();
        mRepository = CouponTemplateRepository.getInstance();
        mTemplate.set(new CouponTemplate());
    }

    public void clickCreateTemplate() {
        if (mRepository == null || !ActivityUtil.isNetworkConnected(mContext) || mProfile == null) {
            return;
        }
        int duration = mHandler.getDuration();
        mTemplate.get().setDuration(duration);
        mTemplate.get().setShopId(mProfile.getShopId());
        mTemplate.get().setCouponTemplateId(ActivityUtil.randomTemplateId());
        new TemplateValidation(mTemplate.get()).validation(new TemplateValidation.Callback() {
            @Override
            public void onSuccess() {
                mRepository.createCouponTemplate(mProfile.getToken(), mTemplate.get(),
                    new Callback<Boolean>() {
                        @Override
                        public void onSuccess(Boolean data) {
                            mHandler.createTemplateSuccess();
                        }

                        @Override
                        public void onError() {
                            showMsg(R.string.msg_create_error);
                        }
                    });
            }

            @Override
            public void onError(TemplateValidation.Error error) {
                switch (error) {
                    case AMOUNT:
                        showMsg(R.string.msg_amount_error);
                        break;
                    case CONTENT:
                        showMsg(R.string.msg_content_error);
                        break;
                    default:
                        break;
                }
            }
        });
    }

    private void showMsg(int res) {
        ActivityUtil.showMsg(mContext, res);
    }

    public ObservableField<CouponTemplate> getTemplate() {
        return mTemplate;
    }
}
