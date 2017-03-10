package com.nhahv.lovecoupon.ui.shop.notificationcreation;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.MenuItem;

import com.nhahv.lovecoupon.R;
import com.nhahv.lovecoupon.data.model.Notification;
import com.nhahv.lovecoupon.databinding.ActivityNotificationCreationBinding;
import com.nhahv.lovecoupon.ui.BaseActivity;

import static com.nhahv.lovecoupon.util.Constant.BundleConstant.BUNDLE_NOTIFICATION;
import static com.nhahv.lovecoupon.util.Constant.BundleConstant.BUNDLE_NOTIFICATION_TYPE;

public class NotificationCreationActivity extends BaseActivity {
    private ActivityNotificationCreationBinding mBinding;
    private NotificationCreationViewModel mViewModel;
    private ActionNotificationType mType;
    private Notification mNotification = new Notification();

    public static Intent getNotificationIntent(@NonNull Context context,
                                               @NonNull Notification notification,
                                               @NonNull ActionNotificationType type) {
        Intent intent = new Intent(context, NotificationCreationActivity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable(BUNDLE_NOTIFICATION, notification);
        bundle.putSerializable(BUNDLE_NOTIFICATION_TYPE, type);
        intent.putExtras(bundle);
        return intent;
    }

    private void getDataFromIntent() {
        Bundle bundle = getIntent().getExtras();
        if (bundle == null || bundle.getSerializable(BUNDLE_NOTIFICATION_TYPE) == null) {
            mType = ActionNotificationType.CREATE;
            return;
        }
        mType = (ActionNotificationType) bundle.getSerializable(BUNDLE_NOTIFICATION_TYPE);
        if (bundle.getSerializable(BUNDLE_NOTIFICATION) != null) {
            mNotification = (Notification) bundle.getSerializable(BUNDLE_NOTIFICATION);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_notification_creation);
        getDataFromIntent();
        mViewModel =
            new NotificationCreationViewModel(getApplicationContext(), mNotification, mType);
        mBinding.setViewModel(mViewModel);
        start();
    }

    @Override
    protected void start() {
        setSupportActionBar(mBinding.toolbar);
        if (getSupportActionBar() != null) getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        switch (mType) {
            case CREATE:
                setTitle(R.string.title_create_notification);
                break;
            case EDIT:
                setTitle(R.string.title_edit_notification);
                break;
            default:
                break;
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) onBackPressed();
        return super.onOptionsItemSelected(item);
    }
}
