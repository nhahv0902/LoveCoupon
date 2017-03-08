package com.nhahv.lovecoupon.ui.share;

import android.content.Context;
import android.content.Intent;
import android.databinding.BaseObservable;
import android.net.Uri;
import android.support.customtabs.CustomTabsIntent;
import android.support.v4.content.ContextCompat;

import com.nhahv.lovecoupon.R;
import com.nhahv.lovecoupon.util.ActivityUtil;

import static com.nhahv.lovecoupon.util.Constant.DataConstant.DATA_EMAIL_LOVE_COUPON;
import static com.nhahv.lovecoupon.util.Constant.DataConstant.DATA_WEB_SITE;

/**
 * Created by Nhahv0902 on 3/6/2017.
 * <></>
 */
public class ShareViewModel extends BaseObservable {
    private final Context mContext;
    private final IShareFragment mShareFragment;

    public ShareViewModel(Context context, IShareFragment shareFragment) {
        mContext = context;
        mShareFragment = shareFragment;
    }

    public void clickShare() {
        if (!ActivityUtil.isNetworkConnected(mContext)) return;
        if (mShareFragment != null) mShareFragment.shareFacebook();
    }

    public void clickSendEmail() {
        if (!ActivityUtil.isNetworkConnected(mContext)) return;
        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setType("text/plain");
        intent.putExtra(Intent.EXTRA_SUBJECT, "");
        intent.putExtra(Intent.EXTRA_TEXT, "");
        intent.setData(Uri.parse("mailto:" + DATA_EMAIL_LOVE_COUPON));
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        mContext.startActivity(Intent.createChooser(intent, getString(R.string.msg_send_email)));
    }

    public void clickToWeb() {
        if (!ActivityUtil.isNetworkConnected(mContext)) return;
        CustomTabsIntent.Builder intentBuilder = new CustomTabsIntent.Builder();
        intentBuilder.setToolbarColor(ContextCompat.getColor(mContext, R.color.colorPrimary))
            .setSecondaryToolbarColor(ContextCompat.getColor(mContext, R.color.colorPrimary))
            .build().launchUrl(mContext, Uri.parse(DATA_WEB_SITE));
    }

    private String getString(int res) {
        return mContext.getString(res);
    }
}
