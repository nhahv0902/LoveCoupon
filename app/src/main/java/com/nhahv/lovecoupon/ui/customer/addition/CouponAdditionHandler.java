package com.nhahv.lovecoupon.ui.customer.addition;

import com.nhahv.lovecoupon.data.model.CouponCustomer;

/**
 * Created by Nhahv0902 on 3/14/2017.
 * <></>
 */
public interface CouponAdditionHandler {
    void addCouponSuccess(CouponCustomer couponCustomer);
    void addCouponError();
}
