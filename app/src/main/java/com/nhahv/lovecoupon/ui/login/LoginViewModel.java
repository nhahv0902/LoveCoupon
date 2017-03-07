package com.nhahv.lovecoupon.ui.login;

import android.content.Context;
import android.databinding.BaseObservable;
import android.databinding.ObservableField;
import android.support.annotation.NonNull;
import android.util.Log;

import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.nhahv.lovecoupon.R;
import com.nhahv.lovecoupon.ui.firstscreen.AccountType;
import com.nhahv.lovecoupon.util.ActivityUtil;

/**
 * Created by Nhahv0902 on 3/6/2017.
 * <></>
 */
public class LoginViewModel extends BaseObservable {
    private final String TAG = getClass().getSimpleName();
    private Context mContext;
    private ILoginView mILoginView;
    private ObservableField<String> mEmail = new ObservableField<>();
    private ObservableField<String> mPassword = new ObservableField<>();
    private CallbackManager mCallbackManager;

    public LoginViewModel(Context context, ILoginView iLoginView) {
        mContext = context;
        mILoginView = iLoginView;
        initFacebook();
    }

    private void initFacebook() {
        mCallbackManager = CallbackManager.Factory.create();
        LoginManager.getInstance().registerCallback(mCallbackManager,
            new FacebookCallback<LoginResult>() {
                @Override
                public void onSuccess(LoginResult loginResult) {
                    handlerFacebook(loginResult);
                }

                @Override
                public void onCancel() {
                    ActivityUtil.showMsg(mContext, R.string.msg_login_error);
                }

                @Override
                public void onError(FacebookException exception) {
                    ActivityUtil.showMsg(mContext, R.string.msg_login_error);
                    exception.printStackTrace();
                }
            });
    }

    private void handlerFacebook(LoginResult result) {
        Log.d(TAG, result.getAccessToken().getToken() + "");
    }

    public void clickLogin(LoginType type) {
        if (mILoginView == null) return;
        switch (type) {
            case NORMAL:
            case FACEBOOK:
                mILoginView.loginFacebook();
                break;
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

    public CallbackManager getCallbackManager() {
        return mCallbackManager;
    }

    public ObservableField<String> getEmail() {
        return mEmail;
    }

    public ObservableField<String> getPassword() {
        return mPassword;
    }
}
