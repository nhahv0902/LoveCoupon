package com.nhahv.lovecoupon.data.source;

/**
 * Created by Nhahv0902 on 3/7/2017.
 * <></>
 */
public interface Callback<T> {
    void onSuccess(T data);

    void onError();
}
