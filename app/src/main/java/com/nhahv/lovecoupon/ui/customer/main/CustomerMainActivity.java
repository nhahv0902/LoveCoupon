package com.nhahv.lovecoupon.ui.customer.main;

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
import com.nhahv.lovecoupon.databinding.ActivityCustomerMainBinding;
import com.nhahv.lovecoupon.databinding.NavHeaderShopMainBinding;
import com.nhahv.lovecoupon.ui.customer.coupon.CouponFragment;
import com.nhahv.lovecoupon.ui.customer.notification.NotificationFragment;
import com.nhahv.lovecoupon.ui.share.ShareFragment;
import com.nhahv.lovecoupon.util.ActivityUtil;

public class CustomerMainActivity extends AppCompatActivity
    implements NavigationView.OnNavigationItemSelectedListener {
    private static final String URL_IMAGE =
        "http://tophinhanhdep.net/wp-content/uploads/2015/12/anh-dep-mua-xuan-5.jpg";
    private ActivityCustomerMainBinding mBinding;

    public static Intent getCustomerMainIntent(Context context) {
        return new Intent(context, CustomerMainActivity.class);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_customer_main);
        mBinding.setViewModel(new CustomerMainViewModel(getApplicationContext()));
        start();
    }

    private void start() {
        Toolbar toolbar = mBinding.toolbar;
        setSupportActionBar(toolbar);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this,
            mBinding.drawerLayout,
            toolbar,
            R.string.action_open,
            R.string.action_close);
        mBinding.drawerLayout.setDrawerListener(toggle);
        toggle.syncState();
        mBinding.navView.setNavigationItemSelectedListener(this);
        NavHeaderShopMainBinding bindHeader =
            NavHeaderShopMainBinding.inflate(LayoutInflater.from(mBinding.navView.getContext()));
        mBinding.navView.addHeaderView(bindHeader.getRoot());
        bindHeader
            .setImage(URL_IMAGE);
        bindHeader.setName("Hoang Van Nha");
        bindHeader.executePendingBindings();
        addFragment(CouponFragment.newInstance(), R.string.menu_coupon);
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
                addFragment(CouponFragment.newInstance(), R.string.menu_coupon);
                break;
            case R.id.action_notification:
                addFragment(NotificationFragment.newInstance(), R.string.menu_notification);
                break;
            case R.id.action_other_notification:
                break;
            case R.id.action_share:
                addFragment(ShareFragment.newInstance(), R.string.menu_share);
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
