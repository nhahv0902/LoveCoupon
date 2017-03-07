package com.nhahv.lovecoupon.ui.shop.history;

import android.content.Context;
import android.databinding.ObservableField;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.nhahv.lovecoupon.R;
import com.nhahv.lovecoupon.ui.shop.history.usecoupon.UseCreateFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Nhahv0902 on 3/7/2017.
 * <></>
 */
public class HistoryViewModel {
    private Context mContext;
    private IHistoryFragment mIHistoryFragment;
    private ObservableField<ViewPagerAdapter> mAdapter = new ObservableField<>();

    public HistoryViewModel(Context context, IHistoryFragment iHistoryFragment,
                            FragmentManager fragmentManager) {
        mContext = context;
        mIHistoryFragment = iHistoryFragment;
        List<Fragment> fragments = new ArrayList<>();
        fragments.add(UseCreateFragment.newInstance());
        fragments.add(UseCreateFragment.newInstance());
        mAdapter.set(new ViewPagerAdapter(fragmentManager, fragments,
            context.getResources().getStringArray(R.array.array_history)));
    }

    public ObservableField<ViewPagerAdapter> getAdapter() {
        return mAdapter;
    }

    public class ViewPagerAdapter extends FragmentPagerAdapter {
        private List<Fragment> mListFragment;
        private String[] mTitle;

        public ViewPagerAdapter(FragmentManager fm, List<Fragment> fragments, String[] title) {
            super(fm);
            mListFragment = fragments;
            mTitle = title;
        }

        @Override
        public Fragment getItem(int position) {
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
}
