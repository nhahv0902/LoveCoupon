package com.nhahv.lovecoupon.ui.shop.notification;

import android.content.Context;
import android.databinding.ObservableArrayList;
import android.databinding.ObservableBoolean;
import android.databinding.ObservableField;
import android.databinding.ObservableList;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatDelegate;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.facebook.share.model.ShareLinkContent;
import com.facebook.share.widget.ShareDialog;
import com.nhahv.lovecoupon.R;
import com.nhahv.lovecoupon.data.model.Notification;
import com.nhahv.lovecoupon.data.model.ShopProfile;
import com.nhahv.lovecoupon.data.source.Callback;
import com.nhahv.lovecoupon.data.source.remote.notification.NotificationRepository;
import com.nhahv.lovecoupon.ui.INotificationViewModel;
import com.nhahv.lovecoupon.ui.ViewModel;
import com.nhahv.lovecoupon.util.ActivityUtil;
import com.nhahv.lovecoupon.util.SharePreferenceUtil;

import java.util.List;

import static com.nhahv.lovecoupon.util.Constant.DataConstant.DATA_ID_SHOP;
import static com.nhahv.lovecoupon.util.Constant.DataConstant.DATA_LINK_PHOTO;
import static com.nhahv.lovecoupon.util.Constant.DataConstant.DATA_WEB_SITE;

/**
 * Created by Nhahv0902 on 3/6/2017.
 * <></>
 */
public class NotificationViewModel implements ViewModel, INotificationViewModel {
    private final Context mContext;
    private final Fragment mFragment;
    private final IShopNotification mIShopNotification;
    private final NotificationRepository mRepository;
    private final ObservableList<Notification> mListNotification = new ObservableArrayList<>();
    private final ShopProfile mProfile;
    static {
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true);
    }
    public NotificationViewModel(@NonNull Context context, @NonNull Fragment fragment,
                                 @NonNull IShopNotification iShopNotification) {
        mContext = context;
        mFragment = fragment;
        mIShopNotification = iShopNotification;
        mProfile = SharePreferenceUtil.getInstance(mContext).profileShop();
        mRepository = NotificationRepository.getInstance();
        mAdapter.set(new NotificationAdapter(this, mListNotification, mProfile));
        loadData();
    }

    @Override
    public void loadData() {
        if (mRepository == null) return;
        mRepository.getNotificationShop(DATA_ID_SHOP, new Callback<List<Notification>>() {
            @Override
            public void onSuccess(List<Notification> data) {
                mListNotification.clear();
                mListNotification.addAll(data);
                mAdapter.get().notifyDataSetChanged();
                setRefresh(false);
            }

            @Override
            public void onError() {
                loadError();
                setRefresh(false);
            }
        });
    }

    @Override
    public void clickFavorite(Notification notification) {
    }

    @Override
    public void clickShare(Notification notification) {
        Uri uriLink =
            Uri.parse(notification.getLink() != null ? notification.getLink() : DATA_WEB_SITE);
        ShareLinkContent content = new ShareLinkContent.Builder()
            .setContentUrl(uriLink)
            .setContentTitle(notification.getTitle())
            .setContentDescription(notification.getContent())
            .setImageUrl(Uri.parse(
                mProfile.getLogoLink() != null ? mProfile.getLogoLink() : DATA_LINK_PHOTO))
            .build();
        ShareDialog shareDialog = new ShareDialog(mFragment);
        shareDialog.show(content);
    }

    @Override
    public void clickMore(View view, Notification notification, int position) {
        if (mIShopNotification == null) return;
        PopupMenu popupMenu = new PopupMenu(mContext, view);
        popupMenu.getMenuInflater().inflate(R.menu.notification_more, popupMenu.getMenu());
        popupMenu.setOnMenuItemClickListener(item -> {
            switch (item.getItemId()) {
                case R.id.action_edit_notification:
                    mIShopNotification.editNotification(notification);
                    break;
                case R.id.action_delete_notification:
                    mIShopNotification.showDialogDeleteNotification(notification);
                    break;
                default:
                    break;
            }
            return false;
        });
        popupMenu.show();
    }

    @Override
    public void loadError() {
        ActivityUtil.showMsg(mContext, R.string.msg_load_data_error);
    }

    public ObservableBoolean getRefresh() {
        return mRefresh;
    }

    @Override
    public void setRefresh(boolean isRefresh) {
        mRefresh.set(isRefresh);
    }

    @Override
    public ObservableField<RecyclerView.Adapter> getAdapter() {
        return mAdapter;
    }

    @Override
    public void preview(List<String> images, int position) {
    }

    @Override
    public void clickDelete(int position) {
    }

    public void deleteNotification(Notification notification) {
        if (mRepository == null || mProfile == null) return;
        mRepository.deleteNotification(mProfile.getToken(), notification,
            new Callback<Boolean>() {
                @Override
                public void onSuccess(Boolean data) {
                    loadData();
                    ActivityUtil.showMsg(mContext, R.string.msg_delete_success);
                }

                @Override
                public void onError() {
                    ActivityUtil.showMsg(mContext, R.string.msg_delete_error);
                }
            });
    }
}
