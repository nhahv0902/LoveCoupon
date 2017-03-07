package com.nhahv.lovecoupon.ui.firstscreen;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Base64;
import android.util.Log;

import com.nhahv.lovecoupon.R;
import com.nhahv.lovecoupon.databinding.ActivityFirstScreenBinding;
import com.nhahv.lovecoupon.ui.customer.main.CustomerMainActivity;
import com.nhahv.lovecoupon.ui.login.LoginActivity;
import com.nhahv.lovecoupon.ui.shop.main.ShopMainActivity;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import static com.nhahv.lovecoupon.util.Constant.BundleConstant.BUNDLE_NOTIFICATION;

public class FirstScreenActivity extends AppCompatActivity implements IFirstScreen {
    private ActivityFirstScreenBinding mBinding;
    private FirstScreenViewModel mViewModel;
    private int mStartNotification;

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
        try {
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
    }

    @Override
    public void startUiMainShop() {
        startActivity(ShopMainActivity.getShopMainIntent(this));
        finish();
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
}
