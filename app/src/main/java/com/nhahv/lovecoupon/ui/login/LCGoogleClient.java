package com.nhahv.lovecoupon.ui.login;

import android.content.Context;
import android.os.AsyncTask;

import com.google.android.gms.auth.GoogleAuthException;
import com.google.android.gms.auth.GoogleAuthUtil;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.GoogleApiClient;
import com.nhahv.lovecoupon.R;

import java.io.IOException;

import static com.nhahv.lovecoupon.util.Constant.DataConstant.DATA_SCOPE;

/**
 * Created by Nhahv0902 on 3/7/2017.
 * <></>
 */
public class LCGoogleClient {
    private Context mContext;
    private GoogleApiClient mClient;

    public LCGoogleClient(Context context) {
        mContext = context;
        initGoogle();
    }

    private void initGoogle() {
        GoogleSignInOptions gso =
            new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(mContext.getString(R.string.key_google_server))
                .requestEmail()
                .build();
        mClient = new GoogleApiClient.Builder(mContext)
            .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
            .build();
        mClient.connect();
    }

    public void requestToken(String email, CallBack callBack) {
        new GetGoogleTokenAsync(callBack).execute(email);
    }

    public GoogleApiClient getClient() {
        return mClient;
    }

    public class GetGoogleTokenAsync extends AsyncTask<String, Void, String> {
        private CallBack mCallBack;

        public GetGoogleTokenAsync(CallBack callBack) {
            mCallBack = callBack;
        }

        @Override
        protected String doInBackground(String... strs) {
            try {
                String email = strs[0];
                return GoogleAuthUtil.getToken(mContext, email, DATA_SCOPE);
            } catch (IOException | GoogleAuthException e) {
                e.printStackTrace();
                return null;
            }
        }

        @Override
        protected void onPostExecute(String token) {
            super.onPostExecute(token);
            if (mCallBack == null) return;
            if (token != null) mCallBack.getTokenSuccess(token);
            else mCallBack.getTokenError();
        }
    }

    public interface CallBack {
        void getTokenSuccess(String token);
        void getTokenError();
    }
}
