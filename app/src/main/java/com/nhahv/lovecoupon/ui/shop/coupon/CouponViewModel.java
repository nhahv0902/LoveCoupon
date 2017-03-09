package com.nhahv.lovecoupon.ui.shop.coupon;

import android.content.Context;
import android.databinding.BaseObservable;
import android.databinding.ObservableArrayList;
import android.databinding.ObservableBoolean;
import android.databinding.ObservableField;
import android.databinding.ObservableList;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.nhahv.lovecoupon.R;
import com.nhahv.lovecoupon.data.model.CouponTemplate;
import com.nhahv.lovecoupon.data.model.ProfileShop;
import com.nhahv.lovecoupon.data.source.Callback;
import com.nhahv.lovecoupon.data.source.remote.coupontemplate.CouponTemplateRepository;
import com.nhahv.lovecoupon.ui.ViewModel;
import com.nhahv.lovecoupon.ui.shop.couponcreation.CouponCreationActivity;
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
    private final ObservableField<ProfileShop> mProfile = new ObservableField<>();
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
                setRefresh(false);
            }

            @Override
            public void onError() {
                loadError();
                setRefresh(false);
            }
        });
    }

    public void clickGenerateCoupon(CouponTemplate template) {
        Log.d(TAG, "generate");
        if (!ActivityUtil.isNetworkConnected(mContext)) return;
        mContext.startActivity(CouponCreationActivity.getCouponCreationIntent(mContext, template));
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

    public ObservableField<ProfileShop> getProfile() {
        return mProfile;
    }

    @Override
    public ObservableField<RecyclerView.Adapter> getAdapter() {
        return mAdapter;
    }
}
