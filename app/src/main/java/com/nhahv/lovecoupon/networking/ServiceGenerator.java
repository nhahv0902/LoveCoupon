package com.nhahv.lovecoupon.networking;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Nhahv0902 on 3/7/2017.
 * <></>
 */
public class ServiceGenerator {
    private static Retrofit sRetrofit;
    private static Retrofit sRetrofitUpload;
    private static final String BASE_URL = "http://lovecoupon.com:4000";
    private static final String URL_UP_LOAD = "http://188.166.196.171:3001";
    private static final String URL_GET_CITY = "http://freegeoip.net";
    private static final String URL_GET_CITY2 = "http://ip-api.com";

    private ServiceGenerator() {
        sRetrofit = new Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(new OkHttpClient())
            .build();
        sRetrofitUpload = new Retrofit.Builder()
            .baseUrl(URL_UP_LOAD)
            .addConverterFactory(GsonConverterFactory.create())
            .client(new OkHttpClient())
            .build();
    }

    public static <S> S createService(Class<S> serviceClass) {
        if (sRetrofit == null) new ServiceGenerator();
        return sRetrofit.create(serviceClass);
    }

    /*public static <T> T createUpload(Class<T> serviceClass) {
    }*/
}
