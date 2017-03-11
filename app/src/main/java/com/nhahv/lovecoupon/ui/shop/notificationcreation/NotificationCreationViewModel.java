package com.nhahv.lovecoupon.ui.shop.notificationcreation;

import android.content.Context;
import android.databinding.BaseObservable;
import android.databinding.ObservableArrayList;
import android.databinding.ObservableField;
import android.databinding.ObservableList;
import android.support.annotation.NonNull;
import android.util.Log;

import com.nhahv.lovecoupon.R;
import com.nhahv.lovecoupon.data.model.ImagePickerItem;
import com.nhahv.lovecoupon.data.model.Notification;
import com.nhahv.lovecoupon.data.model.ShopProfile;
import com.nhahv.lovecoupon.data.source.Callback;
import com.nhahv.lovecoupon.data.source.remote.notification.NotificationRepository;
import com.nhahv.lovecoupon.data.source.remote.upload.UpLoadRepository;
import com.nhahv.lovecoupon.ui.INotificationViewModel;
import com.nhahv.lovecoupon.util.ActivityUtil;
import com.nhahv.lovecoupon.util.SharePreferenceUtil;
import com.wdullaer.materialdatetimepicker.date.DatePickerDialog;

import java.util.Calendar;
import java.util.List;

/**
 * Created by Nhahv0902 on 3/10/2017.
 * <></>
 */
public class NotificationCreationViewModel extends BaseObservable
    implements DatePickerDialog.OnDateSetListener, INotificationViewModel {
    private final String TAG = getClass().getSimpleName();
    private final Context mContext;
    private final ActionNotificationType mType;
    private final Notification mNotification;
    private final NotificationCreationHandler mHandler;
    private final ObservableList<String> mListImage = new ObservableArrayList<>();
    private final ObservableField<NotificationCreationAdapter> mAdapter = new ObservableField<>();
    private final UpLoadRepository mUpLoadRepository;
    private final NotificationRepository mRepository;
    private final ShopProfile mProfile;

    public NotificationCreationViewModel(@NonNull Context context,
                                         @NonNull NotificationCreationHandler handler,
                                         @NonNull Notification notification,
                                         @NonNull ActionNotificationType type) {
        mContext = context;
        mHandler = handler;
        mNotification = notification;
        mProfile = SharePreferenceUtil.getInstance(context).profileShop();
        mUpLoadRepository = UpLoadRepository.getInstance();
        mRepository = NotificationRepository.getInstance();
        mType = type;
        mAdapter.set(new NotificationCreationAdapter(this, mListImage, true));
    }

    public void clickPickDate() {
        mHandler.openClickDate();
    }

    public void clickPickImage() {
        mHandler.openPickImage();
    }

    public void clickCreateNotification() {
        if (!ActivityUtil.isNetworkConnected(mContext)
            || mRepository == null
            || mUpLoadRepository == null) {
            return;
        }
        new NotificationValidation(mNotification).validation(new NotificationValidation.Callback() {
            @Override
            public void onSuccess() {
                if (mListImage.size() > 0) upLoadImage();
                else createNotification();
            }

            @Override
            public void onError(NotificationValidation.Error error) {
                switch (error) {
                    case TITLE:
                        ActivityUtil.showMsg(mContext, R.string.msg_title_error);
                        break;
                    case CONTENT:
                        ActivityUtil.showMsg(mContext, R.string.msg_content_error);
                        break;
                    default:
                        break;
                }
            }
        });
    }

    private void upLoadImage() {
        int size = mListImage.size();
        for (int i = 0; i < size; i++) {
           /* if (!mListImage.get(i).contains(DATA_HTTP)) {
                String fileName = ActivityUtil.createFile(mContext, mListImage.get(i));
                mListImage.set(i, fileName);
            }*/
            Log.d(TAG, mListImage.get(i) + "");
        }
        mUpLoadRepository.upLoadMultiple(mListImage, new Callback<List<String>>() {
            @Override
            public void onSuccess(List<String> data) {
                int size = data.size();
                if (size > 0) {
                    StringBuilder picture = new StringBuilder(data.get(0));
                    for (int i = 1; i < size; i++) {
                        Log.d(TAG, "image = " + data.get(i));
                        picture.append(";").append(data.get(i));
                    }
                    Log.d(TAG, "picture = " + picture.toString());
                }
                createNotification();
            }

            @Override
            public void onError() {
                ActivityUtil.showMsg(mContext, R.string.msg_create_error);
            }
        });
    }

    private void createNotification() {
        mNotification.setShopId(mProfile.getShopId());
        mNotification.setNotificationId(ActivityUtil.randomId());
        mRepository.createNotification(mProfile.getToken(), mNotification,
            new Callback<Boolean>() {
                @Override
                public void onSuccess(Boolean data) {
                    mHandler.createNotificationSuccess();
                }

                @Override
                public void onError() {
                    ActivityUtil.showMsg(mContext, R.string.msg_create_error);
                }
            });
    }

    @Override
    public void clickDelete(int position) {
        mListImage.remove(position);
        mAdapter.get().notifyDataSetChanged();
    }

    @Override
    public void preview(List<String> images, int position) {
    }

    @Override
    public void onDateSet(DatePickerDialog view, int year, int monthOfYear, int dayOfMonth) {
        Calendar now = Calendar.getInstance();
        now.set(year, monthOfYear, dayOfMonth);
        mNotification.setLastDate(now.getTimeInMillis());
        mHandler.updateCalendar(now);
    }

    public void setLastDate(long timeInMillis) {
        mNotification.setLastDate(timeInMillis);
    }

    public void updateListImage(List<ImagePickerItem> imagePickerItems) {
        for (ImagePickerItem item : imagePickerItems) {
            if (!checkImageExists(item, mListImage)) {
                mListImage.add(item.getPathImage());
            }
        }
        mAdapter.get().notifyDataSetChanged();
    }

    private boolean checkImageExists(@NonNull ImagePickerItem item,
                                     @NonNull List<String> listImage) {
        for (String itemImage : listImage) {
            if (itemImage.equals(item.getPathImage())) return true;
        }
        return false;
    }

    public Notification getNotification() {
        return mNotification;
    }

    public ObservableList<String> getListImage() {
        return mListImage;
    }

    public ObservableField<NotificationCreationAdapter> getAdapter() {
        return mAdapter;
    }
}
