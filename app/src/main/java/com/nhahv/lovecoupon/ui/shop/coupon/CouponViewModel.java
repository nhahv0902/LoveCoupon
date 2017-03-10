package com.nhahv.lovecoupon.ui.shop.coupon;

import android.content.Context;
import android.databinding.BaseObservable;
import android.databinding.ObservableArrayList;
import android.databinding.ObservableBoolean;
import android.databinding.ObservableField;
import android.databinding.ObservableList;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.afollestad.materialdialogs.MaterialDialog;
import com.nhahv.lovecoupon.R;
import com.nhahv.lovecoupon.data.model.CouponTemplate;
import com.nhahv.lovecoupon.data.model.ProfileShop;
import com.nhahv.lovecoupon.data.source.Callback;
import com.nhahv.lovecoupon.data.source.remote.coupontemplate.CouponTemplateRepository;
import com.nhahv.lovecoupon.ui.ViewModel;
import com.nhahv.lovecoupon.ui.shop.couponcreation.CouponCreationActivity;
import com.nhahv.lovecoupon.util.ActivityUtil;
import com.nhahv.lovecoupon.util.SharePreferenceUtil;

import java.util.List;

import static com.nhahv.lovecoupon.util.Constant.DataConstant.DATA_ID_SHOP;

/**
 * Created by Nhahv0902 on 3/6/2017.
 * <></>
 */
public class CouponViewModel extends BaseObservable implements ViewModel {
    private final String TAG = getClass().getSimpleName();
    private final Context mContext;
    private final ObservableList<CouponTemplate> mListCoupon = new ObservableArrayList<>();
    private final CouponTemplateRepository mRepository;
    private final ProfileShop mProfile;
    private PopupMenu mPopupMenu;

    public CouponViewModel(@NonNull Context context) {
        mContext = context;
        mProfile = SharePreferenceUtil.getInstance(context).profileShop();
        mRepository = CouponTemplateRepository.getInstance();
        mAdapter.set(new CouponAdapter(mListCoupon, this, mProfile));
        loadData();
    }

    @Override
    public void loadData() {
        if (mRepository == null) return;
        mRepository.getCouponTemplate(DATA_ID_SHOP, new Callback<List<CouponTemplate>>() {
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
        if (!ActivityUtil.isNetworkConnected(mContext)) return;
        mContext.startActivity(CouponCreationActivity.getCouponCreationIntent(mContext, template));
    }

    public void clickMoreTemplate(View view, CouponTemplate template) {
        if (!ActivityUtil.isNetworkConnected(mContext)) return;
        mPopupMenu = new PopupMenu(mContext, view);
        mPopupMenu.getMenuInflater().inflate(R.menu.template_more, mPopupMenu.getMenu());
        mPopupMenu.setOnMenuItemClickListener(item -> {
            switch (item.getItemId()) {
                case R.id.action_generate_coupon:
                    clickGenerateCoupon(template);
                    break;
                case R.id.action_delete_notification:
                    new MaterialDialog
                        .Builder(mContext)
                        .icon(ContextCompat.getDrawable(mContext, R.drawable.ic_delete_grey_24dp))
                        .title(R.string.title_delete_notification)
                        .positiveText(R.string.agree)
                        .positiveColor(ContextCompat.getColor(mContext, R.color.color_blue_600))
                        .onPositive((dialog, which) -> {
                            dialog.dismiss();
                            if (mRepository == null) return;
                            mRepository.deleteCouponTemplate(mProfile.getToken(), template,
                                new Callback<Boolean>() {
                                    @Override
                                    public void onSuccess(Boolean data) {
                                        loadData();
                                        ActivityUtil.showMsg(mContext, R.string.msg_delete_success);
                                    }

                                    @Override
                                    public void onError() {
                                        ActivityUtil.showMsg(mContext, R.string.msg_delete_error);
                                    }
                                });
                        })
                        .show();
                    break;
                default:
                    break;
            }
            return false;
        });
        mPopupMenu.show();
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
