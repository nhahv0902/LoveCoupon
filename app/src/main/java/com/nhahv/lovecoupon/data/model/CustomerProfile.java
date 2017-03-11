package com.nhahv.lovecoupon.data.model;

import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.support.annotation.NonNull;

import com.nhahv.lovecoupon.BR;

/**
 * Created by Nhahv0902 on 3/8/2017.
 * <></>
 */
public class CustomerProfile extends BaseObservable {
    private String mId;
    private String mName;
    private String mAvatar;

    public CustomerProfile(@NonNull String id, @NonNull String name, @NonNull String avatar) {
        mId = id;
        mName = name;
        mAvatar = avatar;
    }

    public void setId(String id) {
        mId = id;
        notifyPropertyChanged(BR.id);
    }

    public void setName(String name) {
        mName = name;
        notifyPropertyChanged(BR.name);
    }

    public void setAvatar(String avatar) {
        mAvatar = avatar;
        notifyPropertyChanged(BR.avatar);
    }

    @Bindable
    public String getId() {
        return mId;
    }

    @Bindable
    public String getName() {
        return mName;
    }

    @Bindable
    public String getAvatar() {
        return mAvatar;
    }
}
