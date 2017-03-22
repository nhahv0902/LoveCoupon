package com.nhahv.lovecoupon.ui.shop.setting;

import android.support.annotation.NonNull;
import com.nhahv.lovecoupon.data.model.ShopProfile;

/**
 * Created by Nhahv0902 on 3/9/2017.
 * <></>
 */
public class ProfileShopValidation {
    private ShopProfile mProfile;

    public ProfileShopValidation(@NonNull ShopProfile profile) {
        mProfile = profile;
    }

    public void validation(@NonNull ProfileCallback callback) {
        if (!isNonNull(mProfile.getName())) {
            callback.onError(ProfileError.SHOP_NAME);
            return;
        }
        if (!isNonNull(mProfile.getAddress())) {
            callback.onError(ProfileError.SHOP_NAME);
            return;
        }
        if (!isNonNullPassword1()) {
            callback.onError(ProfileError.USER_1);
            return;
        }
        if (!isNonNullPassword2()) {
            callback.onError(ProfileError.USER_2);
            return;
        }
        callback.onSuccess();
    }

    private boolean isNonNull(String string) {
        return string != null && !string.isEmpty();
    }

    private boolean isNonNullPassword2() {
        return mProfile.getUser2() == null
                || mProfile.getUser2().isEmpty()
                || (mProfile.getPassword2() != null && !mProfile.getPassword2().isEmpty());
    }

    private boolean isNonNullPassword1() {
        return mProfile.getUser1() == null
                || mProfile.getUser1().isEmpty()
                || (mProfile.getPassword1() != null && !mProfile.getPassword1().isEmpty());
    }

    public enum ProfileError {
        SHOP_NAME, ADDRESS, USER_1, USER_2
    }

    public interface ProfileCallback {
        void onSuccess();

        void onError(ProfileError error);
    }
}
