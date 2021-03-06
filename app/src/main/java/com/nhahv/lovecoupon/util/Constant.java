package com.nhahv.lovecoupon.util;

/**
 * Created by Nhahv0902 on 3/6/2017.
 * <></>
 */
public final class Constant {
    public static final class BundleConstant {
        public static final String BUNDLE_NOTIFICATION = "BUNDLE_NOTIFICATION";
        public static final String BUNDLE_ACCOUNT_TYPE = "BUNDLE_ACCOUNT_TYPE";
        public static final String BUNDLE_USE_CREATE_TYPE = "BUNDLE_USE_CREATE_TYPE";
        public static final String BUNDLE_IMAGE = "BUNDLE_IMAGE";
        public static final String BUNDLE_POSITION = "BUNDLE_POSITION";
        public static final String BUNDLE_TEMPLATE = "BUNDLE_TEMPLATE";
        public static final String BUNDLE_NOTIFICATION_TYPE = "BUNDLE_NOTIFICATION_TYPE";
        public static final String BUNDLE_FOLDER_NAME = "BUNDLE_FOLDER_NAME";
        public static final String BUNDLE_COUPON = "BUNDLE_COUPON";
        public static final String BUNDLE_HANDLER = "BUNDLE_HANDLER";
    }

    public static final class RequestConstant {
        public static final int REQUEST_GOOGLE = 1;
        public static final int REQUEST_PICK_IMAGE = 2;
        public static final int REQUEST_OPEN_GALLERY = 3;
        public static final int REQUEST_NOTIFICATION = 4;
        public static final int REQUEST_TEMPLATE = 5;
        public static final int REQUEST_UI_COUPON_OF_SHOP = 6;
        public static final int REQUEST_ADDITION_COUPON = 7;
    }

    public static final class PreferenceConstant {
        public static final String PREF_TOKEN = "PREF_TOKEN";
        public static final String PREF_IS_LOGIN = "PREF_IS_LOGIN";
        public static final String PREF_ACCOUNT_TYPE = "PREF_ACCOUNT_TYPE";
        public static final String PREF_CITY = "PREF_CITY";
        public static final String PREF_EMAIL = "PREF_EMAIL";
        public static final String PREF_PASSWORD = "PREF_PASSWORD";
    }

    public static final class DataConstant {
        public static final long TIME_DELAY_ADD_COUPON = 500;
        public static final String TAG = "LoveCouponTAG";
        public static final String DATA_SCOPE =
                "oauth2:https://www.googleapis.com/auth/userinfo.profile";
        public static final String DATA_PUBLIC_PROFILE = "public_profile";
        public static final String DATA_ANDROID = "android";
        public static final String DATA_ADMIN = "1";
        public static final int DATA_SPAN = 3;
        public static final String FIRST_BASE64 = "data:image/jpeg;base64,";
        public static final String DATA_FACEBOOK = "facebook";
        public static final String DATA_GOOGLE = "google";
        public static final int SUCCESS = 1;
        public static final int DATA_NOTIFICATION = 3;
        public static final int DATA_NOT_NOTIFICATION = -1;
        public static final String URL_IMAGE =
                "http://tophinhanhdep.net/wp-content/uploads/2015/12/anh-dep-mua-xuan-5.jpg";
        public static final String DATA_ID_SHOP = "0EW720whM80m8fe";
        public static final String DATA_DATE_PICKER = "DATA_DATE_PICKER";
        public static final String DATA_EMAIL_LOVE_COUPON = "info@lovecoupon.com";
        public static final String DATA_WEB_SITE = "http://www.lovecoupon.com";
        public static final String DATA_LINK_PHOTO = "http://188.166.199.25:4000/logo/love.jpg";
        public static final String DATA_PICK_LOGO = "LoveCoupon Pick Logo";
        public static final String DATA_HTTP = "http";
    }

    public static final class LocationAddress {
        public static final int SUCCESS_RESULT = 0;
        public static final int FAILURE_RESULT = 1;
        public static final String PACKAGE_NAME =
                "com.google.android.gms.location.sample.locationaddress";
        public static final String RECEIVER = PACKAGE_NAME + ".RECEIVER";
        public static final String RESULT_DATA_KEY = PACKAGE_NAME + ".RESULT_DATA_KEY";
        public static final String LOCATION_DATA_EXTRA = PACKAGE_NAME + ".LOCATION_DATA_EXTRA";
    }
}
