package com.smh.szyproject.other.utils;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Environment;

import androidx.core.content.ContextCompat;

import java.io.File;

public class Common {

    public static final String TEMP_DIR = "temp" + File.separator;//缓存目录


    //获取根目录信息
    public static String getBasePath(Context context) {
        String basePath = context.getCacheDir().getPath();
        if (ContextCompat.checkSelfPermission(context, Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED
                && ContextCompat.checkSelfPermission(context, Manifest.permission.MANAGE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED
                && Environment.getExternalStorageState() != null
                && Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            basePath = context.getExternalCacheDir().getPath();
        }
        return basePath + File.separator;
    }
}
