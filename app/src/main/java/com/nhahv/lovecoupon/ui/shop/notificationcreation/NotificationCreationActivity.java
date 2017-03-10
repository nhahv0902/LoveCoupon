package com.nhahv.lovecoupon.ui.shop.notificationcreation;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.MenuItem;

import com.nhahv.lovecoupon.R;
import com.nhahv.lovecoupon.data.model.ImagePickerItem;
import com.nhahv.lovecoupon.data.model.Notification;
import com.nhahv.lovecoupon.data.source.remote.upload.UpLoadRepository;
import com.nhahv.lovecoupon.databinding.ActivityNotificationCreationBinding;
import com.nhahv.lovecoupon.ui.BaseActivity;
import com.nhahv.lovecoupon.ui.pickimage.folder.ImageFolderActivity;
import com.wdullaer.materialdatetimepicker.date.DatePickerDialog;

import java.util.Calendar;
import java.util.List;

import static com.nhahv.lovecoupon.util.Constant.BundleConstant.BUNDLE_IMAGE;
import static com.nhahv.lovecoupon.util.Constant.BundleConstant.BUNDLE_NOTIFICATION;
import static com.nhahv.lovecoupon.util.Constant.BundleConstant.BUNDLE_NOTIFICATION_TYPE;
import static com.nhahv.lovecoupon.util.Constant.DataConstant.DATA_DATE_PICKER;
import static com.nhahv.lovecoupon.util.Constant.RequestConstant.REQUEST_PICK_IMAGE;

public class NotificationCreationActivity extends BaseActivity
    implements NotificationCreationHandler {
    private ActivityNotificationCreationBinding mBinding;
    private NotificationCreationViewModel mViewModel;
    private ActionNotificationType mType;
    private Calendar mDatePicker;
    private UpLoadRepository mRepository;
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
        mDatePicker = Calendar.getInstance();
        getDataFromIntent();
        mViewModel =
            new NotificationCreationViewModel(getApplicationContext(), this, mNotification, mType);
        mBinding.setViewModel(mViewModel);
        start();
        mRepository = UpLoadRepository.getInstance();
    }

    @Override
    protected void start() {
        setSupportActionBar(mBinding.toolbar);
        if (getSupportActionBar() != null) getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        switch (mType) {
            case CREATE:
                setTitle(R.string.title_create_notification);
                mViewModel.setLastDate(mDatePicker.getTimeInMillis());
                break;
            case EDIT:
                setTitle(R.string.title_edit_notification);
                break;
            default:
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode != RESULT_OK || data.getExtras() == null) return;
        switch (requestCode) {
            case REQUEST_PICK_IMAGE:
                Bundle bundle = data.getExtras();
                if (bundle == null) return;
                List<ImagePickerItem> images = bundle.getParcelableArrayList(BUNDLE_IMAGE);
                if (images == null) return;
                mViewModel.updateListImage(images);

               /* mRepository.upLoadImage(images.get(0).getPathImage(), new Callback<String>() {
                    @Override
                    public void onSuccess(String data) {
                        Log.d("TAG", data);
                    }

                    @Override
                    public void onError() {
                    }
                });*//*

               mRepository.upLoadMultiple(imageList, new Callback<List<String>>() {
                   @Override
                   public void onSuccess(List<String> data) {
                   }

                   @Override
                   public void onError() {
                   }
               });*/
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

    @Override
    public void openClickDate() {
        DatePickerDialog datePicker = DatePickerDialog.newInstance(
            mViewModel,
            mDatePicker.get(Calendar.YEAR),
            mDatePicker.get(Calendar.MONTH),
            mDatePicker.get(Calendar.DAY_OF_MONTH)
        );
        datePicker.show(getFragmentManager(), DATA_DATE_PICKER);
    }

    @Override
    public void openPickImage() {
        startActivityForResult(ImageFolderActivity.getIntent(this), REQUEST_PICK_IMAGE);
    }

    @Override
    public void updateCalendar(Calendar calendar) {
        mDatePicker = calendar;
    }

    @Override
    public void createNotificationSuccess() {
        setResult(RESULT_OK);
        finish();
    }
}
