package com.nhahv.lovecoupon.ui.shop.history;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.nhahv.lovecoupon.ui.shop.history.usecoupon.UseCreateFragment;

import java.util.List;

public class ViewPagerAdapter extends FragmentPagerAdapter {
    private List<UseCreateFragment> mListFragment;
    private String[] mTitle;

    public ViewPagerAdapter(FragmentManager fm, List<UseCreateFragment> frags, String[] title) {
        super(fm);
        mListFragment = frags;
        mTitle = title;
    }

    @Override
    public UseCreateFragment getItem(int position) {
        return mListFragment.get(position);
    }

    @Override
    public int getCount() {
        return mListFragment == null ? 0 : mListFragment.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mTitle[position];
    }
}