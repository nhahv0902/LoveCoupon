package com.nhahv.lovecoupon.ui.firstscreen;

/**
 * Created by Nhahv0902 on 3/6/2017.
 * <></>
 */
public enum AccountType {
    NORMAL, SHOP, CUSTOMER;

    public static AccountType toAccountType(String type) {
        try {
            return valueOf(type);
        } catch (Exception ex) {
            return NORMAL;
        }
    }
}
