package com.nhahv.lovecoupon.ui.widget;

import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.widget.ProgressBar;

/**
 * Created by Nhahv0902 on 3/27/2017.
 * <></>
 */
public class LCProgressDialog extends ProgressDialog {
    public LCProgressDialog(Context context) {
        super(context);
    }

    public LCProgressDialog(Context context, int theme) {
        super(context, theme);
    }

    @Override
    public void show() {
        super.show();
        if (getWindow() != null) {
            getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        }
        setContentView(new ProgressBar(getContext()));
    }
}
