package com.nhahv.lovecoupon.ui.firstscreen;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.nhahv.lovecoupon.R;
import com.nhahv.lovecoupon.databinding.ActivityFirstScreenBinding;
import com.nhahv.lovecoupon.ui.login.LoginActivity;

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
    }

    @Override
    public void startUiLogin(AccountType type) {
        startActivity(LoginActivity.getLoginIntent(this, type));
        finish();
    }
}
