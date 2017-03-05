package com.nhahv.lovecoupon.ui.login;

import android.content.Context;
import android.databinding.BaseObservable;
import android.databinding.ObservableField;

/**
 * Created by Nhahv0902 on 3/6/2017.
 * <></>
 */
public class LoginViewModel extends BaseObservable {
    private Context mContext;
    private ObservableField<String> mEmail = new ObservableField<>();
    private ObservableField<String> mPassword = new ObservableField<>();

    public LoginViewModel(Context context) {
        mContext = context;
    }

    public void clickLogin(LoginType type) {
    }

    public void clickResetPassword() {
    }

    public ObservableField<String> getEmail() {
        return mEmail;
    }

    public ObservableField<String> getPassword() {
        return mPassword;
    }
}
