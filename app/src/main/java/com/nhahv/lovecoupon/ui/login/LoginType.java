package com.nhahv.lovecoupon.ui.login;

/**
 * Created by Nhahv0902 on 3/6/2017.
 * <></>
 */
public enum LoginType {
    NORMAL("normal"), FACEBOOK("facebook"), GOOGLE("google");
    private String mType;

    LoginType(String type) {
        mType = type;
    }

    public static LoginType toLoginType(String type) {
        try {
            return valueOf(type);
        } catch (Exception ex) {
            return NORMAL;
        }
    }

    public String getType() {
        return mType;
    }
}
