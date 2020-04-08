package com.smh.szyproject.utils;

import android.content.Context;
import android.os.Environment;
import android.os.StatFs;
import android.telephony.TelephonyManager;
import android.util.DisplayMetrics;

import java.io.File;
import java.util.UUID;

/**
 * Created by Administrator on 2016/9/20 0020.
 */
public class PhoneInfo {
    public static String getPhoneNumber(Context context) {
        TelephonyManager telephonyManager = (TelephonyManager) context
                .getSystemService(Context.TELEPHONY_SERVICE);
        return telephonyManager.getLine1Number();
    }

    public static String getIccId(Context context) {
        TelephonyManager telephonyManager = (TelephonyManager) context
                .getSystemService(Context.TELEPHONY_SERVICE);
        return telephonyManager.getSimSerialNumber();
    }

    public static String generateUUID(Context context) {
        TelephonyManager tm = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
        String imei = tm.getDeviceId();
        if (imei == null)
            imei = "000000000000000";
        String simSerialNumber = tm.getSimSerialNumber();
        if (simSerialNumber == null)
            simSerialNumber = "000000000000";
        String androidId = android.provider.Settings.Secure.getString(
                context.getContentResolver(), android.provider.Settings.Secure.ANDROID_ID);
        UUID deviceUuid = new UUID(androidId.hashCode(), ((long) imei.hashCode() << 32) | simSerialNumber.hashCode());
        String uniqueId = deviceUuid.toString();
        return uniqueId;
    }

    public static int getDpi(Context context) {
        DisplayMetrics dm = context.getResources().getDisplayMetrics();
        return (int) dm.densityDpi;
    }

    public static int getAndroidVersion() {
        return android.os.Build.VERSION.SDK_INT;
    }

    public static String getIMEI(Context context) {

        String imei = null;
        TelephonyManager telephonyManager = (TelephonyManager) context
                .getSystemService(Context.TELEPHONY_SERVICE);
        if (telephonyManager != null)
            imei = telephonyManager.getDeviceId();

        return imei;
    }

    @SuppressWarnings("deprecation")
    public static long getSDCardSize() {
        long size = 0;
        if (Environment.getExternalStorageState().equals(
                Environment.MEDIA_MOUNTED)) {
            try {
                File path = Environment.getExternalStorageDirectory();
                StatFs stat = new StatFs(path.getPath());
                long blockSize = stat.getBlockSize();
                // long totalBlocks = stat.getBlockCount();
                long availableBlocks = stat.getAvailableBlocks();
                // KB
                size = (blockSize * availableBlocks) / 1024;
            } catch (Exception e) {
                // this can occur if the SD card is removed
                size = 0;
            }
        }
        return size;
    }

    @SuppressWarnings("deprecation")
    public static long getRomSize() {

        long size = 0;
        File path = Environment.getDataDirectory();
        StatFs stat = new StatFs(path.getPath());
        long blockSize = stat.getBlockSize();
        long availableBlocks = stat.getAvailableBlocks();
        size = (blockSize * availableBlocks) / 1024;
        return size;
    }

    public static String getPhoneBrand() {
        return android.os.Build.BRAND;
    }

    public static String getPhoneModel() {
        return android.os.Build.MODEL;
    }

    public static String getIMSI(Context context) {
        String imsi = null;
        TelephonyManager telephonyManager = (TelephonyManager) context
                .getSystemService(Context.TELEPHONY_SERVICE);
        if (telephonyManager != null)
            imsi = telephonyManager.getSubscriberId();

        return imsi;
    }
    public static String getOperator(Context context) {
        String ProvidersName = "";
        TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
        String IMSI = telephonyManager.getSubscriberId();
        if (IMSI != null) {
            if (IMSI.startsWith("46000") || IMSI.startsWith("46002") || IMSI.startsWith("46007")) {
                ProvidersName = "中国移动";
            } else if (IMSI.startsWith("46001")  || IMSI.startsWith("46006")) {
                ProvidersName = "中国联通";
            } else if (IMSI.startsWith("46003")) {
                ProvidersName = "中国电信";
            }
            return ProvidersName;
        } else {
            return "没有获取到sim卡信息";
        }
    }


}
