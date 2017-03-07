package com.nhahv.lovecoupon.networking.api;

import com.google.gson.annotations.SerializedName;
import com.nhahv.lovecoupon.data.model.ProfileShop;

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
        Call<List<ProfileShop>> loginShop(@Body LoginShopBody body);
        @POST("/get_user_profile")
        Call<Integer> loginCustomer(@Body LoginCustomerBody body);
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
}
