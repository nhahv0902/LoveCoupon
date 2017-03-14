package com.nhahv.lovecoupon.ui.shop.notificationcreation;

import android.databinding.ObservableList;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.nhahv.lovecoupon.databinding.ItemNotificationImageBinding;
import com.nhahv.lovecoupon.ui.INotificationViewModel;

/**
 * Created by Nhahv0902 on 3/10/2017.
 * <></>
 */
public class NotificationCreationAdapter
    extends RecyclerView.Adapter<NotificationCreationAdapter.NotificationHolder> {
    private final INotificationViewModel mViewModel;
    private LayoutInflater mInflater;
    private final ObservableList<String> mListFolder;
    private boolean mShowDelete;

    public NotificationCreationAdapter(@NonNull INotificationViewModel viewModel,
                                       @NonNull ObservableList<String> listFolder,
                                       @NonNull boolean showDelete) {
        mListFolder = listFolder;
        mViewModel = viewModel;
        mShowDelete = showDelete;
    }

    @Override
    public NotificationHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (mInflater == null) mInflater = LayoutInflater.from(parent.getContext());
        ItemNotificationImageBinding binding =
            ItemNotificationImageBinding.inflate(mInflater, parent, false);
        binding.setViewModel(mViewModel);
        binding.setShowDelete(mShowDelete);
        return new NotificationHolder(binding);
    }

    @Override
    public void onBindViewHolder(NotificationHolder holder, int position) {
        String image = mListFolder.get(position);
        if (image != null) holder.bind(image, position);
    }

    @Override
    public int getItemCount() {
        return mListFolder.size();
    }

    public class NotificationHolder extends RecyclerView.ViewHolder {
        private ItemNotificationImageBinding mBinding;

        public NotificationHolder(ItemNotificationImageBinding binding) {
            super(binding.getRoot());
            mBinding = binding;
        }

        private void bind(String image, int position) {
            mBinding.setImage(image);
            mBinding.setPosition(position);
            mBinding.executePendingBindings();
        }
    }
}
