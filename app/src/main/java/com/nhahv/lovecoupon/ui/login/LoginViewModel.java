package com.nhahv.lovecoupon.ui.login;

import android.content.Context;
import android.databinding.BaseObservable;
import android.databinding.ObservableField;
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
import com.nhahv.lovecoupon.data.source.remote.authorization.AuthorizationRepository;
import com.nhahv.lovecoupon.ui.firstscreen.AccountType;
import com.nhahv.lovecoupon.util.ActivityUtil;
import com.nhahv.lovecoupon.util.SharePreferenceUtil;

import static com.nhahv.lovecoupon.util.Constant.DataConstant.DATA_FACEBOOK;
import static com.nhahv.lovecoupon.util.Constant.DataConstant.DATA_GOOGLE;
import static com.nhahv.lovecoupon.util.Constant.PreferenceConstant.PREF_TOKEN;

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
    private AccountType mType;

    public LoginViewModel(Context context, ILoginView iLoginView, AccountType type) {
        mContext = context;
        mILoginView = iLoginView;
        mType = type;
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
        switch (mType) {
            case SHOP:
                mRepository.loginSocialShop(result.getUserId(), DATA_FACEBOOK, result.getToken(),
                    new Callback<ShopProfile>() {
                        @Override
                        public void onSuccess(ShopProfile data) {
                            checkStartUiMain();
                        }

                        @Override
                        public void onError() {
                            loginError();
                        }
                    });
                break;
            case CUSTOMER:
                String token = SharePreferenceUtil.getInstance(mContext).getString(PREF_TOKEN);
                mRepository.loginCustomer(result.getUserId(), null, token, new Callback<Integer>() {
                    @Override
                    public void onSuccess(Integer data) {
                        Log.d(TAG, "success = " + data);
                    }

                    @Override
                    public void onError() {
                        loginError();
                    }
                });
                break;
            default:
                break;
        }
    }

    public void handlerGoogle(GoogleSignInResult result) {
        if (result.isSuccess() && result.getSignInAccount() != null) {
            switch (mType) {
                case SHOP:
                    requestGoogleToken(result.getSignInAccount().getId(),
                        result.getSignInAccount().getEmail());
                    break;
                case CUSTOMER:
                    if (result.getSignInAccount().getId() == null) return;
                    String token = SharePreferenceUtil.getInstance(mContext).getString(PREF_TOKEN);
                    mRepository.loginCustomer(result.getSignInAccount().getId(), null, token,
                        new Callback<Integer>() {
                            @Override
                            public void onSuccess(Integer data) {
                                Log.d(TAG, "success = " + data);
                            }

                            @Override
                            public void onError() {
                                loginError();
                            }
                        });
                    break;
                default:
                    break;
            }
        } else loginError();
    }

    private void requestGoogleToken(String id, String email) {
        mClient.requestToken(email, new LCGoogleClient.CallBack() {
            @Override
            public void getTokenSuccess(String token) {
                mRepository.loginSocialShop(id, DATA_GOOGLE, token, new Callback<ShopProfile>() {
                    @Override
                    public void onSuccess(ShopProfile data) {
                        checkStartUiMain();
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
                String token = SharePreferenceUtil.getInstance(mContext).getString(PREF_TOKEN);
                Log.d(TAG, "token = " + token);
                switch (mType) {
                    case SHOP:
                        mRepository.loginNormalShop(mEmail.get(), mPassword.get(),
                            new Callback<ShopProfile>() {
                                @Override
                                public void onSuccess(ShopProfile data) {
                                    checkStartUiMain();
                                }

                                @Override
                                public void onError() {
                                    loginError();
                                }
                            });
                        break;
                    case CUSTOMER:
                        mRepository.loginCustomer(mEmail.get(), mPassword.get(), token,
                            new Callback<Integer>() {
                                @Override
                                public void onSuccess(Integer data) {
                                    checkStartUiMain();
                                }

                                @Override
                                public void onError() {
                                    loginError();
                                }
                            });
                        break;
                    default:
                        break;
                }
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

    private void checkStartUiMain() {
        switch (mType) {
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
