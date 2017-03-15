package com.nhahv.lovecoupon.networking.api;

import com.google.gson.annotations.SerializedName;
import com.nhahv.lovecoupon.data.model.ShopProfile;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

import static com.nhahv.lovecoupon.util.Constant.DataConstant.DATA_ANDROID;

/**
 * Created by Nhahv0902 on 3/7/2017.
 * <></>
 */
public interface AuthorizationService {
    interface LoginService {
        @POST("/get_company_profile")
        Call<List<ShopProfile>> loginShop(@Body LoginShopBody body);
        @POST("/get_user_profile")
        Call<Integer> loginCustomer(@Body LoginCustomerBody body);
        @POST("/sendPassword")
        Call<String> resetPassword(@Body ResetPasswordBody value);
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
            mUserName = email;
            mPassword = password;
        }

        public void setSocial(String id, String social, String token) {
            mUserId = id;
            mSocial = social;
            mToken = token;
        }
    }

    class LoginCustomerBody {
        @SerializedName("user_id")
        private String mUserId;
        @SerializedName("password")
        private String mPassword;
        @SerializedName("device_os")
        private String mDeviceOS;
        @SerializedName("device_token")
        private String mToken;

        public LoginCustomerBody(String userId, String password, String token) {
            mUserId = userId;
            mPassword = password;
            mDeviceOS = DATA_ANDROID;
            mToken = token;
        }
    }

    class ResetPasswordBody {
        @SerializedName("value")
        private String mEmail;

        public ResetPasswordBody(String email) {
            mEmail = email;
        }
    }
}
