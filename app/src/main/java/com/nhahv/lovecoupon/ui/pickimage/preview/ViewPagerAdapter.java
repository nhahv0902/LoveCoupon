package com.nhahv.lovecoupon.ui.pickimage.preview;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import com.nhahv.lovecoupon.data.model.ImagePickerItem;
import java.util.List;

public class ViewPagerAdapter extends FragmentPagerAdapter {
    private List<ImagePickerItem> mListImage;

    public ViewPagerAdapter(FragmentManager fm, List<ImagePickerItem> frags) {
        super(fm);
        mListImage = frags;
    }

    @Override
    public Fragment getItem(int position) {
        return ImagePreviewerFragment.getInstance(mListImage.get(position).getPathImage());
    }

    @Override
    public int getCount() {
        return mListImage == null ? 0 : mListImage.size();
    }
}