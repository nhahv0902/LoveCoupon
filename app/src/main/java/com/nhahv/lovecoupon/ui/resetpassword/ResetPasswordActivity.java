package com.nhahv.lovecoupon.ui.resetpassword;

import android.app.ProgressDialog;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.afollestad.materialdialogs.MaterialDialog;
import com.nhahv.lovecoupon.R;
import com.nhahv.lovecoupon.databinding.ActivityResetPasswordBinding;

public class ResetPasswordActivity extends AppCompatActivity implements IResetPassword {
    private ActivityResetPasswordBinding mBinding;
    private ProgressDialog mProgressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_reset_password);
        mBinding.setViewModel(new ResetPasswordViewModel(getApplicationContext(), this));
    }

    @Override
    public void showProgressDialog(String msg) {
        if (mProgressDialog == null) {
            mProgressDialog = new ProgressDialog(this);
            mProgressDialog.setMessage(msg);
            mProgressDialog.setIndeterminate(true);
        }
        if (!mProgressDialog.isShowing()) mProgressDialog.show();
    }

    @Override
    public void hideProgressDialog() {
        if (mProgressDialog != null && mProgressDialog.isShowing()) mProgressDialog.dismiss();
    }

    @Override
    public void showDialogResult(String result) {
        String content = getString(
            result != null ? R.string.action_send_email_success : R.string.send_email_fails);
        new MaterialDialog.Builder(this)
            .content(content)
            .positiveText(R.string.agree)
            .positiveColor(getResources().getColor(R.color.color_blue_600))
            .show();
    }
}
