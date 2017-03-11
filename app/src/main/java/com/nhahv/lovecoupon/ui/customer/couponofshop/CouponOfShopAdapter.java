package com.nhahv.lovecoupon.ui.customer.couponofshop;

import android.databinding.ObservableList;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.nhahv.lovecoupon.data.model.Coupon;
import com.nhahv.lovecoupon.databinding.ItemCouponOfShopBinding;

/**
 * Created by Nhahv0902 on 3/11/2017.
 * <></>
 */
public class CouponOfShopAdapter extends RecyclerView.Adapter<CouponOfShopAdapter.CouponHolder> {
    private LayoutInflater mInflater;
    private final ObservableList<Coupon> mListCoupon;
    private final CouponOfShopViewModel mViewModel;
    private final String mLogoLink;
    private final String mNameShop;

    public CouponOfShopAdapter(@NonNull CouponOfShopViewModel viewModel,
                               @NonNull ObservableList<Coupon> listCoupon,
                               @NonNull String logoLink,
                               @NonNull String name) {
        mViewModel = viewModel;
        mLogoLink = logoLink;
        mNameShop = name;
        mListCoupon = listCoupon;
    }

    @Override
    public CouponHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (mInflater == null) mInflater = LayoutInflater.from(parent.getContext());
        ItemCouponOfShopBinding binding = ItemCouponOfShopBinding.inflate(mInflater, parent, false);
        binding.setViewModel(mViewModel);
        return new CouponHolder(binding);
    }

    @Override
    public void onBindViewHolder(CouponHolder holder, int position) {
        Coupon coupon = mListCoupon.get(position);
        if (coupon != null) holder.bind(coupon, position);
    }

    @Override
    public int getItemCount() {
        return mListCoupon.size();
    }

    public class CouponHolder extends RecyclerView.ViewHolder {
        private final ItemCouponOfShopBinding mBinding;

        public CouponHolder(ItemCouponOfShopBinding binding) {
            super(binding.getRoot());
            mBinding = binding;
        }

        private void bind(Coupon coupon, int position) {
            mBinding.setLogo(mLogoLink);
            mBinding.setNameShop(mNameShop);
            mBinding.setCoupon(coupon);
            mBinding.setPosition(position);
            mBinding.executePendingBindings();
        }
    }
}
