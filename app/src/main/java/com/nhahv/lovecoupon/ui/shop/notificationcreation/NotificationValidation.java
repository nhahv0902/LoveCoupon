package com.nhahv.lovecoupon.ui.shop.notificationcreation;

import android.support.annotation.NonNull;
import com.nhahv.lovecoupon.data.model.Notification;

/**
 * Created by Nhahv0902 on 3/10/2017.
 * <></>
 */
public class NotificationValidation {
    private Notification mNotification;

    public NotificationValidation(@NonNull Notification notification) {
        mNotification = notification;
    }

    public void validation(@NonNull Callback callback) {
        if (!isNonNull(mNotification.getTitle())) {
            callback.onError(Error.TITLE);
            return;
        }
        if (!isNonNull(mNotification.getContent())) {
            callback.onError(Error.CONTENT);
            return;
        }
        callback.onSuccess();
    }

    private boolean isNonNull(String textCheck) {
        return textCheck != null && !textCheck.isEmpty();
    }

    public enum Error {
        TITLE, CONTENT
    }

    public interface Callback {
        void onSuccess();

        void onError(Error error);
    }
}
