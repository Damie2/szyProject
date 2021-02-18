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
import androidx.lifecycle.LifecycleRegistry;

import com.clj.fastble.BleManager;
import com.github.moduth.blockcanary.BlockCanary;
import com.hjq.http.EasyConfig;
import com.hjq.http.config.IRequestInterceptor;
import com.hjq.http.config.IRequestServer;
import com.hjq.http.model.HttpHeaders;
import com.hjq.http.model.HttpParams;
import com.smh.szyproject.common.image.ImageLoader;
import com.smh.szyproject.easyNet.model.RequestHandler;
import com.smh.szyproject.easyNet.server.MyServer;
import com.smh.szyproject.other.helper.ActivityStackManager;
import com.smh.szyproject.net.interceptor.CookieReadInterceptor;
import com.smh.szyproject.net.interceptor.CookiesSaveInterceptor;
import com.smh.szyproject.net.interceptor.InterceptorUtil;

import com.smh.szyproject.other.umeng.UmengClient;
import com.smh.szyproject.other.utils.CrashHandler;
import com.smh.szyproject.other.utils.ToastUtils;
import com.smh.szyproject.test.blockcanary.AppBlockCanaryContext;
import com.squareup.leakcanary.LeakCanary;

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
public class MyApplication extends Application implements LifecycleOwner{

    private static MyApplication application;
    private static Context context;
    private static OkHttpClient mOkHttpClient;
    public static final int TIMEOUT = 60;
    private final LifecycleRegistry mLifecycle = new LifecycleRegistry(this);

    @Override
    public void onCreate() {
        super.onCreate();
        application = this;
        context = getApplicationContext();
        mLifecycle.handleLifecycleEvent(Lifecycle.Event.ON_CREATE);
        initOKHttp();//普通的ok
        initEasyHttp();//轮子的请求
        ActivityStackManager.getInstance().init(application);
        CrashHandler.getInstance().init(this);
        initNetManager();
        initSDK();
    }

    private void initEasyHttp() {
        IRequestServer server = new MyServer();
        OkHttpClient okHttpClient = new OkHttpClient.Builder().build();
        EasyConfig.with(okHttpClient)
                // 是否打印日志
                //.setLogEnabled(BuildConfig.DEBUG)
                // 设置服务器配置
                .setServer(server)
                // 设置请求处理策略
                .setHandler(new RequestHandler(this))
                // 设置请求参数拦截器
                .setInterceptor(new IRequestInterceptor() {
                    @Override
                    public void intercept(String url, String tag, HttpParams params, HttpHeaders headers) {
                        headers.put("timestamp", String.valueOf(System.currentTimeMillis()));
                    }
                })
                // 设置请求重试次数
                .setRetryCount(1)
                // 设置请求重试时间
                .setRetryTime(1000)
                // 添加全局请求参数
                //.addParam("token", "6666666")
                // 添加全局请求头
                //.addHeader("time", "20191030")
                .into();
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

        //fastBle蓝牙初始化
        BleManager.getInstance().init(getApplication());
        BleManager.getInstance()
                .enableLog(true)
                .setReConnectCount(1, 5000)
                .setOperateTimeout(5000);



        if (LeakCanary.isInAnalyzerProcess(this)) {
            // This process is dedicated to LeakCanary for heap analysis.
            // You should not init your app in this process.
            return;
        }
        LeakCanary.install(this);

        //查看所有android源码的地址   androidxref


        //卡顿检测优化框架
        BlockCanary.install(this, new AppBlockCanaryContext()).start();
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
//                    .addInterceptor(new CookieReadInterceptor())
//                    .addInterceptor(new CookiesSaveInterceptor())
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
    public void onLowMemory() {
        super.onLowMemory();
        //清理所有图片内存缓存
    }

    @Override
    public void onTrimMemory(int level) {
        super.onTrimMemory(level);
        //根据手机内存剩余情况清理图片内存缓存
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        // 使用 Dex分包
        //MultiDex.install(this);
    }

    @NonNull
    @Override
    public Lifecycle getLifecycle() {
        return mLifecycle;
    }
}
