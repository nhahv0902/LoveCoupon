package com.nhahv.lovecoupon.ui;

import android.support.multidex.MultiDexApplication;
import com.squareup.leakcanary.LeakCanary;

/**
 * Created by Nhahv0902 on 3/15/2017.
 * <></>
 */
public class LCApplication extends MultiDexApplication {
    @Override
    public void onCreate() {
        super.onCreate();
        if (LeakCanary.isInAnalyzerProcess(this)) {
            return;
        }
        LeakCanary.install(this);
    }
}
