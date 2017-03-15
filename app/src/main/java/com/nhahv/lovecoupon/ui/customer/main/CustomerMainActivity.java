package com.nhahv.lovecoupon.ui.customer.main;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;

import com.nhahv.lovecoupon.R;
import com.nhahv.lovecoupon.data.model.CouponCustomer;
import com.nhahv.lovecoupon.databinding.ActivityCustomerMainBinding;
import com.nhahv.lovecoupon.ui.BaseActivity;
import com.nhahv.lovecoupon.ui.customer.addition.CouponAdditionActivity;
import com.nhahv.lovecoupon.ui.customer.coupon.CouponFragment;
import com.nhahv.lovecoupon.ui.customer.couponofshop.CouponOfShopActivity;
import com.nhahv.lovecoupon.ui.customer.notification.NotificationFragment;
import com.nhahv.lovecoupon.ui.customer.notification.NotificationType;
import com.nhahv.lovecoupon.ui.firstscreen.FirstScreenActivity;
import com.nhahv.lovecoupon.ui.share.ShareFragment;
import com.nhahv.lovecoupon.util.ActivityUtil;

import org.parceler.Parcels;

import java.util.ArrayList;
import java.util.List;

import permissions.dispatcher.NeedsPermission;
import permissions.dispatcher.RuntimePermissions;

import static com.nhahv.lovecoupon.util.Constant.BundleConstant.BUNDLE_COUPON;
import static com.nhahv.lovecoupon.util.Constant.RequestConstant.REQUEST_ADDITION_COUPON;
import static com.nhahv.lovecoupon.util.Constant.RequestConstant.REQUEST_UI_COUPON_OF_SHOP;

@RuntimePermissions
public class CustomerMainActivity extends BaseActivity implements CustomerMainHandler {
    private ActivityCustomerMainBinding mBinding;
    private CustomerMainViewModel mViewModel;
    private final List<Fragment> mListFragment = new ArrayList<>();
    private String[] mTitles;

    public static Intent getCustomerMainIntent(Context context) {
        return new Intent(context, CustomerMainActivity.class);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_customer_main);
        mViewModel = new CustomerMainViewModel(this, this);
        mBinding.setViewModel(mViewModel);
        mBinding.setView(this);
        start();
    }

    @Override
    public void start() {
        mListFragment.add(CouponFragment.newInstance());
        mListFragment.add(NotificationFragment.newInstance(NotificationType.NOTIFICATION));
        mListFragment.add(NotificationFragment.newInstance(NotificationType.NOTIFICATION_OTHER));
        mListFragment.add(ShareFragment.newInstance());
        mTitles = getResources().getStringArray(R.array.activity_main_customer);
        setSupportActionBar(mBinding.toolbar);
        addFragment(0);
    }

    @Override
    public void onBackPressed() {
        if (mBinding.drawerLayout.isDrawerOpen(GravityCompat.START)) {
            mBinding.drawerLayout.closeDrawer(GravityCompat.START);
        } else super.onBackPressed();
    }

    @Override
    public void addFragment(int position) {
        ActivityUtil.addFragment(getSupportFragmentManager(),
            mListFragment.get(position),
            R.id.frame_layout);
        setTitle(mTitles[position]);
    }

    @Override
    public void startUiFirstScreen() {
        startActivity(FirstScreenActivity.getFirstIntent(this, 0));
        finish();
    }

    @Override
    public void startUiCouponAddition() {
        CustomerMainActivityPermissionsDispatcher.showCameraWithCheck(this);
    }

    @NeedsPermission(Manifest.permission.CAMERA)
    void showCamera() {
        if (!ActivityUtil.isNetworkConnected(this)) return;
        startActivityForResult(CouponAdditionActivity.getIntent(this), REQUEST_ADDITION_COUPON);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode != RESULT_OK) return;
        switch (requestCode) {
            case REQUEST_ADDITION_COUPON:
                if (data == null || data.getExtras() == null) return;
                CouponCustomer coupon =
                    Parcels.unwrap(data.getExtras().getParcelable(BUNDLE_COUPON));
                startActivityForResult(CouponOfShopActivity.getIntent(this, coupon),
                    REQUEST_UI_COUPON_OF_SHOP);
                break;
            case REQUEST_UI_COUPON_OF_SHOP:
                if (mListFragment.get(0) != null) {
                    ((CouponFragment) mListFragment.get(0)).loadData();
                }
                break;
            default:
                break;
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        CustomerMainActivityPermissionsDispatcher
            .onRequestPermissionsResult(this, requestCode, grantResults);
    }
}
