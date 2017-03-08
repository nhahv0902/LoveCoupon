package com.nhahv.lovecoupon.util;

import android.content.Context;
import android.database.Cursor;
import android.provider.MediaStore;

import com.nhahv.lovecoupon.data.model.ImageFolder;
import com.nhahv.lovecoupon.data.model.ImagePickerItem;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import static com.nhahv.lovecoupon.util.Constant.DataConstant.DATA_JPG;
import static com.nhahv.lovecoupon.util.Constant.DataConstant.DATA_PNG;

/**
 * Created by dee on 15/11/19.
 * <></>
 */
public class LoaderImageUtil {
    private static final String TAG = "LoaderImageUtil";
    private final static String[] IMAGE_PROJECTION = {
        MediaStore.Images.Media.DATA,
        MediaStore.Images.Media.DISPLAY_NAME,
        MediaStore.Images.Media.DATE_ADDED,
        MediaStore.Images.ImageColumns.BUCKET_DISPLAY_NAME
    };
    private static final String SELECTION =
        MediaStore.Images.Media.MIME_TYPE + "=? or " + MediaStore.Images.Media.MIME_TYPE + "=?";
    private static final String[] SELECTION_ARGS = new String[]{"image/jpeg", "image/png"};

    public static List<LoaderImageItem> getListImage(Context context) {
        List<LoaderImageItem> images = new ArrayList<>();
        Cursor cursor = context.getContentResolver().query(
            MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
            IMAGE_PROJECTION, SELECTION, SELECTION_ARGS,
            IMAGE_PROJECTION[2] + " DESC", null);
        if (cursor == null || !cursor.moveToFirst()) return images;
        LoaderImageItem loaderImageItem;
        int indexPath = cursor.getColumnIndex(IMAGE_PROJECTION[0]);
        int indexName = cursor.getColumnIndex(IMAGE_PROJECTION[1]);
        int indexFolder = cursor.getColumnIndex(IMAGE_PROJECTION[3]);
        String pathImage, fileName, folderPath, folderName;
        while (!cursor.isAfterLast()) {
            pathImage = cursor.getString(indexPath);
            fileName = cursor.getString(indexName);
            folderName = cursor.getString(indexFolder);
            folderPath = new File(pathImage).getParent();
            loaderImageItem = new LoaderImageItem(fileName, folderName, pathImage, folderPath);
            images.add(loaderImageItem);
            cursor.moveToNext();
        }
        cursor.close();
        return images;
    }

    public static List<ImageFolder> getListFolder(Context context) {
        List<ImageFolder> folders = new ArrayList<>();
        List<LoaderImageItem> images = getListImage(context);
        if (images.size() == 0) return folders;
        // set up list folder image
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

    private static List<ImagePickerItem> getListFiles(File parentDir) {
        List<ImagePickerItem> inFiles = new ArrayList<>();
        File[] files = parentDir.listFiles();
        for (File file : files) {
            if (file.isDirectory()) inFiles.addAll(getListFiles(file));
            else if (file.getPath().endsWith(DATA_JPG) || file.getPath().endsWith(DATA_PNG)) {
                inFiles.add(new ImagePickerItem(file.getPath()));
            }
        }
        return inFiles;
    }

    private static boolean existsFolder(String nameFolder, List<ImageFolder> folder) {
        for (ImageFolder item : folder) {
            if (item.getFolderName().toLowerCase().equals(nameFolder.toLowerCase())) return true;
        }
        return false;
    }

    public static class LoaderImageItem {
        private String mFileName;
        private String mFolderName;
        private String mPathImage;
        private String mFolderPath;

        public LoaderImageItem(String name, String folder, String path, String folderPath) {
            mFileName = name;
            mFolderName = folder;
            mPathImage = path;
            mFolderPath = folderPath;
        }

        public String getFolderName() {
            return mFolderName;
        }

        public String getFolderPath() {
            return mFolderPath;
        }
    }
}
