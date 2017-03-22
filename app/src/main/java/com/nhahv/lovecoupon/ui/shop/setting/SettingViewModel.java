package com.nhahv.lovecoupon.ui.shop.setting;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.databinding.BaseObservable;
import android.databinding.ObservableBoolean;
import android.graphics.Bitmap;
import android.net.Uri;
import android.support.v4.app.Fragment;
import android.util.Log;
import com.nhahv.lovecoupon.R;
import com.nhahv.lovecoupon.data.model.ShopProfile;
import com.nhahv.lovecoupon.data.source.Callback;
import com.nhahv.lovecoupon.data.source.remote.updateprofile.UpdateRepository;
import com.nhahv.lovecoupon.ui.shop.main.IShopMainHandler;
import com.nhahv.lovecoupon.util.ActivityUtil;
import com.nhahv.lovecoupon.util.SharePreferenceUtil;
import com.yalantis.ucrop.UCrop;
import java.io.File;

import static com.facebook.FacebookSdk.getCacheDir;
import static com.nhahv.lovecoupon.util.Constant.RequestConstant.REQUEST_OPEN_GALLERY;

/**
 * Created by Nhahv0902 on 3/6/2017.
 * <></>
 */
public class SettingViewModel extends BaseObservable {
    private final String TAG = getClass().getSimpleName();
    private final Context mContext;
    private final ObservableBoolean mShopNoData = new ObservableBoolean();
    private final ObservableBoolean mUserTrue = new ObservableBoolean(true);
    private final ISettingFragment mListener;
    private final ShopProfile mProfile;
    private final SharePreferenceUtil mPreference;
    private final UpdateRepository mRepository;
    private final IShopMainHandler mUpdateProfileListener;

    public SettingViewModel(Context context, ISettingFragment iSettingFragment,
            IShopMainHandler listener) {
        mContext = context;
        mListener = iSettingFragment;
        mRepository = UpdateRepository.getInstance();
        mUpdateProfileListener = listener;
        mPreference = SharePreferenceUtil.getInstance(context);
        mProfile = mPreference.profileShop();
    }

    public ObservableBoolean getShopNoData() {
        return mShopNoData;
    }

    public void clickPickLogo() {
        if (mListener != null) mListener.openGallery();
    }

    public void handlerResult(int requestCode, int resultCode, Intent data, Fragment fragment) {
        if (resultCode != Activity.RESULT_OK) return;
        switch (requestCode) {
            case REQUEST_OPEN_GALLERY:
                startCropView(data.getData(), fragment);
                break;
            case UCrop.REQUEST_CROP:
                handleCropResult(data);
                break;
            default:
                break;
        }
    }

    public void startCropView(Uri uri, Fragment fragment) {
        String destinationFileName = ActivityUtil.randomId() + ".jpg";
        Log.d(TAG, "file name = " + destinationFileName);
        File[] files = getCacheDir().listFiles();
        if (files != null && files.length > 0) {
            for (File file : files)
                if (file.isFile()) {
                    Log.d(TAG, "file = " + file.getAbsolutePath());
                    boolean delete = file.delete();
                    Log.d(TAG, "delete = " + delete);
                }
        }
        UCrop uCrop = UCrop.of(uri, Uri.fromFile(new File(getCacheDir(), destinationFileName)));
        uCrop = uCrop.withAspectRatio(1, 1);
        UCrop.Options options = new UCrop.Options();
        options.setCompressionFormat(Bitmap.CompressFormat.JPEG);
        options.setCompressionQuality(100);
        options.setFreeStyleCropEnabled(true);
        uCrop.withOptions(options);
        uCrop.start(mContext, fragment);
    }

    public void handleCropResult(Intent data) {
        Uri resultUri = UCrop.getOutput(data);
        if (resultUri == null) {
            ActivityUtil.showMsg(mContext, R.string.msg_crop_logo_error);
            return;
        }
        mProfile.setLogoLink(resultUri.getPath());
        Log.d(TAG, resultUri.getPath());
    }

    public ShopProfile getProfile() {
        return mProfile;
    }

    public ObservableBoolean getUserTrue() {
        return mUserTrue;
    }

    public void setUserTrueFailed() {
        mUserTrue.set(false);
    }

    public void updateProfileShop() {
        if (!mUserTrue.get()) {
            ActivityUtil.showMsg(mContext, R.string.msg_existing);
            return;
        }
        new ProfileShopValidation(mProfile).validation(new ProfileShopValidation.ProfileCallback() {
            @Override
            public void onSuccess() {
                update();
            }

            @Override
            public void onError(ProfileShopValidation.ProfileError error) {
                switch (error) {
                    case SHOP_NAME:
                        ActivityUtil.showMsg(mContext, R.string.msg_shop_name_error);
                        break;
                    case ADDRESS:
                        ActivityUtil.showMsg(mContext, R.string.msg_shop_address_error);
                        break;
                    case USER_1:
                    case USER_2:
                        ActivityUtil.showMsg(mContext, R.string.msg_password_error);
                        break;
                    default:
                        break;
                }
            }
        });
    }

    private void update() {
        if (mRepository == null || mProfile == null) return;
        if (mProfile.getUser1() != null && !mProfile.getUser1().isEmpty()) {
            checkUser1();
        } else if (mProfile.getUser2() != null && !mProfile.getUser2().isEmpty()) {
            checkUser2();
        } else {
            updateProfile();
        }
    }

    private void checkUser1() {
        mRepository.isUserExists(mProfile.getId(), mProfile.getUser1(), new Callback<Boolean>() {
            @Override
            public void onSuccess(Boolean data) {
                checkUser2();
            }

            @Override
            public void onError() {
                ActivityUtil.showMsg(mContext, R.string.msg_existing);
            }
        });
    }

    private void checkUser2() {
        mRepository.isUserExists(mProfile.getId(), mProfile.getUser2(), new Callback<Boolean>() {
            @Override
            public void onSuccess(Boolean data) {
                updateProfile();
            }

            @Override
            public void onError() {
                ActivityUtil.showMsg(mContext, R.string.msg_existing);
            }
        });
    }

    private void updateProfile() {
        mRepository.updateProfile(mProfile.getToken(), mProfile, new Callback<Boolean>() {
            @Override
            public void onSuccess(Boolean data) {
                if (mUpdateProfileListener != null) {
                    mUpdateProfileListener.updateProfile(mProfile);
                }
                ActivityUtil.showMsg(mContext, R.string.msg_update_profile_success);
            }

            @Override
            public void onError() {
                ActivityUtil.showMsg(mContext, R.string.msg_update_profile_error);
            }
        });
    }
}
