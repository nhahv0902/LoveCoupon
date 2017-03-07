package com.nhahv.lovecoupon.networking.api;

import com.google.gson.annotations.SerializedName;
import com.nhahv.lovecoupon.data.model.ShopProfile;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * Created by Nhahv0902 on 3/7/2017.
 * <></>
 */
public interface AuthorizationService {
    interface LoginService {
        @POST("/get_company_profile")
        Call<List<ShopProfile>> loginShop(@Body LoginShopBody body);
        @POST("/get_user_profile")
        Call<Integer> loginSocial(@Body LoginCustomerBody body);
    }

    class LoginShopBody {
        @SerializedName("user_id")
        private String mUserId;
        @SerializedName("user_name")
        private String mUserName;
        @SerializedName("social")
        private String mSocial;
        @SerializedName("access_token")
        private String mToken;
        @SerializedName("password")
        private String mPassword;

        public void setNormal(String email, String password) {
            mUserId = email;
            mPassword = password;
        }

        public void setSocial(String id, String social, String token) {
            mUserId = id;
            mSocial = social;
            mToken = token;
        }
    }

    class LoginCustomerBody {
        /*
        * profile of shop  user_id;social,access_token,user_name,password
        * profile of customer  user_id,  device_os, device_token, password
        * */
        @SerializedName("user_id")
        private String mUserId;
        @SerializedName("user_name")
        private String mUserName;
        @SerializedName("social")
        private String mSocial;
        @SerializedName("access_token")
        private String mToken;
        @SerializedName("password")
        private String mPassword;
        @SerializedName("device_os")
        private String mDeviceOS;
        @SerializedName("device_token")
        private String mDeviceToken;

        public void setUserId(String userId) {
            mUserId = userId;
        }

        public void setUserName(String userName) {
            mUserName = userName;
        }

        public void setSocial(String social) {
            mSocial = social;
        }

        public void setToken(String token) {
            mToken = token;
        }

        public void setPassword(String password) {
            mPassword = password;
        }

        public void setDeviceOS(String deviceOS) {
            mDeviceOS = deviceOS;
        }

        public void setDeviceToken(String deviceToken) {
            mDeviceToken = deviceToken;
        }
    }
}
