package com.smh.szyproject.other.utils;

/**
 * Created by Administrator on 2017/7/4.
 */

import android.app.AppOpsManager;
import android.content.Context;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public final class SmsWriteOpUtils {
    private static final int WRITE_OP_CODE = 15;

    public static boolean isWriteEnabled(Context context) {
        int result = checkOp(context);
        return result == AppOpsManager.MODE_ALLOWED;
    }

    public static boolean setWriteEnabled(Context context, boolean enabled) {
        int mode = enabled ? AppOpsManager.MODE_ALLOWED : AppOpsManager.MODE_IGNORED;
        return setMode(context, mode);
    }

    private static int checkOp(Context context) {
        try {
            Method checkOpMethod = AppOpsManager.class.getMethod("checkOp",
                    Integer.TYPE,
                    Integer.TYPE,
                    String.class);

            AppOpsManager appOpsManager =
                    (AppOpsManager) context.getSystemService(Context.APP_OPS_SERVICE);
            int uid = context.getApplicationInfo().uid;
            String packageName = context.getPackageName();

            return (int) checkOpMethod.invoke(appOpsManager, WRITE_OP_CODE, uid, packageName);
        }
        catch (NoSuchMethodException | InvocationTargetException | IllegalAccessException e) {
            e.printStackTrace();
        }
        return -1;
    }

    private static boolean setMode(Context context, int mode) {
        try {
            Method setModeMethod = AppOpsManager.class.getMethod("setMode",
                    Integer.TYPE,
                    Integer.TYPE,
                    String.class,
                    Integer.TYPE);

            AppOpsManager appOpsManager =
                    (AppOpsManager) context.getSystemService(Context.APP_OPS_SERVICE);
            int uid = context.getApplicationInfo().uid;
            String packageName = context.getPackageName();

            setModeMethod.invoke(appOpsManager, WRITE_OP_CODE, uid, packageName, mode);

            return true;
        }
        catch (NoSuchMethodException | InvocationTargetException | IllegalAccessException e) {
            e.printStackTrace();
        }
        return false;
    }
}
