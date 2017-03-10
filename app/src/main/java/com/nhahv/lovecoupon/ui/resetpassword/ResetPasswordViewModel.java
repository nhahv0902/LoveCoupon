package com.nhahv.lovecoupon.ui.resetpassword;

import android.content.Context;
import android.databinding.BaseObservable;
import android.databinding.ObservableField;
import android.support.annotation.NonNull;

import com.nhahv.lovecoupon.R;
import com.nhahv.lovecoupon.data.source.Callback;
import com.nhahv.lovecoupon.data.source.remote.authorization.AuthorizationRepository;
import com.nhahv.lovecoupon.ui.login.UserValidation;
import com.nhahv.lovecoupon.util.ActivityUtil;

/**
 * Created by Nhahv0902 on 3/6/2017.
 * <></>
 */
public class ResetPasswordViewModel extends BaseObservable {
    private Context mContext;
    private IResetPassword mIResetPassword;
    private ObservableField<String> mEmail = new ObservableField<>();
    private final AuthorizationRepository mRepository;

    public ResetPasswordViewModel(@NonNull Context context, IResetPassword iResetPassword) {
        mContext = context;
        mRepository = AuthorizationRepository.getInstance();
        mIResetPassword = iResetPassword;
    }

    public void clickSendEmail() {
        if (mRepository == null) return;
        new UserValidation(mEmail.get()).validationEmail(new UserValidation.Callback() {
            @Override
            public void onSuccess() {
                mIResetPassword.showProgressDialog(mContext.getString(R.string.msg_send_email));
                mRepository.resetPassword(mEmail.get(), new Callback<String>() {
                    @Override
                    public void onSuccess(String data) {
                        mIResetPassword.hideProgressDialog();
                        mIResetPassword.showDialogResult();
                    }

                    @Override
                    public void onError() {
                        mIResetPassword.hideProgressDialog();
                        ActivityUtil.showMsg(mContext, R.string.msg_send_email_error);
                    }
                });
            }

            @Override
            public void onError(UserValidation.Error error) {
                ActivityUtil.showMsg(mContext, R.string.msg_email_error);
            }
        });
    }

    public ObservableField<String> getEmail() {
        return mEmail;
    }
}
