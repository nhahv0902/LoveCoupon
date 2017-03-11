package com.nhahv.lovecoupon.ui.customer.coupon;

import android.databinding.ObservableList;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.nhahv.lovecoupon.data.model.CouponCustomer;
import com.nhahv.lovecoupon.databinding.ItemCustomerCouponBinding;

/**
 * Created by Nhahv0902 on 3/6/2017.
 * <></>
 */
public class CouponAdapter extends RecyclerView.Adapter<CouponAdapter.CouponHolder> {
    private final ObservableList<CouponCustomer> mListCoupon;
    private final CouponViewModel mViewModel;
    private LayoutInflater mInflater;

    public CouponAdapter(@NonNull ObservableList<CouponCustomer> couponItems,
                         @NonNull CouponViewModel viewModel) {
        mListCoupon = couponItems;
        mViewModel = viewModel;
    }

    @Override
    public CouponHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (mInflater == null) mInflater = LayoutInflater.from(parent.getContext());
        ItemCustomerCouponBinding binding =
            ItemCustomerCouponBinding.inflate(mInflater, parent, false);
        binding.setViewModel(mViewModel);
        return new CouponHolder(binding);
    }

    @Override
    public void onBindViewHolder(CouponHolder holder, int position) {
        CouponCustomer coupon = mListCoupon.get(position);
        if (coupon != null) holder.bind(coupon);
    }

    @Override
    public int getItemCount() {
        return mListCoupon.size();
    }

    public class CouponHolder extends RecyclerView.ViewHolder {
        private ItemCustomerCouponBinding mBinding;

        public CouponHolder(ItemCustomerCouponBinding binding) {
            super(binding.getRoot());
            mBinding = binding;
        }

        private void bind(CouponCustomer coupon) {
            mBinding.setCoupon(coupon);
            mBinding.executePendingBindings();
        }
    }
}
