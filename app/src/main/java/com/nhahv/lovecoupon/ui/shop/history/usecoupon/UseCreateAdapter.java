package com.nhahv.lovecoupon.ui.shop.history.usecoupon;

import android.databinding.ObservableList;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.nhahv.lovecoupon.data.model.Coupon;
import com.nhahv.lovecoupon.databinding.ItemUseCreateBinding;

/**
 * Created by Nhahv0902 on 3/7/2017.
 * <></>
 */
public class UseCreateAdapter extends RecyclerView.Adapter<UseCreateAdapter.UseCreateHolder> {
    private LayoutInflater mInflater;
    private final ObservableList<Coupon> mListCoupon;
    private final UseCreateViewModel mViewModel;

    public UseCreateAdapter(@NonNull UseCreateViewModel viewModel,
                            @NonNull ObservableList<Coupon> listCoupon) {
        mViewModel = viewModel;
        mListCoupon = listCoupon;
    }

    @Override
    public UseCreateHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (mInflater == null) mInflater = LayoutInflater.from(parent.getContext());
        ItemUseCreateBinding binding = ItemUseCreateBinding.inflate(mInflater, parent, false);
        binding.setViewModel(mViewModel);
        return new UseCreateHolder(binding);
    }

    @Override
    public void onBindViewHolder(UseCreateHolder holder, int position) {
        Coupon coupon = mListCoupon.get(position);
        if (coupon != null) holder.bind(coupon);
    }

    @Override
    public int getItemCount() {
        return mListCoupon == null ? 0 : mListCoupon.size();
    }

    public class UseCreateHolder extends RecyclerView.ViewHolder {
        private ItemUseCreateBinding mBinding;

        public UseCreateHolder(ItemUseCreateBinding binding) {
            super(binding.getRoot());
            mBinding = binding;
        }

        private void bind(Coupon item) {
            mBinding.setCoupon(item);
            mBinding.executePendingBindings();
        }
    }
}
