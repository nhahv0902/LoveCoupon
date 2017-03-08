package com.nhahv.lovecoupon.ui.share;

import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.facebook.share.model.ShareLinkContent;
import com.facebook.share.widget.ShareDialog;
import com.nhahv.lovecoupon.R;
import com.nhahv.lovecoupon.databinding.FragmentShareBinding;

import static com.nhahv.lovecoupon.util.Constant.DataConstant.DATA_LINK_PHOTO;
import static com.nhahv.lovecoupon.util.Constant.DataConstant.DATA_WEB_SITE;

/**
 * A simple {@link Fragment} subclass.
 */
public class ShareFragment extends Fragment implements IShareFragment {
    private FragmentShareBinding mBinding;

    public static Fragment newInstance() {
        return new ShareFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mBinding = FragmentShareBinding.inflate(inflater, container, false);
        mBinding.setViewModel(new ShareViewModel(getActivity(), this));
        return mBinding.getRoot();
    }

    @Override
    public void shareFacebook() {
        ShareLinkContent content = new ShareLinkContent.Builder()
            .setContentUrl(Uri.parse(DATA_WEB_SITE))
            .setImageUrl(Uri.parse(DATA_LINK_PHOTO))
            .setContentTitle(getString(R.string.msg_share_facebook))
            .setContentDescription(getString(R.string.msg_description_share_facebook))
            .build();
        ShareDialog shareDialog = new ShareDialog(this);
        shareDialog.show(content);
    }
}
