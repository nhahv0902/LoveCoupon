package com.nhahv.lovecoupon.ui.shop.main;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v7.app.AppCompatDelegate;

import com.nhahv.lovecoupon.R;
import com.nhahv.lovecoupon.data.model.Notification;
import com.nhahv.lovecoupon.databinding.ActivityShopMainBinding;
import com.nhahv.lovecoupon.ui.BaseActivity;
import com.nhahv.lovecoupon.ui.firstscreen.FirstScreenHandlerActivity;
import com.nhahv.lovecoupon.ui.share.ShareFragment;
import com.nhahv.lovecoupon.ui.shop.coupon.CouponFragment;
import com.nhahv.lovecoupon.ui.shop.history.HistoryFragment;
import com.nhahv.lovecoupon.ui.shop.notification.NotificationFragment;
import com.nhahv.lovecoupon.ui.shop.notificationcreation.NotificationCreationActivity;
import com.nhahv.lovecoupon.ui.shop.setting.SettingFragment;
import com.nhahv.lovecoupon.ui.shop.templatecreation.TemplateCreationActivity;
import com.nhahv.lovecoupon.util.ActivityUtil;

import java.util.ArrayList;
import java.util.List;

import static com.nhahv.lovecoupon.ui.shop.notificationcreation.ActionNotificationType.CREATE;
import static com.nhahv.lovecoupon.util.Constant.RequestConstant.REQUEST_NOTIFICATION;
import static com.nhahv.lovecoupon.util.Constant.RequestConstant.REQUEST_TEMPLATE;

public class ShopMainActivity extends BaseActivity implements IShopMainHandler {
    private ActivityShopMainBinding mBinding;
    private ShopMainViewModel mViewModel;
    private final List<Fragment> mListFragment = new ArrayList<>();
    private String[] mTitles;
    static {
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true);
    }
    public static Intent getShopMainIntent(Context context) {
        return new Intent(context, ShopMainActivity.class);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_shop_main);
        mViewModel = new ShopMainViewModel(getApplicationContext(), this);
        mBinding.setViewModel(mViewModel);
        mBinding.setActivity(this);
        start();
    }

    @Override
    public void start() {
        mListFragment.add(CouponFragment.newInstance());
        mListFragment.add(NotificationFragment.newInstance());
        mListFragment.add(SettingFragment.newInstance());
        mListFragment.add(HistoryFragment.newInstance());
        mListFragment.add(ShareFragment.newInstance());
        mTitles = getResources().getStringArray(R.array.activity_main_shop);
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
        mViewModel.updateShowImagePlus(position <= 2);
        mViewModel.updateIconDone(position == 2);
        setTitle(mTitles[position]);
        ActivityUtil.addFragment(getSupportFragmentManager(),
            mListFragment.get(position),
            R.id.frame_layout);
    }

    @Override
    public void createCouponTemplate() {
        startActivityForResult(TemplateCreationActivity.getTemplateIntent(this), REQUEST_TEMPLATE);
    }

    @Override
    public void createNotification() {
        startActivityForResult(
            NotificationCreationActivity.getNotificationIntent(this, new Notification(), CREATE),
            REQUEST_NOTIFICATION);
    }

    @Override
    public void startUiFirstScreen() {
        startActivity(FirstScreenHandlerActivity.getFirstIntent(this, 0));
    }

    @Override
    public void clickUpdateProfile() {
        Fragment fragment = getSupportFragmentManager().findFragmentById(R.id.frame_layout);
        if (fragment instanceof SettingFragment) {
            ((SettingFragment) fragment).updateProfile();
        }
    }
}
