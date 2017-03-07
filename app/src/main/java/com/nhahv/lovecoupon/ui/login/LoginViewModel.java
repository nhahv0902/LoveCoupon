package com.nhahv.lovecoupon.ui.login;

import android.content.Context;
import android.databinding.BaseObservable;
import android.databinding.ObservableField;
import android.support.annotation.NonNull;

import com.nhahv.lovecoupon.ui.firstscreen.AccountType;

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
            case FACEBOOK:
            case GOOGLE:
                mILoginView.startUiMain();
                break;
            default:
                break;
        }
    }

    public void checkStartUiMain(@NonNull AccountType type) {
        switch (type) {
            case SHOP:
                mILoginView.startUiShopMain();
                break;
            case CUSTOMER:
                mILoginView.startUiCustomer();
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
