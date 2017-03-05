package com.nhahv.lovecoupon.ui.login;

import android.support.annotation.NonNull;

/**
 * Created by Nhahv0902 on 2/22/2017.
 * <></>
 */
public class UserValidation {
    private String mEmail;
    private String mPassword;

    public UserValidation(String email, String password) {
        mEmail = email;
        mPassword = password;
    }

    public UserValidation(String email) {
        mEmail = email;
    }

    public void validationEmail(@NonNull Callback callback) {
        if (!isValidateEmail()) {
            callback.onError(Error.EMAIL);
            return;
        }
        callback.onSuccess();
    }

    public void validation(@NonNull Callback callback) {
        if (!isValidateEmail()) {
            callback.onError(Error.EMAIL);
            return;
        }
        if (!isValidatePassword()) {
            callback.onError(Error.PASSWORD);
            return;
        }
        callback.onSuccess();
    }

    private boolean isValidateEmail() {
        return mEmail != null && !mEmail.trim().isEmpty();
    }

    private boolean isValidatePassword() {
        return mPassword != null && !mPassword.trim().isEmpty();
    }

    public enum Error {
        EMAIL, PASSWORD
    }

    public interface Callback {
        void onSuccess();
        void onError(Error error);
    }
}
