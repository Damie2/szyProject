package com.smh.szyproject.other.crash;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build;

import com.smh.szyproject.BuildConfig;
import com.smh.szyproject.other.helper.ActivityStackManager;
import com.smh.szyproject.other.utils.AppUtils;
import com.smh.szyproject.other.utils.L;

import java.io.File;
import java.io.FilenameFilter;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.text.SimpleDateFormat;
import java.util.Locale;

/**
 * 全局异常捕获类
 * Created by lwb on 2018/1/3.
 */
public class CrashHandler implements Thread.UncaughtExceptionHandler {
    private static final int MAX_STACK_TRACE_SIZE = 131071; //128 KB - 1
    public static final String SP_BUG = "BUG";
    /**
     * 系统默认UncaughtExceptionHandler
     */
    private Thread.UncaughtExceptionHandler mDefaultHandler;
    /**
     * 错误报告文件的扩展名
     */
    private static final String CRASH_REPORTER_EXTENSION = ".cr";
    /**
     * context
     */
    private Context mContext;

    /**
     * 存储异常和参数信息
     */
    private StringBuilder bugInfomation;
    /**
     * 格式化时间
     */
    private SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss", Locale.CHINA);

    private String TAG = this.getClass().getSimpleName();

    private static CrashHandler mInstance;


    private CrashHandler(Context context) {
        init(context);
    }

    /**
     * 获取CrashHandler实例
     */
    public static synchronized CrashHandler getInstance(Context context) {
        if (null == mInstance) {
            mInstance = new CrashHandler(context);
        }
        return mInstance;
    }

    public void init(Context context) {
        mContext = context;
        mDefaultHandler = Thread.getDefaultUncaughtExceptionHandler();
        //设置该CrashHandler为系统默认的
        Thread.setDefaultUncaughtExceptionHandler(this);
    }

    /**
     * uncaughtException 回调函数
     */
    @Override
    public void uncaughtException(Thread thread, Throwable ex) {
            //收集设备参数信息
            collectDeviceInfo(mContext);
            //添加自定义信息
            addCustomInfo();
            //处理错误日志
            saveCrashInfo2File(ex);
            mDefaultHandler.uncaughtException(thread, ex);
    }

    /**
     * 发送错误报告给服务器
     * @param ctx
     */
    private void sendCrashReportsToServer(Context ctx) {
        ActivityStackManager.getInstance().finishAllActivities();

    }

    /**
     * 获取错误报告文件名
     *
     * @param ctx
     * @return
     */
    private String[] getCrashReportFiles(Context ctx) {
        File filesDir = ctx.getFilesDir();
        FilenameFilter filter = new FilenameFilter() {
            public boolean accept(File dir, String name) {
                return name.endsWith(CRASH_REPORTER_EXTENSION);
            }
        };
        return filesDir.list(filter);
    }

    private void postReport(File file) {
        // TODO 发送错误报告到服务器
    }

    /**
     * 收集设备参数信息
     *
     * @param ctx
     */
    public void collectDeviceInfo(Context ctx) {
        bugInfomation = new StringBuilder();
        //获取versionName,versionCode
        try {
            PackageManager pm = ctx.getPackageManager();
            PackageInfo pi = pm.getPackageInfo(ctx.getPackageName(), PackageManager.GET_ACTIVITIES);
            if (pi != null) {
                String versionName = pi.versionName == null ? "null" : pi.versionName;
                String versionCode = pi.versionCode + "";
                bugInfomation.append("应用名：").append(AppUtils.getAppName(mContext)).append("\n");
                bugInfomation.append("应用版本：").append(versionName).append("\n");
                bugInfomation.append("应用版本号：").append(versionCode).append("\n");
                bugInfomation.append("型号：").append(Build.MODEL).append("\n");
                bugInfomation.append("设备名：").append(Build.DEVICE).append("\n");
                bugInfomation.append("安卓版本：").append(Build.VERSION.RELEASE).append("\n");
                bugInfomation.append("生产厂商：").append(Build.MANUFACTURER).append("\n");
                bugInfomation.append("Android SDK版本：").append(Build.VERSION.SDK_INT).append("\n");
                bugInfomation.append("硬件名：").append(Build.HARDWARE).append("\n");
                bugInfomation.append("———————————————").append("\n");
            }
        } catch (PackageManager.NameNotFoundException e) {
            L.e("出错啦" + e);
        }
    }

    /**
     * 添加自定义参数
     */
    private void addCustomInfo() {

    }

    /**
     * Handle Exception
     */
    private void saveCrashInfo2File(Throwable ex) {
        Writer writer = new StringWriter();
        PrintWriter printWriter = new PrintWriter(writer);
        ex.printStackTrace(printWriter);
        ex.printStackTrace();//这个是先打印出来
        printWriter.close();
        String stackTraceString = writer.toString();
        if (stackTraceString.length() > MAX_STACK_TRACE_SIZE) {
            String disclaimer = " [stack trace too large]";
            stackTraceString = stackTraceString.substring(0, MAX_STACK_TRACE_SIZE - disclaimer.length()) + disclaimer;
        }
        //错误日志
        bugInfomation.append("BUG：").append(stackTraceString);
        if (BuildConfig.DEBUG) {
//            Intent intent = new Intent(mContext,DefaultErrorActivity.class);
//            intent.putExtra(SP_BUG,bugInfomation.toString());
//            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
//            mContext.startActivity(intent);
//            L.e(bugInfomation.toString());
        } else {
            //杀掉自己
//            ActivityStackManager.getInstance().finishAllActivities();
//            android.os.Process.killProcess(android.os.Process.myPid());
//            System.exit(10);
        }
        ActivityStackManager.getInstance().finishAllActivities();
        android.os.Process.killProcess(android.os.Process.myPid());
        System.exit(10);
    }
}