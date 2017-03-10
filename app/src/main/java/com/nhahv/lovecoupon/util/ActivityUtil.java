package com.nhahv.lovecoupon.util;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.media.ExifInterface;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.widget.Toast;

import com.nhahv.lovecoupon.R;
import com.nhahv.lovecoupon.service.NetworkReceiver;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * Created by Nhahv0902 on 3/6/2017.
 * <></>
 */
public final class ActivityUtil {
    public static final String DATA_TIME_EN = "MMM dd, yyyy";
    public static final String DATA_TIME_VN = "dd/MM/yyyy";
    private static final String ALLOWED_CHARACTERS = "0123456789qwertyuiopasdfghjklzxcvbnm";
    public static final int DATA_NUMBER = 20;
    public static final int DATA_NUMBER_TEMPLATE = 15;
    public static final int WIDTH_IMAGES_NEWS = 450;

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

    public static String randomTemplateId() {
        final Random random = new Random();
        final StringBuilder sb = new StringBuilder(DATA_NUMBER_TEMPLATE);
        for (int i = 0; i < DATA_NUMBER_TEMPLATE; ++i) {
            sb.append(ALLOWED_CHARACTERS.charAt(random.nextInt(ALLOWED_CHARACTERS.length())));
        }
        return sb.toString();
    }

    public static String dayLeft(long time) {
        Date last_date = new Date(time);
        long diff = last_date.getTime() - new Date().getTime();
        return String.valueOf(TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS) + 1);
    }

    public static String timeToString(long time) {
        String format = ActivityUtil.isEnglish() ? DATA_TIME_EN : DATA_TIME_VN;
        SimpleDateFormat mDateFormat = new SimpleDateFormat(format, Locale.getDefault());
        return mDateFormat.format(time);
    }

    public static boolean isEnglish() {
        String local = Locale.getDefault().getLanguage();
        return local.equals("en");
    }

    public static String createFile(Context context, String path) {
        Bitmap bitmap = scaleImages(path, WIDTH_IMAGES_NEWS);
        String nameImages = path.substring(path.lastIndexOf("/") + 1, path.indexOf("."));
        String timeStamp =
            new SimpleDateFormat("ddMMyyyy_HHmmss", Locale.getDefault()).format(new Date());
        File resizedFile = new File(context.getCacheDir(), nameImages + "_" + timeStamp + ".png");
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
        byte[] byteArray = stream.toByteArray();
        try {
            FileOutputStream fos = new FileOutputStream(resizedFile);
            fos.write(byteArray);
            fos.flush();
            fos.close();
            return resizedFile.getAbsolutePath();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static Bitmap scaleImages(String path, int width) {
        BitmapFactory.Options options = new BitmapFactory.Options();
        Bitmap bitmap = BitmapFactory.decodeFile(path, options);
        int bWidth = bitmap.getWidth();
        if (bWidth > width) {
            float ratioX = width / (float) bitmap.getWidth();
            int height = (int) (ratioX * bitmap.getHeight());
            Bitmap scaledBitmap = Bitmap.createBitmap(width, height,
                Bitmap.Config.ARGB_8888);
            float middleX = width / 2.0f;
            float middleY = height / 2.0f;
            Matrix scaleMatrix = new Matrix();
            scaleMatrix.setScale(ratioX, ratioX, middleX, middleY);
            Canvas canvas = new Canvas(scaledBitmap);
            canvas.setMatrix(scaleMatrix);
            canvas.drawBitmap(bitmap, middleX - bitmap.getWidth() / 2,
                middleY - bitmap.getHeight() / 2, new Paint(Paint.FILTER_BITMAP_FLAG));
            return modifyOrientation(scaledBitmap, path);
        }
        return modifyOrientation(bitmap, path);
    }

    public static Bitmap modifyOrientation(Bitmap bitmap, String image_absolute_path) {
        ExifInterface ei = null;
        try {
            ei = new ExifInterface(image_absolute_path);
        } catch (IOException e) {
            e.printStackTrace();
            return bitmap;
        }
        int orientation =
            ei.getAttributeInt(ExifInterface.TAG_ORIENTATION, ExifInterface.ORIENTATION_NORMAL);
        switch (orientation) {
            case ExifInterface.ORIENTATION_ROTATE_90:
                return rotate(bitmap, 90);
            case ExifInterface.ORIENTATION_ROTATE_180:
                return rotate(bitmap, 180);
            case ExifInterface.ORIENTATION_ROTATE_270:
                return rotate(bitmap, 270);
            case ExifInterface.ORIENTATION_FLIP_HORIZONTAL:
                return flip(bitmap, true, false);
            case ExifInterface.ORIENTATION_FLIP_VERTICAL:
                return flip(bitmap, false, true);
            default:
                return bitmap;
        }
    }

    private static Bitmap rotate(Bitmap bitmap, float degrees) {
        Matrix matrix = new Matrix();
        matrix.postRotate(degrees);
        return Bitmap
            .createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, true);
    }

    private static Bitmap flip(Bitmap bitmap, boolean horizontal, boolean vertical) {
        Matrix matrix = new Matrix();
        matrix.preScale(horizontal ? -1 : 1, vertical ? -1 : 1);
        return Bitmap
            .createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, true);
    }
}
