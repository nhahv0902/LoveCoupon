package com.nhahv.lovecoupon.ui.firstscreen;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.location.Address;
import android.location.Location;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import com.nhahv.lovecoupon.R;
import com.nhahv.lovecoupon.databinding.ActivityFirstScreenBinding;
import com.nhahv.lovecoupon.ui.customer.main.CustomerMainActivity;
import com.nhahv.lovecoupon.ui.login.LoginActivity;
import com.nhahv.lovecoupon.ui.shop.main.ShopMainActivity;
import com.nhahv.lovecoupon.util.SharePreferenceUtil;
import io.nlopez.smartlocation.OnLocationUpdatedListener;
import io.nlopez.smartlocation.SmartLocation;
import io.nlopez.smartlocation.location.providers.LocationGooglePlayServicesProvider;
import permissions.dispatcher.NeedsPermission;
import permissions.dispatcher.RuntimePermissions;

import static com.nhahv.lovecoupon.util.Constant.BundleConstant.BUNDLE_NOTIFICATION;

@RuntimePermissions
public class FirstScreenActivity extends AppCompatActivity
        implements FirstScreenHandler, OnLocationUpdatedListener {
    private final String TAG = getClass().getSimpleName();
    private ActivityFirstScreenBinding mBinding;
    private FirstScreenViewModel mViewModel;
    private int mStartNotification;
    private LocationGooglePlayServicesProvider mProvider;

    public static Intent getFirstIntent(Context context, int typeNotification) {
        Intent intent = new Intent(context, FirstScreenActivity.class);
        Bundle bundle = new Bundle();
        bundle.putInt(BUNDLE_NOTIFICATION, typeNotification);
        intent.putExtras(bundle);
        return intent;
    }

    public void getDataFromIntent() {
        Bundle bundle = getIntent().getExtras();
        if (bundle != null) mStartNotification = bundle.getInt(BUNDLE_NOTIFICATION);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_first_screen);
        mViewModel = new FirstScreenViewModel(getApplicationContext(), this);
        mBinding.setViewModel(mViewModel);
    }

    private void startLocation() {
        if (mProvider == null) {
            mProvider = new LocationGooglePlayServicesProvider();
        }
        mProvider.setCheckLocationSettings(true);
        SmartLocation smartLocation = new SmartLocation.Builder(this).logging(true).build();
        smartLocation.location(mProvider).start(this);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (mProvider != null) {
            mProvider.onActivityResult(requestCode, resultCode, data);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        FirstScreenActivityPermissionsDispatcher.startUIWithCheck(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        SmartLocation.with(this).location().stop();
    }

    @Override
    protected void onStop() {
        super.onStop();
        mProvider = null;
    }

    @Override
    public void startUiMainShop() {
        startActivity(ShopMainActivity.getShopMainIntent(this));
        finish();
    }

    @NeedsPermission({
            Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION
    })
    void startUI() {
        startLocation();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
            @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        FirstScreenActivityPermissionsDispatcher.onRequestPermissionsResult(this, requestCode,
                grantResults);
    }

    @Override
    public void startUiMainCustomer() {
        startActivity(CustomerMainActivity.getCustomerMainIntent(this));
        finish();
    }

    @Override
    public void startUiLogin(AccountType type) {
        startActivity(LoginActivity.getLoginIntent(this, type));
        finish();
    }

    @Override
    public void onLocationUpdated(Location location) {
        showLocation(location);
    }

    private void showLocation(Location location) {
        if (location == null) return;
        SmartLocation.with(this).geocoding().reverse(location, (original, results) -> {
            if (results.size() > 0) {
                Address result = results.get(0);
                if (result == null) return;
                SharePreferenceUtil.getInstance(getApplicationContext())
                        .writeCity(result.getLocality());
                Log.d("city = ", result.getLocality() + "");
            }
        });
    }
}
