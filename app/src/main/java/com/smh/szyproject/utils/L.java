package com.smh.szyproject.utils;

import android.util.Log;

import com.smh.szyproject.BuildConfig;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;


public class L {

    /**
     * 当前是否为 Debug 模式
     */
    public static boolean isDebug() {
        return BuildConfig.DEBUG;
    }


    public static String TAG = "-- LXbao --";

    public static void i(String str) {
        Log.i(TAG, str);
    }

    public static void d(String str) {
        Log.d(TAG, str);
    }

    public static void v(String str) {
        Log.v(TAG, str);
    }

    public static void w(String str) {
        Log.w(TAG, str);
    }

    public static String w(Throwable e) {
        String message = getErrorMessage(e);
        Log.e(TAG, message);
        return message;
    }

    public static void e(String str) {
        if (isDebug()) {
            Log.e(TAG, str);
        }
    }

    public static String e(Throwable e) {
        String message = getErrorMessage(e);
        if (isDebug()) {
            Log.e(TAG, message);
        }
        return message;
    }

    private static String getErrorMessage(Throwable e) {
        StringBuffer sb = new StringBuffer();
        Writer writer = new StringWriter();
        PrintWriter printWriter = new PrintWriter(writer);
        e.printStackTrace(printWriter);
        Throwable cause = e.getCause();
        while (cause != null) {
            cause.printStackTrace(printWriter);
            cause = cause.getCause();
        }
        printWriter.close();
        String result = writer.toString();
        sb.append(result);

        return sb.toString();
    }
}
