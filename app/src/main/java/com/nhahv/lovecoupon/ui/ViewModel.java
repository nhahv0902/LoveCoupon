package com.nhahv.lovecoupon.ui;

import android.databinding.ObservableBoolean;
import android.databinding.ObservableField;
import android.support.v7.widget.RecyclerView;

/**
 * Created by Nhahv0902 on 3/8/2017.
 * <></>
 */
public interface ViewModel {
    void loadData();

    void loadError();

    ObservableBoolean getRefresh();

    void setRefresh(boolean isRefresh);

    ObservableField<RecyclerView.Adapter> getAdapter();
}
