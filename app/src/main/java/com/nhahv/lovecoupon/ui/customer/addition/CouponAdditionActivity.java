package com.nhahv.lovecoupon.ui.customer.addition;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;

import com.afollestad.materialdialogs.MaterialDialog;
import com.google.zxing.Result;
import com.nhahv.lovecoupon.R;
import com.nhahv.lovecoupon.databinding.ActivityCouponAdditionBinding;
import com.nhahv.lovecoupon.ui.BaseActivity;

import me.dm7.barcodescanner.zxing.ZXingScannerView;

import static com.nhahv.lovecoupon.util.Constant.DataConstant.TIME_DELAY_ADD_COUPON;

public class CouponAdditionActivity extends BaseActivity
    implements ZXingScannerView.ResultHandler, CouponAdditionHandler {
    private ActivityCouponAdditionBinding mBinding;
    private CouponAdditionViewModel mViewModel;
    private ZXingScannerView mScannerView;
    private boolean mIsCamera;

    public static Intent getIntent(Context context) {
        return new Intent(context, CouponAdditionActivity.class);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_coupon_addition);
        mViewModel = new CouponAdditionViewModel(getApplicationContext(), this);
        start();
    }

    @Override
    protected void start() {
        mScannerView = new ZXingScannerView(this);
        mBinding.frameLayout.addView(mScannerView);
    }

    @Override
    protected void onResume() {
        super.onResume();
        mScannerView.setResultHandler(this);
        mScannerView.startCamera();
    }

    @Override
    protected void onStop() {
        super.onStop();
        mScannerView.stopCamera();
    }

    @Override
    public void handleResult(Result result) {
        if (!mIsCamera) {
            mIsCamera = true;
            mScannerView.stopCamera();
            mViewModel.addCoupon(result.getText());
        }
    }

    @Override
    public void addCouponSuccess() {
    }

    @Override
    public void addCouponError() {
        new MaterialDialog.Builder(this)
            .content(R.string.msg_add_coupon_error)
            .cancelListener(dialog -> resumeScannerView())
            .positiveText(R.string.agree)
            .onPositive((dialog, which) -> resumeScannerView())
            .show();
    }

    private void resumeScannerView() {
        new Handler().postDelayed(() -> {
            mIsCamera = false;
            mScannerView.setResultHandler(this);
            mScannerView.startCamera();
        }, TIME_DELAY_ADD_COUPON);
    }
}
