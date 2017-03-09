package com.nhahv.lovecoupon.ui.pickimage.image;

import android.databinding.ObservableList;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.nhahv.lovecoupon.data.model.ImagePickerItem;
import com.nhahv.lovecoupon.databinding.ItemImagePickerBinding;

/**
 * Created by Nhahv0902 on 3/9/2017.
 * <></>
 */
public class ImagePickerAdapter extends RecyclerView.Adapter<ImagePickerAdapter.ImagePickerHolder> {
    private final ImagePickerViewModel mViewModel;
    private LayoutInflater mInflater;
    private final ObservableList<ImagePickerItem> mListFolder;

    public ImagePickerAdapter(ImagePickerViewModel viewModel,
                              ObservableList<ImagePickerItem> listFolder) {
        mListFolder = listFolder;
        mViewModel = viewModel;
    }

    @Override
    public ImagePickerHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (mInflater == null) mInflater = LayoutInflater.from(parent.getContext());
        ItemImagePickerBinding binding = ItemImagePickerBinding.inflate(mInflater, parent, false);
        binding.setViewModel(mViewModel);
        return new ImagePickerHolder(binding);
    }

    @Override
    public void onBindViewHolder(ImagePickerHolder holder, int position) {
        ImagePickerItem image = mListFolder.get(position);
        if (image != null) holder.bind(image, position);
    }

    @Override
    public int getItemCount() {
        return mListFolder == null ? 0 : mListFolder.size();
    }

    public class ImagePickerHolder extends RecyclerView.ViewHolder {
        private final ItemImagePickerBinding mBinding;

        public ImagePickerHolder(ItemImagePickerBinding binding) {
            super(binding.getRoot());
            mBinding = binding;
        }

        private void bind(ImagePickerItem image, int position) {
            mBinding.setImagePicker(image);
            mBinding.setPosition(position);
            mBinding.executePendingBindings();
        }
    }
}
