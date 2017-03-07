package com.nhahv.lovecoupon.ui.shop.coupon;

import android.databinding.ObservableList;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.nhahv.lovecoupon.data.model.CouponTemplate;
import com.nhahv.lovecoupon.databinding.ItemShopCouponBinding;

import static com.nhahv.lovecoupon.util.Constant.DataConstant.URL_IMAGE;

/**
 * Created by Nhahv0902 on 3/6/2017.
 * <></>
 */
public class CouponAdapter extends RecyclerView.Adapter<CouponAdapter.CouponHolder> {
    private final ObservableList<CouponTemplate> mListCoupon;
    private final CouponViewModel mViewModel;
    private LayoutInflater mInflater;

    public CouponAdapter(ObservableList<CouponTemplate> couponItems, CouponViewModel viewModel) {
        mListCoupon = couponItems;
        mViewModel = viewModel;
    }

    public void update() {
        notifyDataSetChanged();
    }

    @Override
    public CouponHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (mInflater == null) mInflater = LayoutInflater.from(parent.getContext());
        ItemShopCouponBinding binding = ItemShopCouponBinding.inflate(mInflater, parent, false);
        return new CouponHolder(binding);
    }

    @Override
    public void onBindViewHolder(CouponHolder holder, int position) {
        CouponTemplate template = mListCoupon.get(position);
        if (template != null) holder.bind(template);
    }

    @Override
    public int getItemCount() {
        return mListCoupon == null ? 0 : mListCoupon.size();
    }

    public class CouponHolder extends RecyclerView.ViewHolder {
        private ItemShopCouponBinding mBinding;

        public CouponHolder(ItemShopCouponBinding binding) {
            super(binding.getRoot());
            mBinding = binding;
        }

        private void bind(CouponTemplate template) {
            mBinding.setTemplate(template);
            mBinding.setShopName("Coffee Ha Noi");
            mBinding.setImage(URL_IMAGE);
            mBinding.executePendingBindings();
        }
    }
}
