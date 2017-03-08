package com.nhahv.lovecoupon.ui.shop.history;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.nhahv.lovecoupon.R;
import com.nhahv.lovecoupon.databinding.FragmentHistoryBinding;
import com.wdullaer.materialdatetimepicker.date.DatePickerDialog;

import java.util.Calendar;

import static com.nhahv.lovecoupon.util.Constant.DataConstant.DATA_DATE_PICKER;

/**
 * A simple {@link Fragment} subclass.
 */
public class HistoryFragment extends Fragment implements IHistoryFragment {
    private FragmentHistoryBinding mBinding;
    private HistoryViewModel mViewModel;
    private MenuItem mMenuItem;
    private Calendar mDatePicker;

    public static HistoryFragment newInstance() {
        return new HistoryFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle state) {
        mBinding = FragmentHistoryBinding.inflate(inflater, container, false);
        mViewModel = new HistoryViewModel(getActivity(), this, getChildFragmentManager());
        mBinding.setViewModel(mViewModel);
        mDatePicker = Calendar.getInstance();
        setHasOptionsMenu(true);
        return mBinding.getRoot();
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.fragment_history, menu);
        mMenuItem = menu.findItem(R.id.action_date_time);
        updateMenuDatePicker(mViewModel.toStringDatePicker(mDatePicker));
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.action_date_time) {
            DatePickerDialog datePicker = DatePickerDialog.newInstance(
                mViewModel,
                mDatePicker.get(Calendar.YEAR),
                mDatePicker.get(Calendar.MONTH),
                mDatePicker.get(Calendar.DAY_OF_MONTH)
            );
            datePicker.show(getActivity().getFragmentManager(), DATA_DATE_PICKER);
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void updateMenuDatePicker(String dateTime) {
        if (mMenuItem != null && dateTime != null) mMenuItem.setTitle(dateTime);
    }

    @Override
    public void updateDatePicker(Calendar datePicker) {
        mDatePicker = datePicker;
    }
}
