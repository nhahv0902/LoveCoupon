package com.nhahv.lovecoupon.ui.shop.main;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuItem;

import com.nhahv.lovecoupon.R;
import com.nhahv.lovecoupon.databinding.ActivityShopMainBinding;
import com.nhahv.lovecoupon.databinding.NavHeaderShopMainBinding;
import com.nhahv.lovecoupon.ui.shop.notification.NotificationFragment;
import com.nhahv.lovecoupon.util.ActivityUtil;

public class ShopMainActivity extends AppCompatActivity
    implements NavigationView.OnNavigationItemSelectedListener, IShopMain {
    private ActivityShopMainBinding mBinding;
    private ShopMainViewModel mViewModel;

    public static Intent getShopMainIntent(Context context) {
        return new Intent(context, ShopMainActivity.class);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_shop_main);
        mViewModel = new ShopMainViewModel(getApplicationContext(), this);
        mBinding.setViewModel(mViewModel);
        start();
    }

    private void start() {
        Toolbar toolbar = mBinding.toolbar;
        setSupportActionBar(toolbar);
        ActionBarDrawerToggle toggle =
            new ActionBarDrawerToggle(this, mBinding.drawerLayout, toolbar,
                R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        mBinding.drawerLayout.setDrawerListener(toggle);
        toggle.syncState();
        mBinding.navView.setNavigationItemSelectedListener(this);
        NavHeaderShopMainBinding bindHeader =
            NavHeaderShopMainBinding.inflate(LayoutInflater.from(mBinding.navView.getContext()));
        mBinding.navView.addHeaderView(bindHeader.getRoot());
        bindHeader
            .setImage("http://tophinhanhdep.net/wp-content/uploads/2015/12/anh-dep-mua-xuan-5.jpg");
        bindHeader.setName("Hoang Van Nha");
        bindHeader.executePendingBindings();
        addFragment(NotificationFragment.newInstance(), R.string.menu_notification);
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
                addFragment(NotificationFragment.newInstance(), R.string.menu_notification);
                break;
            case R.id.action_notification:
                addFragment(NotificationFragment.newInstance(), R.string.menu_notification);
                break;
            case R.id.action_settings:
                break;
            case R.id.action_history:
                break;
            case R.id.action_share:
                break;
            case R.id.action_exit:
                break;
            default:
                break;
        }
        mBinding.drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

    private void addFragment(Fragment fragment, int title) {
        ActivityUtil.addFragment(getSupportFragmentManager(), fragment, R.id.frame_layout);
        setTitle(title);
    }
}
