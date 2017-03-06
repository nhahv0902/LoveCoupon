package com.nhahv.lovecoupon.ui.shop.coupon;

import android.databinding.ObservableList;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.nhahv.lovecoupon.data.model.CouponItem;
import com.nhahv.lovecoupon.databinding.ItemShopCouponBinding;

/**
 * Created by Nhahv0902 on 3/6/2017.
 * <></>
 */
public class CouponAdapter extends RecyclerView.Adapter<CouponAdapter.CouponHolder> {
    private final ObservableList<CouponItem> mListCoupon;
    private final CouponViewModel mViewModel;
    private LayoutInflater mInflater;

    public CouponAdapter(ObservableList<CouponItem> couponItems, CouponViewModel viewModel) {
        mListCoupon = couponItems;
        mViewModel = viewModel;
    }

    @Override
    public CouponHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (mInflater == null) mInflater = LayoutInflater.from(parent.getContext());
        ItemShopCouponBinding binding = ItemShopCouponBinding.inflate(mInflater, parent, false);
        return new CouponHolder(binding);
    }

    @Override
    public void onBindViewHolder(CouponHolder holder, int position) {
    }

    @Override
    public int getItemCount() {
        return mListCoupon == null ? 0 : mListCoupon.size();
    }

    public class CouponHolder extends RecyclerView.ViewHolder {
        public CouponHolder(ItemShopCouponBinding binding) {
            super(binding.getRoot());
        }
    }
}
