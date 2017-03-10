package com.nhahv.lovecoupon.data.source.remote.upload;

import android.support.annotation.NonNull;
import android.util.Log;

import com.nhahv.lovecoupon.data.source.Callback;
import com.nhahv.lovecoupon.networking.ServiceGenerator;
import com.nhahv.lovecoupon.networking.api.UpLoadService;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Response;

import static com.nhahv.lovecoupon.networking.ServiceGenerator.URL_UP_LOAD;

/**
 * Created by Nhahv0902 on 3/10/2017.
 * <></>
 */
public class UpLoadRemoteDataSource implements UpLoadDataSource {
    private static UpLoadRemoteDataSource sDataSource;
    private final UpLoadService mService;

    private UpLoadRemoteDataSource() {
        mService = ServiceGenerator.createUpload();
    }

    public static UpLoadRemoteDataSource getInstance() {
        if (sDataSource == null) sDataSource = new UpLoadRemoteDataSource();
        return sDataSource;
    }

    @NonNull
    private RequestBody createPartFromString(String description) {
        return RequestBody.create(MediaType.parse("multipart/form-data"), description);
    }

    @NonNull
    private MultipartBody.Part prepareFilePart(String partName, String path) {
        final File file = new File(path);
        RequestBody requestFile = RequestBody.create(MediaType.parse("multipart/form-data"), file);
        return MultipartBody.Part.createFormData(partName, file.getName(), requestFile);
    }

    @Override
    public void upLoadImage(@NonNull String pathImage, @NonNull Callback<String> callback) {
        if (mService == null) return;
        final File file = new File(pathImage);
        MultipartBody.Part body = prepareFilePart("picture", pathImage);
        RequestBody description = createPartFromString("LoveCoupon");
        mService.upload(description, body).enqueue(new retrofit2.Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response == null) callback.onError();
                else {
                    Log.d("TAG", response.body().toString() + "");
                    Log.d("TAG", response.toString() + "");
                    String url = URL_UP_LOAD + "/upload/" + file.getName();
                    Log.d("upLoadImage", "image = " + url);
                    callback.onSuccess(url);
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                callback.onError();
            }
        });
    }

    @Override
    public void upLoadMultiple(@NonNull List<String> pathImages,
                               @NonNull Callback<List<String>> callback) {
        if (mService == null) return;
        List<MultipartBody.Part> parts = new ArrayList<>();
        List<String> urls = new ArrayList<>();
        int size = pathImages.size();
        for (int i = 0; i < size; i++) {
          /*  if (pathImages.get(i).equals(DATA_HTTP)) urls.add(pathImages.get(i));
            else {*/
            parts.add(prepareFilePart("LoveCoupon" + i, pathImages.get(i)));
            urls.add(URL_UP_LOAD + "/upload/" + new File(pathImages.get(i)).getName());
//            }
        }
        mService.upLoadMultiple(createPartFromString("LoveCoupon"), parts).enqueue(
            new retrofit2.Callback<ResponseBody>() {
                @Override
                public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                    if (response == null || response.body() == null) callback.onError();
                    else callback.onSuccess(urls);
                }

                @Override
                public void onFailure(Call<ResponseBody> call, Throwable t) {
                    callback.onError();
                }
            });
    }
}
