package com.nhahv.lovecoupon.ui.pickimage.preview;

import android.content.Context;
import android.databinding.BaseObservable;
import android.databinding.ObservableField;
import android.databinding.ObservableInt;
import android.support.v4.app.FragmentManager;
import com.nhahv.lovecoupon.data.model.ImagePickerItem;
import java.util.List;

/**
 * Created by Nhahv0902 on 3/9/2017.
 * <></>
 */
public class ImagePreviewerViewModel extends BaseObservable {
    private final Context mContext;
    private final ObservableField<ViewPagerAdapter> mAdapter = new ObservableField<>();
    private final ObservableInt mPosition = new ObservableInt();

    public ImagePreviewerViewModel(Context context) {
        mContext = context;
    }

    public void update(FragmentManager manager, List<ImagePickerItem> images, int position) {
        mPosition.set(position);
        mAdapter.set(new ViewPagerAdapter(manager, images));
    }

    public ObservableInt getPosition() {
        return mPosition;
    }

    public ObservableField<ViewPagerAdapter> getAdapter() {
        return mAdapter;
    }
}
