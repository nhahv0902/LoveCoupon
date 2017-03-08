package com.nhahv.lovecoupon.ui.pickimage.folder;

import android.databinding.ObservableList;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.nhahv.lovecoupon.data.model.ImageFolder;
import com.nhahv.lovecoupon.databinding.ItemImageFolderBinding;

/**
 * Created by Nhahv0902 on 3/9/2017.
 * <></>
 */
public class ImageFolderAdapter extends RecyclerView.Adapter<ImageFolderAdapter.FolderHolder> {
    private final ImageFolderViewModel mViewModel;
    private LayoutInflater mInflater;
    private final ObservableList<ImageFolder> mListFolder;

    public ImageFolderAdapter(ImageFolderViewModel viewModel,
                              ObservableList<ImageFolder> listFolder) {
        mListFolder = listFolder;
        mViewModel = viewModel;
    }

    @Override
    public FolderHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (mInflater == null) mInflater = LayoutInflater.from(parent.getContext());
        ItemImageFolderBinding binding = ItemImageFolderBinding.inflate(mInflater, parent, false);
        binding.setViewModel(mViewModel);
        return new FolderHolder(binding);
    }

    @Override
    public void onBindViewHolder(FolderHolder holder, int position) {
        ImageFolder folder = mListFolder.get(position);
        if (folder != null) holder.bind(folder);
    }

    @Override
    public int getItemCount() {
        return mListFolder == null ? 0 : mListFolder.size();
    }

    public class FolderHolder extends RecyclerView.ViewHolder {
        private final ItemImageFolderBinding mBinding;

        public FolderHolder(ItemImageFolderBinding binding) {
            super(binding.getRoot());
            mBinding = binding;
        }

        private void bind(ImageFolder folder) {
            mBinding.setFolder(folder);
            mBinding.executePendingBindings();
        }
    }
}
