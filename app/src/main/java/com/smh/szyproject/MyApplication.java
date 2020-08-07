package com.smh.szyproject;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.Application;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.Network;
import android.os.Build;
import android.text.TextUtils;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleOwner;

import com.smh.szyproject.common.image.ImageLoader;
import com.smh.szyproject.other.helper.ActivityStackManager;
import com.smh.szyproject.net.interceptor.CookieReadInterceptor;
import com.smh.szyproject.net.interceptor.CookiesSaveInterceptor;
import com.smh.szyproject.net.interceptor.InterceptorUtil;

import com.smh.szyproject.other.umeng.UmengClient;
import com.smh.szyproject.other.utils.CrashHandler;
import com.smh.szyproject.other.utils.ToastUtils;
import com.smh.szyproject.ui.activity.LoginActivity;

import org.xutils.x;

import java.util.List;
import java.util.concurrent.TimeUnit;

import cn.jpush.im.android.api.JMessageClient;
import me.jessyan.autosize.AutoSizeConfig;
import me.jessyan.autosize.unit.Subunits;
import okhttp3.OkHttpClient;

/**
 * 应用入口
 * 　　　　　　　　┏┓　　　┏┓
 * 　　　　　　　┏┛┻━━━┛┻┓
 * 　　　　　　　┃　　　　　　　┃
 * 　　　　　　　┃　　　━　　　┃
 * 　　　　　　　┃　＞　　　＜　┃
 * 　　　　　　　┃　　　　　　　┃
 * 　　　　　　　┃...　⌒　...　┃
 * 　　　　　　　┃　　　　　　　┃
 * 　　　　　　　┗━┓　　　┏━┛
 * 　　　　　　　　　┃　　　┃　Code is far away from bug with the animal protecting
 * 　　　　　　　　　┃　　　┃   神兽保佑,代码无bug
 * 　　　　　　　　　┃　　　┃
 * 　　　　　　　　　┃　　　┃
 * 　　　　　　　　　┃　　　┃
 * 　　　　　　　　　┃　　　┃
 * 　　　　　　　　　┃　　　┗━━━┓
 * 　　　　　　　　　┃　　　　　　　┣┓
 * 　　　　　　　　　┃　　　　　　　┏┛
 * 　　　　　　　　　┗┓┓┏━┳┓┏┛
 * 　　　　　　　　　　┃┫┫　┃┫┫
 * 　　　　　　　　　　┗┻┛　┗┻┛
 */
public class MyApplication extends Application {

    private static MyApplication application;
    private static Context context;
    private static OkHttpClient mOkHttpClient;
    public static final int TIMEOUT = 60;

    @Override
    public void onCreate() {
        super.onCreate();
        application = this;
        context = getApplicationContext();
        initSDK();
        initOKHttp();
        ActivityStackManager.getInstance().init(application);
        CrashHandler.getInstance().init(this);
        initNetManager();
    }


    private void initSDK() {
        String curProcessName = getProcessName(this);
        if (!curProcessName.equals(getPackageName())) {
            return;
        }
        // 友盟统计、登录、分享、推送 SDK 初始化
        UmengClient.init(this);
        //xutils
        x.Ext.init(this);
        x.Ext.setDebug(true);
        // 图片加载器
        ImageLoader.init(this);
        //autosize
        AutoSizeConfig.getInstance().getUnitsManager().setSupportDP(true).setSupportSubunits(Subunits.PT);
        //极光im第六步
        JMessageClient.setDebugMode(true);
        JMessageClient.init(this);

    }


    private void initNetManager() {
        //注册网络状态变化监听
        ConnectivityManager connectivityManager = ContextCompat.getSystemService(application, ConnectivityManager.class);
        if (connectivityManager != null && Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            connectivityManager.registerDefaultNetworkCallback(new ConnectivityManager.NetworkCallback() {
                @Override
                public void onLost(@NonNull Network network) {
                    super.onLost(network);
                    Activity topActivity = ActivityStackManager.getInstance().getTopActivity();
                    if (topActivity instanceof LifecycleOwner) {
                        LifecycleOwner lifecycleOwner = (LifecycleOwner) topActivity;
                        if (lifecycleOwner.getLifecycle().getCurrentState() == Lifecycle.State.RESUMED) {
                            ToastUtils.showToastForText(application, "当前网络不可用");
                        }
                    }
                }
            });
        }
    }


    public static OkHttpClient initOKHttp() {
        if (mOkHttpClient == null) {
            mOkHttpClient = new OkHttpClient.Builder()
                    .connectTimeout(TIMEOUT, TimeUnit.SECONDS)//设置连接超时时间
                    .readTimeout(TIMEOUT, TimeUnit.SECONDS)//设置读取超时时间
                    .writeTimeout(TIMEOUT, TimeUnit.SECONDS)//设置写入超时时间
                    .addInterceptor(InterceptorUtil.LogInterceptor())//添加日志拦截器
//                    .addInterceptor(new RequestEncryptInterceptor())//加密请求体了啊
                    .addInterceptor(new CookieReadInterceptor())
                    .addInterceptor(new CookiesSaveInterceptor())
//                    .addInterceptor(new CommonInterceptor())
                    .build();
        }
        return mOkHttpClient;
    }


    /**
     * 获取进程名。
     * 由于app是一个多进程应用，因此每个进程被os创建时，
     * onCreate()方法均会被执行一次，
     * 进行辨别初始化，针对特定进程进行相应初始化工作，
     * 此方法可以提高一半启动时间。
     *
     * @param context 上下文环境对象
     * @return 获取此进程的进程名
     */
    private String getProcessName(Context context) {
        ActivityManager am = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        List<ActivityManager.RunningAppProcessInfo> runningAppProcesses = am.getRunningAppProcesses();
        if (runningAppProcesses == null) {
            return "";
        }

        for (ActivityManager.RunningAppProcessInfo runningAppProcess : runningAppProcesses) {
            if (runningAppProcess.pid == android.os.Process.myPid()
                    && !TextUtils.isEmpty(runningAppProcess.processName)) {
                return runningAppProcess.processName;
            }
        }
        return "";
    }

    public static MyApplication getApplication() {
        return application;
    }

    public static Context getContext() {
        return context;
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        // 使用 Dex分包
        //MultiDex.install(this);
    }


}
