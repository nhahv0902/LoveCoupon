package com.nhahv.lovecoupon.ui.login;

import android.content.Context;
import android.databinding.BaseObservable;
import android.databinding.ObservableField;
import android.util.Log;

import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.Profile;
import com.facebook.ProfileTracker;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.nhahv.lovecoupon.R;
import com.nhahv.lovecoupon.data.model.ProfileCustomer;
import com.nhahv.lovecoupon.data.model.ProfileShop;
import com.nhahv.lovecoupon.data.source.Callback;
import com.nhahv.lovecoupon.data.source.remote.authorization.AuthorizationRepository;
import com.nhahv.lovecoupon.ui.firstscreen.AccountType;
import com.nhahv.lovecoupon.ui.resetpassword.ResetPasswordActivity;
import com.nhahv.lovecoupon.util.ActivityUtil;
import com.nhahv.lovecoupon.util.SharePreferenceUtil;

import static com.facebook.FacebookSdk.getApplicationContext;
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
    private Context mContext;
    private ILoginView mILoginView;
    private ObservableField<String> mEmail = new ObservableField<>();
    private ObservableField<String> mPassword = new ObservableField<>();
    private LCGoogleClient mClient;
    private CallbackManager mCallbackManager;
    private AuthorizationRepository mRepository;
    private AccountType mType;
    private SharePreferenceUtil mPreference;
    private ProfileTracker mProfileTracker;

    public LoginViewModel(Context context, ILoginView iLoginView, AccountType type) {
        mContext = context;
        mILoginView = iLoginView;
        mType = type;
        mPreference = SharePreferenceUtil.getInstance(mContext);
        mRepository = AuthorizationRepository.getInstance();
        mClient = LCGoogleClient.getInstance(mContext);
        initFacebook();
    }

    private void initFacebook() {
        FacebookSdk.sdkInitialize(getApplicationContext());
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
        mProfileTracker = new ProfileTracker() {
            @Override
            protected void onCurrentProfileChanged(Profile oldProfile, Profile currentProfile) {
                this.stopTracking();
                Profile.setCurrentProfile(currentProfile);
                if (currentProfile == null) return;
                String name = currentProfile.getName();
                String id = currentProfile.getId();
                String avatar = currentProfile.getProfilePictureUri(200, 200).toString();
                Log.d(TAG, "avatar = " + avatar);
                mPreference.writeProfileCustomer(new ProfileCustomer(id, name, avatar));
            }
        };
        mProfileTracker.startTracking();
    }

    public void stopTracker() {
        mProfileTracker.stopTracking();
    }

    private void handlerFacebook(AccessToken result) {
        Log.d(TAG, "id = " + result.getUserId());
        switch (mType) {
            case SHOP:
                mRepository.loginSocialShop(result.getUserId(), DATA_FACEBOOK, result.getToken(),
                    new Callback<ProfileShop>() {
                        @Override
                        public void onSuccess(ProfileShop data) {
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
                        Profile profile = Profile.getCurrentProfile();
                        if (profile == null) return;
                        String name = profile.getName();
                        String id = profile.getId();
                        String avatar = profile.getProfilePictureUri(200, 200).toString();
                        Log.d(TAG, "avatar = " + avatar);
                        mPreference.writeProfileCustomer(new ProfileCustomer(id, name, avatar));
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
        if (!result.isSuccess()) {
            loginError();
            return;
        }
        GoogleSignInAccount account = result.getSignInAccount();
        if (account == null) {
            loginError();
            return;
        }
        switch (mType) {
            case SHOP:
                requestGoogleToken(result.getSignInAccount().getId(),
                    result.getSignInAccount().getEmail());
                break;
            case CUSTOMER:
                if (result.getSignInAccount().getId() == null) return;
                String token = mPreference.getString(PREF_TOKEN);
                mRepository.loginCustomer(result.getSignInAccount().getId(), null, token,
                    new Callback<Integer>() {
                        @Override
                        public void onSuccess(Integer data) {
                            String avatar = account.getPhotoUrl() == null ? null :
                                account.getPhotoUrl().toString();
                            mPreference.writeProfileCustomer(
                                new ProfileCustomer(account.getId(), account.getDisplayName(),
                                    avatar));
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
        mClient.requestToken(email, new LCGoogleClient.CallBack() {
            @Override
            public void getTokenSuccess(String token) {
                mRepository.loginSocialShop(id, DATA_GOOGLE, token, new Callback<ProfileShop>() {
                    @Override
                    public void onSuccess(ProfileShop data) {
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
                String token = mPreference.getString(PREF_TOKEN);
                switch (mType) {
                    case SHOP:
                        mRepository.loginNormalShop(mEmail.get(), mPassword.get(),
                            new Callback<ProfileShop>() {
                                @Override
                                public void onSuccess(ProfileShop data) {
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
                                        new ProfileCustomer(mEmail.get(), mPassword.get(), null));
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
        mILoginView.startUiResetPassword();
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
