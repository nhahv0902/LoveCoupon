package com.nhahv.lovecoupon.ui;

import android.support.v7.app.AppCompatActivity;
import com.nhahv.lovecoupon.ui.widget.LCProgressDialog;

/**
 * Created by Nhahv0902 on 3/9/2017.
 * <></>
 */
public class BaseActivity extends AppCompatActivity {
    private LCProgressDialog mProgressDialog;

    protected void start() {
    }

    public void showProgressDialog() {
        if (mProgressDialog == null) {
            mProgressDialog = new LCProgressDialog(this);
            mProgressDialog.setCancelable(false);
        }
        if (!mProgressDialog.isShowing()) mProgressDialog.show();
    }

    public void hideProgressDialog() {
        if (mProgressDialog != null && mProgressDialog.isShowing()) mProgressDialog.hide();
    }
}
