package com.nhahv.lovecoupon.ui.login;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.nhahv.lovecoupon.R;
import com.nhahv.lovecoupon.databinding.ActivityLoginBinding;
import com.nhahv.lovecoupon.ui.BaseActivity;
import com.nhahv.lovecoupon.ui.customer.main.CustomerMainActivity;
import com.nhahv.lovecoupon.ui.firstscreen.AccountType;
import com.nhahv.lovecoupon.ui.firstscreen.FirstScreenActivity;
import com.nhahv.lovecoupon.ui.resetpassword.ResetPasswordActivity;
import com.nhahv.lovecoupon.ui.shop.main.ShopMainActivity;

import static com.nhahv.lovecoupon.util.Constant.BundleConstant.BUNDLE_ACCOUNT_TYPE;
import static com.nhahv.lovecoupon.util.Constant.DataConstant.DATA_NOT_NOTIFICATION;
import static com.nhahv.lovecoupon.util.Constant.RequestConstant.REQUEST_GOOGLE;

public class LoginActivity extends BaseActivity implements LoginHandler {
    private ActivityLoginBinding mBinding;
    private LoginViewModel mViewModel;

    public static Intent getLoginIntent(Context context, AccountType type) {
        Intent intent = new Intent(context, LoginActivity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable(BUNDLE_ACCOUNT_TYPE, type);
        intent.putExtras(bundle);
        return intent;
    }

    private AccountType getDataFromIntent() {
        Bundle bundle = getIntent().getExtras();
        if (bundle == null) return AccountType.SHOP;
        return (AccountType) bundle.getSerializable(BUNDLE_ACCOUNT_TYPE);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_login);
        mViewModel = new LoginViewModel(this, this, getDataFromIntent());
        mBinding.setViewModel(mViewModel);
    }

    @Override
    public void showProgress() {
        showProgressDialog();
    }

    @Override
    public void hideProgress() {
        hideProgressDialog();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_GOOGLE) {
            GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
            mViewModel.handlerGoogle(result);
            return;
        }
        mViewModel.getCallbackManager().onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void startUiShopMain() {
        startActivity(ShopMainActivity.getShopMainIntent(this));
        finish();
    }

    @Override
    public void startUiCustomer() {
        startActivity(CustomerMainActivity.getCustomerMainIntent(this));
        finish();
    }

    @Override
    public void startUiResetPassword() {
        startActivity(ResetPasswordActivity.getIntent(this));
        finish();
    }

    @Override
    public void onBackPressed() {
        startActivity(FirstScreenActivity.getFirstIntent(this, DATA_NOT_NOTIFICATION));
        finish();
    }

    @Override
    protected void onStop() {
        super.onStop();
        mViewModel.stopTracker();
    }
}
