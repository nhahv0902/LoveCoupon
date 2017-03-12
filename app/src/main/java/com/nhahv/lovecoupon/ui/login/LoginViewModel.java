package com.nhahv.lovecoupon.ui.login;

import android.app.Activity;
import android.databinding.BaseObservable;
import android.databinding.ObservableField;
import android.support.annotation.NonNull;

import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.Profile;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.nhahv.lovecoupon.R;
import com.nhahv.lovecoupon.data.model.CustomerProfile;
import com.nhahv.lovecoupon.data.model.ShopProfile;
import com.nhahv.lovecoupon.data.source.Callback;
import com.nhahv.lovecoupon.data.source.remote.authorization.AuthorizationRepository;
import com.nhahv.lovecoupon.ui.firstscreen.AccountType;
import com.nhahv.lovecoupon.util.ActivityUtil;
import com.nhahv.lovecoupon.util.SharePreferenceUtil;

import static com.nhahv.lovecoupon.util.Constant.DataConstant.DATA_FACEBOOK;
import static com.nhahv.lovecoupon.util.Constant.DataConstant.DATA_GOOGLE;
import static com.nhahv.lovecoupon.util.Constant.PreferenceConstant.PREF_ACCOUNT_TYPE;
import static com.nhahv.lovecoupon.util.Constant.PreferenceConstant.PREF_IS_LOGIN;
import static com.nhahv.lovecoupon.util.Constant.PreferenceConstant.PREF_TOKEN;

/**
 * Created by Nhahv0902 on 3/6/2017.
 * <></>
 */
public class LoginViewModel extends BaseObservable {
    private final String TAG = getClass().getSimpleName();
    private final Activity mContext;
    private final LoginHandler mLoginHandler;
    private final ObservableField<String> mEmail = new ObservableField<>();
    private final ObservableField<String> mPassword = new ObservableField<>();
    private final LCGoogle mLCGoogle;
    private final LCFacebook mFacebook;
    private final AuthorizationRepository mRepository;
    private final AccountType mType;
    private final SharePreferenceUtil mPreference;

    public LoginViewModel(@NonNull Activity context, @NonNull LoginHandler loginHandler,
                          @NonNull AccountType type) {
        mContext = context;
        mLoginHandler = loginHandler;
        mType = type;
        mPreference = SharePreferenceUtil.getInstance(mContext);
        mRepository = AuthorizationRepository.getInstance();
        mLCGoogle = LCGoogle.getInstance(mContext);
        LCFacebook.FacebookCallback callback = new LCFacebook.FacebookCallback() {
            @Override
            public void onSuccess(AccessToken token, Profile profile) {
                handlerFacebook(token, profile);
            }

            @Override
            public void onError() {
                loginError();
            }
        };
        mFacebook = LCFacebook.getInstance(mContext, callback);
    }

    public void stopTracker() {
        mFacebook.stopTracker();
    }

    private void handlerFacebook(AccessToken result, Profile profile) {
        switch (mType) {
            case SHOP:
                mRepository.loginSocialShop(result.getUserId(), DATA_FACEBOOK, result.getToken(),
                    new Callback<ShopProfile>() {
                        @Override
                        public void onSuccess(ShopProfile data) {
                            checkStartUiMain();
                            mPreference.writeProfileShop(data);
                        }

                        @Override
                        public void onError() {
                            loginError();
                        }
                    });
                break;
            case CUSTOMER:
                String token = mPreference.getString(PREF_TOKEN);
                mRepository.loginCustomer(result.getUserId(), null, token, new Callback<Integer>() {
                    @Override
                    public void onSuccess(Integer data) {
                        String name = profile.getName();
                        String id = profile.getId();
                        String avatar =
                            mContext.getString(R.string.facebook_avatar, profile.getId());
                        mPreference.writeProfileCustomer(new CustomerProfile(id, name, avatar));
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

    public void handlerGoogle(GoogleSignInResult result) {
        GoogleSignInAccount account = result.getSignInAccount();
        if (!result.isSuccess() || account == null) {
            loginError();
            return;
        }
        switch (mType) {
            case SHOP:
                requestGoogleToken(account.getId(), account.getEmail());
                break;
            case CUSTOMER:
                if (account.getId() == null) return;
                String token = mPreference.getString(PREF_TOKEN);
                mRepository.loginCustomer(account.getId(), null, token, new Callback<Integer>() {
                    @Override
                    public void onSuccess(Integer data) {
                        String name = account.getDisplayName();
                        if (name == null) name = "";
                        String avatar = "";
                        if (account.getPhotoUrl() != null) {
                            avatar = account.getPhotoUrl().toString();
                        }
                        mPreference.writeProfileCustomer(
                            new CustomerProfile(account.getId(), name, avatar));
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

    private void requestGoogleToken(String id, String email) {
        mLCGoogle.requestToken(email, new LCGoogle.CallBack() {
            @Override
            public void getTokenSuccess(String token) {
                mRepository.loginSocialShop(id, DATA_GOOGLE, token, new Callback<ShopProfile>() {
                    @Override
                    public void onSuccess(ShopProfile data) {
                        mPreference.writeProfileShop(data);
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
        if (mLoginHandler == null) return;
        mLoginHandler.showProgressDialog();
        switch (type) {
            case NORMAL:
                loginNormal();
                break;
            case FACEBOOK:
                mFacebook.login();
                break;
            case GOOGLE:
                mLCGoogle.login();
                break;
            default:
                break;
        }
    }

    private void loginNormal() {
        new UserValidation(mEmail.get(), mPassword.get()).validation(new UserValidation.Callback() {
            @Override
            public void onSuccess() {
                String token = mPreference.getString(PREF_TOKEN);
                switch (mType) {
                    case SHOP:
                        mRepository.loginNormalShop(mEmail.get(), mPassword.get(),
                            new Callback<ShopProfile>() {
                                @Override
                                public void onSuccess(ShopProfile data) {
                                    checkStartUiMain();
                                    mPreference.writeProfileShop(data);
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
                                    mPreference.writeProfileCustomer(
                                        new CustomerProfile(mEmail.get(), mEmail.get(), null));
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
        mPreference.writePreference(PREF_IS_LOGIN, true);
        mPreference.writePreference(PREF_ACCOUNT_TYPE, mType.toString());
        mLoginHandler.hideProgressDialog();
        switch (mType) {
            case SHOP:
                mLoginHandler.startUiShopMain();
                break;
            case CUSTOMER:
                mLoginHandler.startUiCustomer();
                break;
            default:
                break;
        }
    }

    private void loginError() {
        ActivityUtil.showMsg(mContext, R.string.msg_login_error);
    }

    public void clickResetPassword() {
        mLoginHandler.startUiResetPassword();
    }

    public CallbackManager getCallbackManager() {
        return mFacebook.getCallbackManager();
    }

    public ObservableField<String> getEmail() {
        return mEmail;
    }

    public ObservableField<String> getPassword() {
        return mPassword;
    }
}
