package com.nhahv.lovecoupon.ui.shop.history;

import android.content.Context;
import android.databinding.BaseObservable;
import android.databinding.ObservableField;
import android.support.v4.app.FragmentManager;
import com.nhahv.lovecoupon.R;
import com.nhahv.lovecoupon.ui.shop.history.usecoupon.UseCreateFragment;
import com.wdullaer.materialdatetimepicker.date.DatePickerDialog;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

/**
 * Created by Nhahv0902 on 3/7/2017.
 * <></>
 */
public class HistoryViewModel extends BaseObservable implements DatePickerDialog.OnDateSetListener {
    private final String TAG = getClass().getSimpleName();
    private final Context mContext;
    private final IHistoryFragment mIHistoryFragment;
    private final ObservableField<ViewPagerAdapter> mAdapter = new ObservableField<>();
    private List<UseCreateFragment> mFragments = new ArrayList<>();

    public HistoryViewModel(Context context, IHistoryFragment iHistoryFragment,
            FragmentManager fragmentManager) {
        mContext = context;
        mIHistoryFragment = iHistoryFragment;
        mFragments.add(UseCreateFragment.newInstance(UseCreateType.CREATE));
        mFragments.add(UseCreateFragment.newInstance(UseCreateType.USE));
        mAdapter.set(new ViewPagerAdapter(fragmentManager, mFragments,
                context.getResources().getStringArray(R.array.array_history)));
    }

    @Override
    public void onDateSet(DatePickerDialog view, int year, int monthOfYear, int dayOfMonth) {
        Calendar now = Calendar.getInstance();
        now.set(year, monthOfYear, dayOfMonth);
        now.set(Calendar.HOUR_OF_DAY, 0);
        now.set(Calendar.MINUTE, 0);
        now.set(Calendar.SECOND, 0);
        now.set(Calendar.MILLISECOND, 0);
        long utc1 = now.getTimeInMillis();
        mIHistoryFragment.updateMenuDatePicker(toStringDatePicker(now));
        mIHistoryFragment.updateDatePicker(now);
        for (UseCreateFragment fragment : mFragments) {
            fragment.onDateChange(utc1);
        }
    }

    public ObservableField<ViewPagerAdapter> getAdapter() {
        return mAdapter;
    }

    public String toStringDatePicker(Calendar calendar) {
        SimpleDateFormat fmt = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
        return fmt.format(calendar.getTime());
    }
}
