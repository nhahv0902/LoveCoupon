package com.nhahv.lovecoupon.ui.pickimage.folder;

import android.content.Context;
import android.databinding.BaseObservable;
import android.databinding.ObservableArrayList;
import android.databinding.ObservableBoolean;
import android.databinding.ObservableField;
import android.databinding.ObservableList;
import android.support.v7.widget.RecyclerView;

import com.nhahv.lovecoupon.R;
import com.nhahv.lovecoupon.data.model.ImageFolder;
import com.nhahv.lovecoupon.data.model.ImagePickerItem;
import com.nhahv.lovecoupon.ui.ViewModel;
import com.nhahv.lovecoupon.ui.pickimage.image.ImagePickerActivity;
import com.nhahv.lovecoupon.util.ActivityUtil;
import com.nhahv.lovecoupon.util.LoaderImageUtil;

import java.util.List;

/**
 * Created by Nhahv0902 on 3/9/2017.
 * <></>
 */
public class ImageFolderViewModel extends BaseObservable implements ViewModel {
    private final Context mContext;
    private final ObservableList<ImageFolder> mListFolder = new ObservableArrayList<>();

    public ImageFolderViewModel(Context context) {
        mContext = context;
        mAdapter.set(new ImageFolderAdapter(this, mListFolder));
        loadData();
    }

    public void clickStartImagePicker(ImageFolder folder) {
        mContext.startActivity(ImagePickerActivity.getImagePickerIntent(mContext, folder.getListImage()));
    }

    @Override
    public void loadData() {
        LoaderImageUtil.getInstance(mContext).getImageFolders(new LoaderImageUtil.LoaderCallback() {
            @Override
            public void onSuccess(List<ImageFolder> imageFolders) {
                mListFolder.clear();
                ImageFolder all = new ImageFolder(getString(R.string.title_all_albums));
                for (ImageFolder folder : imageFolders) {
                    List<ImagePickerItem> imagePickerItems = folder.getListImage();
                    for (ImagePickerItem item : imagePickerItems) {
                        all.getListImage().add(new ImagePickerItem(item.getPathImage()));
                    }
                }
                mListFolder.add(all);
                mListFolder.addAll(imageFolders);
                mAdapter.get().notifyDataSetChanged();
                setRefresh(false);
            }

            @Override
            public void onError() {
                loadError();
                setRefresh(false);
            }
        });
    }

    @Override
    public void loadError() {
        ActivityUtil.showMsg(mContext, R.string.msg_load_image_error);
    }

    @Override
    public ObservableBoolean getRefresh() {
        return mRefresh;
    }

    @Override
    public void setRefresh(boolean isRefresh) {
        mRefresh.set(false);
    }

    @Override
    public ObservableField<RecyclerView.Adapter> getAdapter() {
        return mAdapter;
    }

    private String getString(int res) {
        return mContext.getString(res);
    }
}
