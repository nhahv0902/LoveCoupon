package com.nhahv.lovecoupon.ui.shop.coupon;

import android.databinding.ObservableList;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.nhahv.lovecoupon.data.model.CouponTemplate;
import com.nhahv.lovecoupon.data.model.ProfileShop;
import com.nhahv.lovecoupon.databinding.ItemShopCouponBinding;

/**
 * Created by Nhahv0902 on 3/6/2017.
 * <></>
 */
public class CouponAdapter extends RecyclerView.Adapter<CouponAdapter.CouponHolder> {
    private final ObservableList<CouponTemplate> mListCoupon;
    private final CouponViewModel mViewModel;
    private final ProfileShop mProfile;
    private LayoutInflater mInflater;

    public CouponAdapter(@NonNull ObservableList<CouponTemplate> couponItems,
                         @NonNull CouponViewModel viewModel,
                         @NonNull ProfileShop profile) {
        mListCoupon = couponItems;
        mProfile = profile;
        mViewModel = viewModel;
    }

    @Override
    public CouponHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (mInflater == null) mInflater = LayoutInflater.from(parent.getContext());
        ItemShopCouponBinding binding = ItemShopCouponBinding.inflate(mInflater, parent, false);
        binding.setViewModel(mViewModel);
        return new CouponHolder(binding);
    }

    @Override
    public void onBindViewHolder(CouponHolder holder, int position) {
        CouponTemplate template = mListCoupon.get(position);
        if (template != null) holder.bind(template);
    }

    @Override
    public int getItemCount() {
        return mListCoupon.size();
    }

    public class CouponHolder extends RecyclerView.ViewHolder {
        private ItemShopCouponBinding mBinding;

        public CouponHolder(ItemShopCouponBinding binding) {
            super(binding.getRoot());
            mBinding = binding;
        }

        private void bind(CouponTemplate template) {
            mBinding.setTemplate(template);
            mBinding.setShopName(mProfile.getName());
            mBinding.setImage(mProfile.getLogoLink());
            mBinding.executePendingBindings();
        }
    }
}
