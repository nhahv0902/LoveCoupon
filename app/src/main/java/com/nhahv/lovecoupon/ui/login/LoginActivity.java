package com.nhahv.lovecoupon.ui.login;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.facebook.login.LoginManager;
import com.nhahv.lovecoupon.R;
import com.nhahv.lovecoupon.databinding.ActivityLoginBinding;
import com.nhahv.lovecoupon.ui.customer.main.CustomerMainActivity;
import com.nhahv.lovecoupon.ui.firstscreen.AccountType;
import com.nhahv.lovecoupon.ui.shop.main.ShopMainActivity;

import java.util.Collections;

import static com.nhahv.lovecoupon.util.Constant.BundleConstant.BUNDLE_ACCOUNT_TYPE;
import static com.nhahv.lovecoupon.util.Constant.DataConstant.DATA_FACEBOOK;

public class LoginActivity extends AppCompatActivity implements ILoginView {
    private ActivityLoginBinding mBinding;
    private LoginViewModel mViewModel;
    private AccountType mAccountType;

    public static Intent getLoginIntent(Context context, AccountType type) {
        Intent intent = new Intent(context, LoginActivity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable(BUNDLE_ACCOUNT_TYPE, type);
        intent.putExtras(bundle);
        return intent;
    }

    private void getDataFromIntent() {
        Bundle bundle = getIntent().getExtras();
        if (bundle == null) return;
        mAccountType = (AccountType) bundle.getSerializable(BUNDLE_ACCOUNT_TYPE);
        if (mAccountType == null) mAccountType = AccountType.SHOP;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_login);
        getDataFromIntent();
        mViewModel = new LoginViewModel(getApplicationContext(), this);
        mBinding.setViewModel(mViewModel);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        mViewModel.getCallbackManager().onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void loginFacebook() {
        LoginManager.getInstance()
            .logInWithReadPermissions(this, Collections.singletonList(DATA_FACEBOOK));
    }

    @Override
    public void startUiMain() {
        mViewModel.checkStartUiMain(mAccountType);
    }

    @Override
    public void startUiShopMain() {
        startActivity(ShopMainActivity.getShopMainIntent(this));
    }

    @Override
    public void startUiCustomer() {
        startActivity(CustomerMainActivity.getCustomerMainIntent(this));
    }
}
