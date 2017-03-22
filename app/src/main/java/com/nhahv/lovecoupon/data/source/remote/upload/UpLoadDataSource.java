package com.nhahv.lovecoupon.data.source.remote.upload;

import android.support.annotation.NonNull;
import com.nhahv.lovecoupon.data.source.Callback;
import java.util.List;

/**
 * Created by Nhahv0902 on 3/10/2017.
 * <></>
 */
public interface UpLoadDataSource {
    void upLoadImage(@NonNull String pathImage, @NonNull Callback<String> callback);

    void upLoadMultiple(@NonNull List<String> pathImages, @NonNull Callback<List<String>> callback);
}
