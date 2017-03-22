package com.nhahv.lovecoupon.data.source.remote.upload;

import android.support.annotation.NonNull;
import com.nhahv.lovecoupon.data.source.Callback;
import java.util.List;

/**
 * Created by Nhahv0902 on 3/10/2017.
 * <></>
 */
public class UpLoadRepository implements UpLoadDataSource {
    private static UpLoadRepository sRepository;
    private final UpLoadRemoteDataSource mDataSource;

    private UpLoadRepository() {
        mDataSource = UpLoadRemoteDataSource.getInstance();
    }

    public static UpLoadRepository getInstance() {
        if (sRepository == null) sRepository = new UpLoadRepository();
        return sRepository;
    }

    @Override
    public void upLoadImage(@NonNull String pathImage, @NonNull Callback<String> callback) {
        if (mDataSource == null) return;
        mDataSource.upLoadImage(pathImage, new Callback<String>() {
            @Override
            public void onSuccess(String data) {
                callback.onSuccess(data);
            }

            @Override
            public void onError() {
                callback.onError();
            }
        });
    }

    @Override
    public void upLoadMultiple(@NonNull List<String> pathImages,
            @NonNull Callback<List<String>> callback) {
        if (mDataSource == null) return;
        mDataSource.upLoadMultiple(pathImages, new Callback<List<String>>() {
            @Override
            public void onSuccess(List<String> data) {
                callback.onSuccess(data);
            }

            @Override
            public void onError() {
                callback.onError();
            }
        });
    }
}
