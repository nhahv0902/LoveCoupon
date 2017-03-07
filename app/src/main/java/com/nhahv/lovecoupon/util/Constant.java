package com.nhahv.lovecoupon.util;

/**
 * Created by Nhahv0902 on 3/6/2017.
 * <></>
 */
public final class Constant {
    public static final class BundleConstant {
        public static final String BUNDLE_NOTIFICATION = "BUNDLE_NOTIFICATION";
        public static final String BUNDLE_ACCOUNT_TYPE = "BUNDLE_ACCOUNT_TYPE";
    }

    public static final class RequestConstant {
        public static final int REQUEST_GOOGLE = 1;
    }

    public static final class PreferenceConstant {
        public static final String PREF_TOKEN = "PREF_TOKEN";
    }

    public static final class DataConstant {
        public static final String DATA_SCOPE =
            "oauth2:https://www.googleapis.com/auth/userinfo.profile";
        public static final String DATA_PUBLIC_PROFILE = "public_profile";
        public static final String DATA_ANDROID = "android";
        public static final String DATA_ADMIN = "1";
        public static final String FIRST_BASE64 = "data:image/jpeg;base64,";
        public static final int DATA_PREVIEW_PICTURE = 6;
        public static final String DATA_FACEBOOK = "facebook";
        public static final String DATA_GOOGLE = "google";
        public static final int SUCCESS = 1;
    }
}
