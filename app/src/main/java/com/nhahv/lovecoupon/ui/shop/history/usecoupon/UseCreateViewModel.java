package com.nhahv.lovecoupon.ui.shop.history.usecoupon;

import android.content.Context;
import android.content.Intent;
import android.databinding.BaseObservable;
import android.databinding.ObservableArrayList;
import android.databinding.ObservableBoolean;
import android.databinding.ObservableField;
import android.databinding.ObservableList;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;

import com.nhahv.lovecoupon.R;
import com.nhahv.lovecoupon.data.model.CouponItem;
import com.nhahv.lovecoupon.data.source.Callback;
import com.nhahv.lovecoupon.data.source.remote.coupon.CouponDataSource;
import com.nhahv.lovecoupon.data.source.remote.coupon.CouponRepository;
import com.nhahv.lovecoupon.ui.ViewModel;
import com.nhahv.lovecoupon.ui.shop.history.UseCreateType;
import com.nhahv.lovecoupon.util.ActivityUtil;

import java.util.List;

import static com.nhahv.lovecoupon.util.Constant.DataConstant.DATA_FACEBOOK;
import static com.nhahv.lovecoupon.util.Constant.DataConstant.DATA_GOOGLE;

/**
 * Created by Nhahv0902 on 3/7/2017.
 * <></>
 */
public class UseCreateViewModel extends BaseObservable implements ViewModel {
    private final String TOKEN = "1665217970367185&&VtAhb49qmGDFqQd";
    private final long TIME_ONE_DAY = 24 * 3600 * 100;
    private final String ID = "qqIHubd8BF3N3Cf";
    private final long UTC1 = 1488960000901L;
    private final long UTC2 = 1488960006901L;
    private final Context mContext;
    private final ObservableList<CouponItem> mListCoupon = new ObservableArrayList<>();
    private final ObservableBoolean mShowData = new ObservableBoolean(true);
    private final CouponDataSource mRepository;
    private final UseCreateType mType;

    public UseCreateViewModel(Context context, UseCreateType type) {
        mContext = context;
        mType = type;
        mRepository = CouponRepository.getInstance();
        mAdapter.set(new UseCreateAdapter(this, mListCoupon));
    }

    @Override
    public void loadData() {
        if (mRepository == null) return;
        switch (mType) {
            case CREATE:
                mRepository
                    .getCreateCoupon(TOKEN, ID, UTC1, UTC2, new Callback<List<CouponItem>>() {
                        @Override
                        public void onSuccess(List<CouponItem> data) {
                            mListCoupon.clear();
                            mListCoupon.addAll(data);
                            mAdapter.get().notifyDataSetChanged();
                            setRefresh(false);
                        }

                        @Override
                        public void onError() {
                        }
                    });
                break;
            case USE:
                mRepository.getUsedCoupon(TOKEN, ID, UTC1, UTC2, new Callback<List<CouponItem>>() {
                    @Override
                    public void onSuccess(List<CouponItem> data) {
                        mListCoupon.clear();
                        mListCoupon.addAll(data);
                        setRefresh(false);
                        mAdapter.get().notifyDataSetChanged();
                    }

                    @Override
                    public void onError() {
                    }
                });
                break;
            default:
                break;
        }
    }

    public void updateTime(long time) {
//        mUtc1 = time;
//        mUtc2 = (time + TIME_ONE_DAY);
        loadData();
    }

    public void clickViewUser(CouponItem coupon) {
        String link = coupon.getUserSocial().equals(DATA_FACEBOOK) ?
            "https://facebook" + "" + ".com/" + coupon.getUserId() :
            (coupon.getUserSocial().equals(DATA_GOOGLE) ?
                "https://plus.google.com/" + coupon.getUserId() : "");
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(link));
        mContext.startActivity(intent);
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

    public ObservableBoolean getShowData() {
        return mShowData;
    }

    @Override
    public ObservableField<RecyclerView.Adapter> getAdapter() {
        return mAdapter;
    }
}
