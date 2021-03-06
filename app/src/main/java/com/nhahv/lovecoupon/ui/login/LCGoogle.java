package com.nhahv.lovecoupon.ui.login;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.annotation.NonNull;
import com.facebook.FacebookSdk;
import com.google.android.gms.auth.GoogleAuthException;
import com.google.android.gms.auth.GoogleAuthUtil;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.GoogleApiClient;
import com.nhahv.lovecoupon.R;
import java.io.IOException;
import java.lang.ref.WeakReference;

import static com.nhahv.lovecoupon.util.Constant.DataConstant.DATA_SCOPE;
import static com.nhahv.lovecoupon.util.Constant.RequestConstant.REQUEST_GOOGLE;

/**
 * Created by Nhahv0902 on 3/7/2017.
 * <></>
 */
public class LCGoogle {
    private static LCGoogle sInstance;
    private final WeakReference<Activity> mReference;
    private GoogleApiClient mClient;

    private LCGoogle(@NonNull Activity activity) {
        mReference = new WeakReference<>(activity);
        FacebookSdk.sdkInitialize(activity.getApplicationContext());
        initGoogle();
    }

    public static LCGoogle getInstance(@NonNull Activity context) {
        if (sInstance == null) sInstance = new LCGoogle(context);
        return sInstance;
    }

    private void initGoogle() {
        Activity activity = mReference.get();
        if (activity == null) return;
        GoogleSignInOptions gso =
                new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestIdToken(
                        activity.getString(R.string.key_google_server)).requestEmail().build();
        mClient =
                new GoogleApiClient.Builder(activity).addApi(Auth.GOOGLE_SIGN_IN_API, gso).build();
        mClient.connect();
    }

    public void logout() {
        Activity activity = mReference.get();
        if (activity == null) return;
        GoogleSignInOptions gso =
                new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestEmail()
                        .build();
        mClient =
                new GoogleApiClient.Builder(activity).addApi(Auth.GOOGLE_SIGN_IN_API, gso).build();
        mClient.connect();
        Auth.GoogleSignInApi.signOut(mClient).setResultCallback(status -> {
        });
    }

    public void login() {
        Activity activity = mReference.get();
        if (activity == null) return;
        Intent signInIntent = Auth.GoogleSignInApi.getSignInIntent(mClient);
        activity.startActivityForResult(signInIntent, REQUEST_GOOGLE);
    }

    public void requestToken(String email, CallBack callBack) {
        new GetGoogleTokenAsync(callBack).execute(email);
    }

    public GoogleApiClient getClient() {
        return mClient;
    }

    public interface CallBack {
        void getTokenSuccess(String token);

        void getTokenError();
    }

    public class GetGoogleTokenAsync extends AsyncTask<String, Void, String> {
        private CallBack mCallBack;

        public GetGoogleTokenAsync(CallBack callBack) {
            mCallBack = callBack;
        }

        @Override
        protected String doInBackground(String... strs) {
            Activity activity = mReference.get();
            if (activity == null) return null;
            try {
                String email = strs[0];
                return GoogleAuthUtil.getToken(activity, email, DATA_SCOPE);
            } catch (IOException | GoogleAuthException e) {
                e.printStackTrace();
                return null;
            }
        }

        @Override
        protected void onPostExecute(String token) {
            super.onPostExecute(token);
            if (mCallBack == null) return;
            if (token != null) {
                mCallBack.getTokenSuccess(token);
            } else {
                mCallBack.getTokenError();
            }
        }
    }
}
