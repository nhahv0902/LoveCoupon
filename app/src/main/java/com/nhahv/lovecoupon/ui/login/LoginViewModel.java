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
    private ILoginView mILoginView;
    private ObservableField<String> mEmail = new ObservableField<>();
    private ObservableField<String> mPassword = new ObservableField<>();

    public LoginViewModel(Context context, ILoginView iLoginView) {
        mContext = context;
        mILoginView = iLoginView;
    }

    public void clickLogin(LoginType type) {
        if (mILoginView == null) return;
        switch (type) {
            case NORMAL:
                mILoginView.startUiShopMain();
                break;
            case FACEBOOK:
                mILoginView.startUiShopMain();
                break;
            case GOOGLE:
                mILoginView.startUiShopMain();
                break;
            default:
                break;
        }
    }

    public void clickResetPassword() {
        mILoginView.startUiShopMain();
    }

    public ObservableField<String> getEmail() {
        return mEmail;
    }

    public ObservableField<String> getPassword() {
        return mPassword;
    }
}
