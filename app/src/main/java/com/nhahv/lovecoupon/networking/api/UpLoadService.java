package com.nhahv.lovecoupon.networking.api;

import java.util.List;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

/**
 * Created by Nhahv0902 on 3/7/2017.
 * <></>
 */
public interface UpLoadService {
    @Multipart
    @POST("upload")
    Call<ResponseBody> upload(@Part("upload") RequestBody description,
                              @Part MultipartBody.Part file);
    @Multipart
    @POST("upload")
    Call<ResponseBody> upLoadMultiple(@Part("upload") RequestBody description,
                                      @Part List<MultipartBody.Part> file);
}
