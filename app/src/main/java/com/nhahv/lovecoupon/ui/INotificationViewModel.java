package com.nhahv.lovecoupon.ui;

import java.util.List;

/**
 * Created by Nhahv0902 on 3/11/2017.
 * <></>
 */
public interface INotificationViewModel {
    void preview(List<String> images, int position);
    void clickDelete(int position);
}
