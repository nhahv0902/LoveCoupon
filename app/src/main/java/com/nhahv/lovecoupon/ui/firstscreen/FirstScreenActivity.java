package com.nhahv.lovecoupon.ui.firstscreen;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.databinding.DataBindingUtil;
import android.location.Geocoder;
import android.location.Location;
import android.os.Bundle;
import android.os.Handler;
import android.os.ResultReceiver;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationServices;
import com.nhahv.lovecoupon.R;
import com.nhahv.lovecoupon.databinding.ActivityFirstScreenBinding;
import com.nhahv.lovecoupon.service.LocationAddressService;
import com.nhahv.lovecoupon.ui.customer.main.CustomerMainActivity;
import com.nhahv.lovecoupon.ui.login.LoginActivity;
import com.nhahv.lovecoupon.ui.shop.main.ShopMainActivity;
import com.nhahv.lovecoupon.util.ActivityUtil;
import com.nhahv.lovecoupon.util.SharePreferenceUtil;

import permissions.dispatcher.NeedsPermission;
import permissions.dispatcher.RuntimePermissions;

import static com.nhahv.lovecoupon.util.Constant.BundleConstant.BUNDLE_NOTIFICATION;
import static com.nhahv.lovecoupon.util.Constant.LocationAddress.LOCATION_DATA_EXTRA;
import static com.nhahv.lovecoupon.util.Constant.LocationAddress.RECEIVER;
import static com.nhahv.lovecoupon.util.Constant.LocationAddress.RESULT_DATA_KEY;

@RuntimePermissions
public class FirstScreenActivity extends AppCompatActivity
    implements FirstScreenHandler, GoogleApiClient.ConnectionCallbacks,
    GoogleApiClient.OnConnectionFailedListener {
    private final String TAG = getClass().getSimpleName();
    private ActivityFirstScreenBinding mBinding;
    private FirstScreenViewModel mViewModel;
    private int mStartNotification;
    protected GoogleApiClient mGoogleApiClient;
    protected Location mLastLocation;
    protected boolean mAddressRequested;
    private AddressResultReceiver mResultReceiver;

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

    /* get keyHash of facebook sdk
    *   try {
            @SuppressLint("PackageManagerGetSignatures") PackageInfo info =
                getPackageManager().getPackageInfo(getPackageName(), PackageManager.GET_SIGNATURES);
            for (Signature signature : info.signatures) {
                MessageDigest md = MessageDigest.getInstance("SHA");
                md.update(signature.toByteArray());
                Log.d("KeyHash:", Base64.encodeToString(md.digest(), Base64.DEFAULT));
            }
        } catch (PackageManager.NameNotFoundException | NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    * */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_first_screen);
        mViewModel = new FirstScreenViewModel(getApplicationContext(), this);
        mBinding.setViewModel(mViewModel);
        /*
        * location address
        * */
        mResultReceiver = new AddressResultReceiver(new Handler());
        mAddressRequested = false;
        buildGoogleApiClient();
        /*
        * actin button
        * */
        if (mGoogleApiClient.isConnected() && mLastLocation != null) {
            startIntentService();
        }
        mAddressRequested = true;
    }

    protected synchronized void buildGoogleApiClient() {
        mGoogleApiClient = new GoogleApiClient.Builder(this)
            .addConnectionCallbacks(this)
            .addOnConnectionFailedListener(this)
            .addApi(LocationServices.API)
            .build();
    }

    @Override
    protected void onStart() {
        super.onStart();
        mGoogleApiClient.connect();
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (mGoogleApiClient.isConnected()) mGoogleApiClient.disconnect();
    }

    @Override
    public void startUiMainShop() {
        startActivity(ShopMainActivity.getShopMainIntent(this));
        finish();
    }

    @NeedsPermission(
        {Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION})
    void startUI() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) !=
            PackageManager.PERMISSION_GRANTED &&
            ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) !=
                PackageManager.PERMISSION_GRANTED) {
            return;
        }
        mLastLocation = LocationServices.FusedLocationApi.getLastLocation(mGoogleApiClient);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        FirstScreenActivityPermissionsDispatcher.onRequestPermissionsResult(this,
            requestCode, grantResults);
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

    protected void startIntentService() {
        Intent intent = new Intent(this, LocationAddressService.class);
        intent.putExtra(RECEIVER, mResultReceiver);
        intent.putExtra(LOCATION_DATA_EXTRA, mLastLocation);
        startService(intent);
    }

    @Override
    public void onConnected(@Nullable Bundle bundle) {
        FirstScreenActivityPermissionsDispatcher.startUIWithCheck(this);
        if (mLastLocation != null) {
            if (!Geocoder.isPresent()) {
                ActivityUtil.showMsg(getApplicationContext(), R.string.no_geocoder_available);
                return;
            }
            if (mAddressRequested) startIntentService();
        }
    }

    @Override
    public void onConnectionSuspended(int i) {
        Log.i(TAG, "Connection suspended");
        mGoogleApiClient.connect();
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult result) {
        Log.i(TAG, "Connection failed: ConnectionResult.getErrorCode() = " + result.getErrorCode());
    }

    private class AddressResultReceiver extends ResultReceiver {
        public AddressResultReceiver(Handler handler) {
            super(handler);
        }

        @Override
        protected void onReceiveResult(int resultCode, Bundle resultData) {
            String city = resultData.getString(RESULT_DATA_KEY);
            SharePreferenceUtil.getInstance(getApplicationContext()).writeCity(city);
            mAddressRequested = false;
        }
    }
}
