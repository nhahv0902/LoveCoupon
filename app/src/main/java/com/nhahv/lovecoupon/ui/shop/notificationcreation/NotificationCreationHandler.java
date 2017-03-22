package com.nhahv.lovecoupon.ui.shop.notificationcreation;

import java.util.Calendar;

/**
 * Created by Nhahv0902 on 3/10/2017.
 * <></>
 */
public interface NotificationCreationHandler {
    void openClickDate();

    void updateCalendar(Calendar calendar);

    void openPickImage();

    void createNotificationSuccess();
}
