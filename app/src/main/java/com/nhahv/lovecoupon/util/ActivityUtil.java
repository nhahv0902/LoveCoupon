package com.nhahv.lovecoupon.util;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.widget.Toast;

/**
 * Created by Nhahv0902 on 3/6/2017.
 * <></>
 */
public final class ActivityUtil {
    public static void addFragment(FragmentManager manager, Fragment fragment, int layout) {
        manager.beginTransaction().replace(layout, fragment).commit();
    }

    public static void showMsg(Context context, int msg) {
        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
    }
}
