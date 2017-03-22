package com.nhahv.lovecoupon.ui.shop.notification;

import com.nhahv.lovecoupon.data.model.Notification;

/**
 * Created by Nhahv0902 on 3/6/2017.
 * <></>
 */
public interface IShopNotification {
    void editNotification(Notification notification);

    void showDialogDeleteNotification(Notification notification);
}
