package com.nhahv.lovecoupon.util;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.widget.Toast;

import com.nhahv.lovecoupon.R;
import com.nhahv.lovecoupon.service.NetworkReceiver;

import java.util.Random;

/**
 * Created by Nhahv0902 on 3/6/2017.
 * <></>
 */
public final class ActivityUtil {
    private static final String ALLOWED_CHARACTERS = "0123456789qwertyuiopasdfghjklzxcvbnm";
    public static final int DATA_NUMBER = 20;

    public static void addFragment(FragmentManager manager, Fragment fragment, int layout) {
        manager.beginTransaction().replace(layout, fragment).commit();
    }

    public static void showMsg(Context context, int msg) {
        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
    }

    public static boolean isNetworkConnected(Context context) {
        if (NetworkReceiver.isConnect(context)) return true;
        showMsg(context, R.string.msg_network_error);
        return false;
    }

    public static String randomId() {
        final Random random = new Random();
        final StringBuilder sb = new StringBuilder(DATA_NUMBER);
        for (int i = 0; i < DATA_NUMBER; ++i) {
            sb.append(ALLOWED_CHARACTERS.charAt(random.nextInt(ALLOWED_CHARACTERS.length())));
        }
        return sb.toString();
    }
}
