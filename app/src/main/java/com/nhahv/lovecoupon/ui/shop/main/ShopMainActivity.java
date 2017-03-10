package com.nhahv.lovecoupon.ui.shop.main;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.databinding.ObservableInt;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.AppCompatDelegate;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuItem;

import com.facebook.login.LoginManager;
import com.nhahv.lovecoupon.R;
import com.nhahv.lovecoupon.data.model.Notification;
import com.nhahv.lovecoupon.data.model.ProfileShop;
import com.nhahv.lovecoupon.databinding.ActivityShopMainBinding;
import com.nhahv.lovecoupon.databinding.NavHeaderShopMainBinding;
import com.nhahv.lovecoupon.ui.firstscreen.FirstScreenActivity;
import com.nhahv.lovecoupon.ui.share.ShareFragment;
import com.nhahv.lovecoupon.ui.shop.coupon.CouponFragment;
import com.nhahv.lovecoupon.ui.shop.history.HistoryFragment;
import com.nhahv.lovecoupon.ui.shop.notification.NotificationFragment;
import com.nhahv.lovecoupon.ui.shop.notificationcreation.ActionNotificationType;
import com.nhahv.lovecoupon.ui.shop.notificationcreation.NotificationCreationActivity;
import com.nhahv.lovecoupon.ui.shop.setting.SettingFragment;
import com.nhahv.lovecoupon.ui.shop.templatecreation.TemplateCreationActivity;
import com.nhahv.lovecoupon.util.ActivityUtil;
import com.nhahv.lovecoupon.util.SharePreferenceUtil;

import java.util.ArrayList;
import java.util.List;

import static com.nhahv.lovecoupon.util.Constant.PreferenceConstant.PREF_IS_LOGIN;
import static com.nhahv.lovecoupon.util.Constant.RequestConstant.REQUEST_NOTIFICATION;
import static com.nhahv.lovecoupon.util.Constant.RequestConstant.REQUEST_TEMPLATE;

public class ShopMainActivity extends AppCompatActivity
    implements NavigationView.OnNavigationItemSelectedListener, IShopMainHandler {
    private ActivityShopMainBinding mBinding;
    private ShopMainViewModel mViewModel;
    private SharePreferenceUtil mPreference;
    private final List<Fragment> mListFragment = new ArrayList<>();
    private String[] mTitles;
    private ObservableInt mPositionFragment = new ObservableInt();
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
        mViewModel = new ShopMainViewModel(getApplicationContext(), mPositionFragment, this);
        mBinding.setViewModel(mViewModel);
        mPreference = SharePreferenceUtil.getInstance(this);
        initFragment();
        start();
    }

    private void initFragment() {
        mListFragment.add(CouponFragment.newInstance());
        mListFragment.add(NotificationFragment.newInstance());
        mListFragment.add(SettingFragment.newInstance());
        mListFragment.add(HistoryFragment.newInstance());
        mListFragment.add(ShareFragment.newInstance());
        mTitles = getResources().getStringArray(R.array.activity_main_shop);
    }

    private void start() {
        Toolbar toolbar = mBinding.toolbar;
        setSupportActionBar(toolbar);
        ActionBarDrawerToggle toggle =
            new ActionBarDrawerToggle(this, mBinding.drawerLayout, toolbar,
                R.string.action_open, R.string.action_close);
        mBinding.drawerLayout.setDrawerListener(toggle);
        toggle.syncState();
        ProfileShop profile = mPreference.profileShop();
        mBinding.navView.setNavigationItemSelectedListener(this);
        NavHeaderShopMainBinding bindHeader =
            NavHeaderShopMainBinding.inflate(LayoutInflater.from(mBinding.navView.getContext()));
        bindHeader.setImage(profile.getLogoLink());
        bindHeader.setName(profile.getName());
        bindHeader.executePendingBindings();
        mBinding.navView.addHeaderView(bindHeader.getRoot());
        startFragment(mPositionFragment.get());
    }

    @Override
    public void onBackPressed() {
        if (mBinding.drawerLayout.isDrawerOpen(GravityCompat.START)) {
            mBinding.drawerLayout.closeDrawer(GravityCompat.START);
        } else super.onBackPressed();
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_coupon:
                mPositionFragment.set(0);
                break;
            case R.id.action_notification:
                mPositionFragment.set(1);
                break;
            case R.id.action_settings:
                mPositionFragment.set(2);
                break;
            case R.id.action_history:
                mPositionFragment.set(3);
                break;
            case R.id.action_share:
                mPositionFragment.set(4);
                break;
            case R.id.action_exit:
                LoginManager.getInstance().logOut();
                mPreference.writePreference(PREF_IS_LOGIN, false);
                startActivity(FirstScreenActivity.getFirstIntent(this, 0));
                break;
            default:
                break;
        }
        startFragment(mPositionFragment.get());
        mBinding.drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

    private void startFragment(int position) {
        mViewModel.updateShowImagePlus(mPositionFragment.get() <= 2);
        mViewModel.updateIconDone(mPositionFragment.get() == 2);
        setTitle(mTitles[position]);
        ActivityUtil.addFragment(getSupportFragmentManager(),
            mListFragment.get(position),
            R.id.frame_layout);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode != RESULT_OK) return;
        Fragment fragment = getSupportFragmentManager().findFragmentById(R.id.frame_layout);
        switch (requestCode) {
            case REQUEST_TEMPLATE:
                if (fragment != null && fragment instanceof CouponFragment) {
                    ((CouponFragment) fragment).loadData();
                }
                break;
            case REQUEST_NOTIFICATION:
                if (fragment != null && fragment instanceof NotificationFragment) {
                    ((NotificationFragment) fragment).loadData();
                }
                break;
            default:
                break;
        }
    }

    @Override
    public void createCouponTemplate() {
        startActivityForResult(TemplateCreationActivity.getTemplateIntent(this), REQUEST_TEMPLATE);
    }

    @Override
    public void createNotification() {
        startActivityForResult(NotificationCreationActivity
                .getNotificationIntent(this, new Notification(), ActionNotificationType.CREATE),
            REQUEST_NOTIFICATION);
    }

    @Override
    public void clickUpdateProfile() {
        Fragment fragment = getSupportFragmentManager().findFragmentById(R.id.frame_layout);
        if (fragment instanceof SettingFragment) {
            ((SettingFragment) fragment).updateProfile();
        }
    }
}
