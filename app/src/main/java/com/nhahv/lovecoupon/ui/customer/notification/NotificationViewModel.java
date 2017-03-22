package com.nhahv.lovecoupon.ui.customer.notification;

import android.content.Context;
import android.databinding.BaseObservable;
import android.databinding.ObservableArrayList;
import android.databinding.ObservableBoolean;
import android.databinding.ObservableField;
import android.databinding.ObservableList;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import com.afollestad.materialdialogs.MaterialDialog;
import com.facebook.share.model.ShareLinkContent;
import com.facebook.share.widget.ShareDialog;
import com.nhahv.lovecoupon.R;
import com.nhahv.lovecoupon.data.model.LCProfile;
import com.nhahv.lovecoupon.data.model.Notification;
import com.nhahv.lovecoupon.data.model.NotificationCustomer;
import com.nhahv.lovecoupon.data.source.Callback;
import com.nhahv.lovecoupon.data.source.remote.notification.NotificationRepository;
import com.nhahv.lovecoupon.ui.INotificationViewModel;
import com.nhahv.lovecoupon.ui.ViewModel;
import com.nhahv.lovecoupon.util.ActivityUtil;
import com.nhahv.lovecoupon.util.SharePreferenceUtil;
import java.util.List;

import static com.nhahv.lovecoupon.util.Constant.DataConstant.DATA_LINK_PHOTO;
import static com.nhahv.lovecoupon.util.Constant.DataConstant.DATA_WEB_SITE;

/**
 * Created by Nhahv0902 on 3/6/2017.
 * <></>
 */
public class NotificationViewModel extends BaseObservable
        implements ViewModel, INotificationViewModel {
    private final String TAG = getClass().getSimpleName();
    private final Context mContext;
    private final Fragment mFragment;
    private final ObservableBoolean mRefresh = new ObservableBoolean(false);
    private final ObservableField<RecyclerView.Adapter> mAdapter = new ObservableField<>();
    private final NotificationType mType;
    private final NotificationRepository mRepository;
    private final SharePreferenceUtil mPreference;
    private final LCProfile mProfile;
    private ObservableList<NotificationCustomer> mListNotification = new ObservableArrayList<>();

    public NotificationViewModel(@NonNull Context context, @NonNull Fragment fragment,
            @NonNull NotificationType type) {
        mContext = context;
        mFragment = fragment;
        mType = type;
        mPreference = SharePreferenceUtil.getInstance(context);
        mProfile = mPreference.profileCustomer();
        mRepository = NotificationRepository.getInstance();
        mAdapter.set(new NotificationAdapter(this, mListNotification));
        loadData();
    }

    @Override
    public void loadData() {
        if (mRepository == null) return;
        switch (mType) {
            case NOTIFICATION:
                mRepository.getNotificationCustomer(mProfile.getId(),
                        new Callback<List<NotificationCustomer>>() {
                            @Override
                            public void onSuccess(List<NotificationCustomer> data) {
                                mListNotification.clear();
                                mListNotification.addAll(data);
                                mAdapter.get().notifyDataSetChanged();
                                setRefresh(false);
                            }

                            @Override
                            public void onError() {
                                loadError();
                            }
                        });
                break;
            case NOTIFICATION_OTHER:
                mRepository.getOtherNotificationCustomer(mProfile.getId(), mPreference.getCity(),
                        new Callback<List<NotificationCustomer>>() {
                            @Override
                            public void onSuccess(List<NotificationCustomer> data) {
                                mListNotification.clear();
                                mListNotification.addAll(data);
                                mAdapter.get().notifyDataSetChanged();
                                setRefresh(false);
                            }

                            @Override
                            public void onError() {
                                loadError();
                            }
                        });
                break;
            default:
                break;
        }
    }

    @Override
    public void loadError() {
        ActivityUtil.showMsg(mContext, R.string.msg_load_data_error);
    }

    @Override
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
    public void clickMore(View view, Notification notification, int position) {
        new MaterialDialog.Builder(mContext).content(R.string.title_delete_notification)
                .positiveText(R.string.agree)
                .positiveColor(ContextCompat.getColor(mContext, R.color.color_blue_600))
                .onPositive((dialog, which) -> {
                    mListNotification.remove(position);
                    mAdapter.get().notifyDataSetChanged();
                })
                .show();
    }

    @Override
    public void clickFavorite(Notification notification) {
    }

    @Override
    public void clickShare(Notification notification) {
        String logo = ((NotificationCustomer) notification).getLogo() != null
                ? ((NotificationCustomer) notification).getLogo() : DATA_LINK_PHOTO;
        Uri uriLink =
                Uri.parse(notification.getLink() != null ? notification.getLink() : DATA_WEB_SITE);
        ShareLinkContent content = new ShareLinkContent.Builder().setContentUrl(uriLink)
                .setContentTitle(notification.getTitle())
                .setContentDescription(notification.getContent())
                .setImageUrl(Uri.parse(logo))
                .build();
        ShareDialog shareDialog = new ShareDialog(mFragment);
        shareDialog.show(content);
    }

    @Override
    public void clickDelete(int position) {
    }
}
