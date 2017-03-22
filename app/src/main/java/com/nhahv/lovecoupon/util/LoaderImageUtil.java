package com.nhahv.lovecoupon.util;

import android.content.Context;
import android.database.Cursor;
import android.os.AsyncTask;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import com.nhahv.lovecoupon.data.model.ImageFolder;
import com.nhahv.lovecoupon.data.model.ImagePickerItem;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by dee on 15/11/19.
 * <></>
 */
public class LoaderImageUtil {
    private static final String TAG = "LoaderImageUtil";
    private static final String DATA_JPG = ".jpg";
    private static final String DATA_PNG = ".png";
    private static final String[] SELECTION_ARGS = new String[] { "image/jpeg", "image/png" };
    private static final String MIME_TYPE = MediaStore.Images.Media.MIME_TYPE;
    private static final String SELECTION = MIME_TYPE + "=? or " + MIME_TYPE + "=?";
    private final static String[] IMAGE_PROJECTION = {
            MediaStore.Images.Media.DATA, MediaStore.Images.Media.DISPLAY_NAME,
            MediaStore.Images.Media.DATE_ADDED, MediaStore.Images.ImageColumns.BUCKET_DISPLAY_NAME
    };
    private static LoaderImageUtil sInstance;
    private Context mContext;

    private LoaderImageUtil(Context context) {
        mContext = context;
    }

    public static LoaderImageUtil getInstance(Context context) {
        if (sInstance == null) sInstance = new LoaderImageUtil(context);
        return sInstance;
    }

    public void getImageFolders(@NonNull LoaderCallback callback) {
        new LoaderImageAsync(callback).execute();
    }

    private List<LoaderImageItem> getListImage() {
        List<LoaderImageItem> images = new ArrayList<>();
        Cursor cursor = mContext.getContentResolver()
                .query(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, IMAGE_PROJECTION, SELECTION,
                        SELECTION_ARGS, IMAGE_PROJECTION[2] + " DESC", null);
        if (cursor == null || !cursor.moveToFirst()) return images;
        LoaderImageItem loaderImageItem;
        int indexPath = cursor.getColumnIndex(IMAGE_PROJECTION[0]);
        int indexFolder = cursor.getColumnIndex(IMAGE_PROJECTION[3]);
        String pathImage, folderPath, folderName;
        while (!cursor.isAfterLast()) {
            pathImage = cursor.getString(indexPath);
            folderName = cursor.getString(indexFolder);
            folderPath = new File(pathImage).getParent();
            loaderImageItem = new LoaderImageItem(folderName, folderPath);
            images.add(loaderImageItem);
            cursor.moveToNext();
        }
        cursor.close();
        return images;
    }

    public List<ImageFolder> getListFolder() {
        List<ImageFolder> folders = new ArrayList<>();
        List<LoaderImageItem> images = getListImage();
        if (images.size() == 0) return folders;
        ImageFolder folder;
        for (LoaderImageItem item : images) {
            if (!existsFolder(item.getFolderName(), folders)) {
                folder = new ImageFolder(item.getFolderName(), item.getFolderPath());
                folder.getListImage().addAll(getListFiles(new File(folder.getFolderPath())));
                folders.add(folder);
            }
        }
        return folders;
    }

    private List<ImagePickerItem> getListFiles(File parentDir) {
        List<ImagePickerItem> inFiles = new ArrayList<>();
        File[] files = parentDir.listFiles();
        for (File file : files) {
            if (file.isDirectory()) {
                inFiles.addAll(getListFiles(file));
            } else if (file.getPath().endsWith(DATA_JPG) || file.getPath().endsWith(DATA_PNG)) {
                inFiles.add(new ImagePickerItem(file.getPath()));
            }
        }
        return inFiles;
    }

    private boolean existsFolder(String nameFolder, List<ImageFolder> folder) {
        for (ImageFolder item : folder) {
            if (item.getFolderName().toLowerCase().equals(nameFolder.toLowerCase())) return true;
        }
        return false;
    }

    public interface LoaderCallback {
        void onSuccess(List<ImageFolder> imageFolders);

        void onError();
    }

    public class LoaderImageItem {
        private String mFolderName;
        private String mFolderPath;

        public LoaderImageItem(String folderName, String folderPath) {
            mFolderName = folderName;
            mFolderPath = folderPath;
        }

        public String getFolderName() {
            return mFolderName;
        }

        public String getFolderPath() {
            return mFolderPath;
        }
    }

    private class LoaderImageAsync extends AsyncTask<Void, Void, List<ImageFolder>> {
        private LoaderCallback mCallback;

        public LoaderImageAsync(@NonNull LoaderCallback callback) {
            mCallback = callback;
        }

        @Override
        protected List<ImageFolder> doInBackground(Void... params) {
            return getListFolder();
        }

        @Override
        protected void onPostExecute(List<ImageFolder> imageFolders) {
            super.onPostExecute(imageFolders);
            if (mCallback == null) return;
            if (imageFolders != null) {
                mCallback.onSuccess(imageFolders);
            } else {
                mCallback.onError();
            }
        }
    }
}
