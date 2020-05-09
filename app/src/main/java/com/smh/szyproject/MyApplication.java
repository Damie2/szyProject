package com.smh.szyproject;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.Application;
import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;

import com.smh.szyproject.common.image.ImageLoader;
import com.smh.szyproject.helper.ActivityStackManager;
import com.smh.szyproject.net.interceptor.CookieReadInterceptor;
import com.smh.szyproject.net.interceptor.CookiesSaveInterceptor;
import com.smh.szyproject.net.interceptor.InterceptorUtil;

import com.smh.szyproject.utils.L;
import com.umeng.commonsdk.UMConfigure;
import com.umeng.message.IUmengRegisterCallback;
import com.umeng.message.PushAgent;

import org.xutils.x;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import me.jessyan.autosize.AutoSizeConfig;
import me.jessyan.autosize.unit.Subunits;
import okhttp3.OkHttpClient;

public class MyApplication extends Application {

    public static List<Activity> activities = new ArrayList<>();
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
        initActivityLife();
        ActivityStackManager.getInstance().init(application);
    }

    private void initSDK() {
        // 友盟统计、登录、分享 SDK
        UMConfigure.init(this, Constants.UM.UM_APPKEY, "Umeng", UMConfigure.DEVICE_TYPE_PHONE, Constants.UM.UMENG_MESSAGE_SECRET);
        UMConfigure.setLogEnabled(true);
        PushAgent.getInstance(this).register(new IUmengRegisterCallback() {
            @Override
            public void onSuccess(String s) {
                L.e("注册成功:" + s);
            }

            @Override
            public void onFailure(String s, String s1) {
                L.e("失败:"+s+","+s1);
            }
        });
//        PushAgent agent = PushAgent.getInstance(this);
//        agent.register(new IUmengRegisterCallback() {
//            @Override
//            public void onSuccess(String s) {
//                L.e("deviceToken:"+s);
//            }
//
//            @Override
//            public void onFailure(String s, String s1) {
//                L.e("注册失败：" + s + ",s1:" + s1);
//            }
//        });

        //xtuil
        x.Ext.init(this);
        x.Ext.setDebug(true);
        // 图片加载器
        ImageLoader.init(this);
        //autosize
        AutoSizeConfig.getInstance().getUnitsManager().setSupportDP(true).setSupportSubunits(Subunits.MM);
    }


    public static Activity getTopActivity() {
        return activities.get(0);
    }


    //测试后台返回前台后展示广告

    // 正常状态
    public static final int STATE_NORMAL = 0;
    // 从后台回到前台
    public static final int STATE_BACK_TO_FRONT = 1;
    // 从前台进入后台
    public static final int STATE_FRONT_TO_BACK = 2;


    // APP状态
    private static int sAppState = STATE_NORMAL;
    // 标记程序是否已进入后台(依据onStop回调)
    private boolean flag;
    // 标记程序是否已进入后台(依据onTrimMemory回调)
    private boolean background;
    // 从前台进入后台的时间
    private static long frontToBackTime;
    // 从后台返回前台的时间
    private static long backToFrontTime;

    private void initActivityLife() {
        registerActivityLifecycleCallbacks(new ActivityLifecycleCallbacks() {
            @Override
            public void onActivityCreated(Activity activity, Bundle savedInstanceState) {
                activities.add(activity);
            }

            @Override
            public void onActivityStarted(Activity activity) {
            }

            @Override
            public void onActivityResumed(Activity activity) {
                if (background || flag) {
                    background = false;
                    flag = false;
                    sAppState = STATE_BACK_TO_FRONT;
                    backToFrontTime = System.currentTimeMillis();
                    if (canShowAd()) {
//                        ShowADActivity.show(activity);
//                        L.e("展示广告页面");
                    }
                } else {
                    sAppState = STATE_NORMAL;
                }

            }

            @Override
            public void onActivityPaused(Activity activity) {
            }

            @Override
            public void onActivityStopped(Activity activity) {

                //判断当前activity是否处于前台
                if (!isCurAppTop(activity)) {
                    // 从前台进入后台
                    sAppState = STATE_FRONT_TO_BACK;
                    frontToBackTime = System.currentTimeMillis();
                    flag = true;
                } else {
                    // 否则是正常状态
                    sAppState = STATE_NORMAL;
                }

            }

            @Override
            public void onActivitySaveInstanceState(Activity activity, Bundle outState) {

            }

            @Override
            public void onActivityDestroyed(Activity activity) {
                activities.remove(activity);
            }

        });

    }


    @Override
    public void onTrimMemory(int level) {
        super.onTrimMemory(level);
        // TRIM_MEMORY_UI_HIDDEN是UI不可见的回调, 通常程序进入后台后都会触发此回调,大部分手机多是回调这个参数
        // TRIM_MEMORY_BACKGROUND也是程序进入后台的回调, 不同厂商不太一样, 魅族手机就是回调这个参数
        if (level == Application.TRIM_MEMORY_UI_HIDDEN || level == TRIM_MEMORY_BACKGROUND) {
            background = true;
        } else if (level == Application.TRIM_MEMORY_COMPLETE) {
            background = !isCurAppTop(this);
        }
        if (background) {
            frontToBackTime = System.currentTimeMillis();
            sAppState = STATE_FRONT_TO_BACK;
            L.e("");
        } else {
            sAppState = STATE_NORMAL;
        }

    }

    /**
     * 判断当前程序是否前台进程
     *
     * @param context
     * @return
     */
    public static boolean isCurAppTop(Context context) {
        if (context == null) {
            return false;
        }
        String curPackageName = context.getPackageName();
        ActivityManager am = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        List<ActivityManager.RunningTaskInfo> list = am.getRunningTasks(1);
        if (list != null && list.size() > 0) {
            ActivityManager.RunningTaskInfo info = list.get(0);
            String topPackageName = info.topActivity.getPackageName();
            String basePackageName = info.baseActivity.getPackageName();
            if (topPackageName.equals(curPackageName) && basePackageName.equals(curPackageName)) {
                return true;
            }
        }
        return false;
    }


    /**
     * 进入后台间隔10分钟以后可以再次显示广告
     *
     * @return 是否能显示广告
     */
    public static boolean canShowAd() {
        return sAppState == STATE_BACK_TO_FRONT &&
//                (backToFrontTime - frontToBackTime) > 10 * 60 * 1000;
                (backToFrontTime - frontToBackTime) > 10 * 1000;
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


    public static Context getContext() {
        return context;
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
}
