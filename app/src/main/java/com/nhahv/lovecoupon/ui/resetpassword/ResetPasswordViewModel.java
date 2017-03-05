package com.nhahv.lovecoupon.ui.resetpassword;

import android.content.Context;
import android.databinding.BaseObservable;
import android.databinding.ObservableField;

import com.nhahv.lovecoupon.R;
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

    public ResetPasswordViewModel(Context context, IResetPassword iResetPassword) {
        mContext = context;
        mIResetPassword = iResetPassword;
    }
    public void clickSendEmail(){
        new UserValidation(mEmail.get()).validationEmail(new UserValidation.Callback() {
            @Override
            public void onSuccess() {
                // TODO: 3/6/2017 call api send email
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
