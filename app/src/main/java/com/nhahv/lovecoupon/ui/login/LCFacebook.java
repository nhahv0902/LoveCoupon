package com.nhahv.lovecoupon.ui.login;

import android.app.Activity;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.util.Log;

import com.facebook.AccessToken;
import com.facebook.AccessTokenTracker;
import com.facebook.CallbackManager;
import com.facebook.FacebookException;
import com.facebook.Profile;
import com.facebook.ProfileTracker;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;

import java.util.Collections;

import static com.nhahv.lovecoupon.util.Constant.DataConstant.DATA_PUBLIC_PROFILE;

/**
 * Created by Nhahv0902 on 3/11/2017.
 * <></>
 */
public class LCFacebook {
    private static LCFacebook sInstance;
    private final static int TIME_DELAY = 1000;
    private final String TAG = getClass().getSimpleName();
    private final Activity mContext;
    private final CallbackManager mCallbackManager;
    private final ProfileTracker mProfileTracker;
    private final AccessTokenTracker mTokenTracker;
    private final FacebookCallback mCallback;

    private LCFacebook(@NonNull Activity context, @NonNull FacebookCallback callback) {
        mContext = context;
        mCallback = callback;
        mCallbackManager = CallbackManager.Factory.create();
        LoginManager.getInstance().registerCallback(mCallbackManager,
            new com.facebook.FacebookCallback<LoginResult>() {
                @Override
                public void onSuccess(LoginResult result) {
                    new Handler().postDelayed(() -> {
                        Profile profile = Profile.getCurrentProfile();
                        mCallback.onSuccess(result.getAccessToken(), profile);
                    }, TIME_DELAY);
                }

                @Override
                public void onCancel() {
                    mCallback.onError();
                }

                @Override
                public void onError(FacebookException error) {
                    mCallback.onError();
                    error.printStackTrace();
                }
            });
        mProfileTracker = new ProfileTracker() {
            @Override
            protected void onCurrentProfileChanged(Profile oldProfile, Profile newProfile) {
                oldProfile = newProfile;
                AccessToken token = AccessToken.getCurrentAccessToken();
                if (newProfile != null && token != null) {
                    mCallback.onSuccess(token, newProfile);
                }
            }
        };
        mTokenTracker = new AccessTokenTracker() {
            @Override
            protected void onCurrentAccessTokenChanged(AccessToken oldAccessToken,
                                                       AccessToken currentAccessToken) {
                oldAccessToken = currentAccessToken;
                Log.d(TAG, currentAccessToken.getToken() + "");
            }
        };
        mProfileTracker.stopTracking();
        mTokenTracker.stopTracking();
    }

    public static LCFacebook getInstance(@NonNull Activity context,
                                         @NonNull FacebookCallback callback) {
        if (sInstance == null) sInstance = new LCFacebook(context, callback);
        return sInstance;
    }

    public void login() {
        LoginManager.getInstance()
            .logInWithReadPermissions(mContext, Collections.singletonList(DATA_PUBLIC_PROFILE));
    }

    public void stopTracker() {
        mProfileTracker.stopTracking();
        mTokenTracker.stopTracking();
    }

    public CallbackManager getCallbackManager() {
        return mCallbackManager;
    }

    public void logout() {
        LoginManager.getInstance().logOut();
    }

    public interface FacebookCallback {
        void onSuccess(AccessToken token, Profile profile);
        void onError();
    }
}
