package com.nhahv.lovecoupon.ui.login;

import android.content.Context;
import android.databinding.BaseObservable;
import android.databinding.ObservableField;
import android.support.annotation.NonNull;
import android.util.Log;

import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.nhahv.lovecoupon.R;
import com.nhahv.lovecoupon.data.model.ShopProfile;
import com.nhahv.lovecoupon.data.source.Callback;
import com.nhahv.lovecoupon.data.source.remote.AuthorizationRepository;
import com.nhahv.lovecoupon.ui.firstscreen.AccountType;
import com.nhahv.lovecoupon.util.ActivityUtil;

import static com.nhahv.lovecoupon.util.Constant.DataConstant.DATA_FACEBOOK;
import static com.nhahv.lovecoupon.util.Constant.DataConstant.DATA_GOOGLE;

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
    private LCGoogleClient mClient;
    private CallbackManager mCallbackManager;
    private AuthorizationRepository mRepository;

    public LoginViewModel(Context context, ILoginView iLoginView) {
        mContext = context;
        mILoginView = iLoginView;
        mRepository = AuthorizationRepository.getInstance();
        mClient = new LCGoogleClient(mContext);
        initFacebook();
    }

    private void initFacebook() {
        mCallbackManager = CallbackManager.Factory.create();
        LoginManager.getInstance().registerCallback(mCallbackManager,
            new FacebookCallback<LoginResult>() {
                @Override
                public void onSuccess(LoginResult loginResult) {
                    handlerFacebook(loginResult.getAccessToken());
                }

                @Override
                public void onCancel() {
                    loginError();
                }

                @Override
                public void onError(FacebookException exception) {
                    loginError();
                    exception.printStackTrace();
                }
            });
    }

    private void handlerFacebook(AccessToken result) {
        mRepository.loginSocialShop(result.getUserId(), DATA_FACEBOOK, result.getToken(),
            new Callback<ShopProfile>() {
                @Override
                public void onSuccess(ShopProfile data) {
                    Log.d(TAG, data.getShopId());
                    Log.d(TAG, data.getToken());
                }

                @Override
                public void onError() {
                    loginError();
                }
            });
    }

    public void handlerGoogle(GoogleSignInResult result) {
        if (result.isSuccess() && result.getSignInAccount() != null) {
            requestGoogleToken(result.getSignInAccount().getId(),
                result.getSignInAccount().getEmail());
        } else ActivityUtil.showMsg(mContext, R.string.msg_login_error);
    }

    private void requestGoogleToken(String id, String email) {
        mClient.requestToken(email, new LCGoogleClient.CallBack() {
            @Override
            public void getTokenSuccess(String token) {
                mRepository.loginSocialShop(id, DATA_GOOGLE, token, new Callback<ShopProfile>() {
                    @Override
                    public void onSuccess(ShopProfile data) {
                        Log.d(TAG, "id = " + data.getShopId());
                        Log.d(TAG, "token = " + data.getToken());
                    }

                    @Override
                    public void onError() {
                        loginError();
                    }
                });
            }

            @Override
            public void getTokenError() {
                loginError();
            }
        });
    }

    public void clickLogin(LoginType type) {
        if (mILoginView == null) return;
        switch (type) {
            case NORMAL:
                loginNormal();
                break;
            case FACEBOOK:
                mILoginView.loginFacebook();
                break;
            case GOOGLE:
                mILoginView.loginGoogle(mClient.getClient());
                break;
            default:
                break;
        }
    }

    private void loginNormal() {
        new UserValidation(mEmail.get(), mPassword.get()).validation(new UserValidation.Callback() {
            @Override
            public void onSuccess() {
                mRepository.loginNormalShop(mEmail.get(), mPassword.get(),
                    new Callback<ShopProfile>() {
                        @Override
                        public void onSuccess(ShopProfile data) {
                            Log.d(TAG, "id  = " + data.getShopId());
                            Log.d(TAG, "token = " + data.getToken());
                        }

                        @Override
                        public void onError() {
                            loginError();
                        }
                    });
            }

            @Override
            public void onError(UserValidation.Error error) {
                switch (error) {
                    case EMAIL:
                        ActivityUtil.showMsg(mContext, R.string.msg_email_error);
                        break;
                    case PASSWORD:
                        ActivityUtil.showMsg(mContext, R.string.msg_password_error);
                        break;
                    default:
                        break;
                }
            }
        });
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

    private void loginError() {
        ActivityUtil.showMsg(mContext, R.string.msg_login_error);
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
