package com.nhahv.lovecoupon.ui.shop.history.usecoupon;

import android.content.Context;
import android.databinding.BaseObservable;
import android.databinding.ObservableArrayList;
import android.databinding.ObservableBoolean;
import android.databinding.ObservableField;
import android.databinding.ObservableList;

import com.nhahv.lovecoupon.R;
import com.nhahv.lovecoupon.data.model.CouponItem;
import com.nhahv.lovecoupon.data.source.Callback;
import com.nhahv.lovecoupon.data.source.remote.coupon.CouponDataSource;
import com.nhahv.lovecoupon.data.source.remote.coupon.CouponRepository;
import com.nhahv.lovecoupon.ui.ViewModel;
import com.nhahv.lovecoupon.ui.shop.history.UseCreateType;
import com.nhahv.lovecoupon.util.ActivityUtil;

import java.util.List;

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
    private final ObservableField<UseCreateAdapter> mAdapter = new ObservableField<>();
    private final ObservableList<CouponItem> mListCoupon = new ObservableArrayList<>();
    private final ObservableBoolean mShowData = new ObservableBoolean(true);
    private ObservableBoolean mRefresh = new ObservableBoolean(false);
    private final CouponDataSource mRepository;
    private final UseCreateType mType;

    public UseCreateViewModel(Context context, UseCreateType type) {
        mContext = context;
        mType = type;
        mRepository = CouponRepository.getInstance();
        mAdapter.set(new UseCreateAdapter(mListCoupon));
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
                            loadFinish();
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
                        loadFinish();
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

    private void loadFinish() {
        mRefresh.set(false);
    }

    @Override
    public void loadError() {
        ActivityUtil.showMsg(mContext, R.string.msg_load_data_error);
    }

    public ObservableBoolean getShowData() {
        return mShowData;
    }

    public ObservableField<UseCreateAdapter> getAdapter() {
        return mAdapter;
    }
}
