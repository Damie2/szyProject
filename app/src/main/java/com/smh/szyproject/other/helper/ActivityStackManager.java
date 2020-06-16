package com.smh.szyproject.other.helper;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.collection.ArrayMap;

/**
 * time   : 2018/11/18
 * desc   : Activity 栈管理
 */
public final class ActivityStackManager implements Application.ActivityLifecycleCallbacks {

    private static volatile ActivityStackManager sInstance;

    private final ArrayMap<String, Activity> mActivitySet = new ArrayMap<>();

    /**
     * 当前应用上下文对象
     */
    private Application mApplication;
    /**
     * 当前 Activity 对象标记
     */
    private String mCurrentTag;

    private ActivityStackManager() {
    }

    public static ActivityStackManager getInstance() {
        // 加入双重校验锁
        if (sInstance == null) {
            synchronized (ActivityStackManager.class) {
                if (sInstance == null) {
                    sInstance = new ActivityStackManager();
                }
            }
        }
        return sInstance;
    }

    public void init(Application application) {
        mApplication = application;
        application.registerActivityLifecycleCallbacks(this);
    }

    /**
     * 获取 Application 对象
     */
    public Application getApplication() {
        return mApplication;
    }

    /**
     * 获取栈顶的 Activity
     */
    public Activity getTopActivity() {
        return mActivitySet.get(mCurrentTag);
    }

    /**
     * 销毁所有的 Activity
     */
    public void finishAllActivities() {
        finishAllActivities((Class<? extends Activity>) null);
    }

    /**
     * 销毁所有的 Activity，除这些 Class 之外的 Activity
     */
    @SafeVarargs
    public final void finishAllActivities(Class<? extends Activity>... classArray) {
        String[] keys = mActivitySet.keySet().toArray(new String[]{});
        for (String key : keys) {
            Activity activity = mActivitySet.get(key);
            if (activity != null && !activity.isFinishing()) {
                boolean whiteClazz = false;
                if (classArray != null) {
                    for (Class<? extends Activity> clazz : classArray) {
                        if (activity.getClass() == clazz) {
                            whiteClazz = true;
                        }
                    }
                }
                // 如果不是白名单上面的 Activity 就销毁掉
                if (!whiteClazz) {
                    activity.finish();
                    mActivitySet.remove(key);
                }
            }
        }
    }

    /**
     * 获取一个对象的独立无二的标记
     */
    private static String getObjectTag(Object object) {
        // 对象所在的包名 + 对象的内存地址
        return object.getClass().getName() + Integer.toHexString(object.hashCode());
    }

    @Override
    public void onActivityCreated(@NonNull Activity activity, @Nullable Bundle savedInstanceState) {
        mCurrentTag = getObjectTag(activity);
        mActivitySet.put(getObjectTag(activity), activity);
    }

    @Override
    public void onActivityStarted(@NonNull Activity activity) {
        mCurrentTag = getObjectTag(activity);
    }

    @Override
    public void onActivityResumed(@NonNull Activity activity) {
        mCurrentTag = getObjectTag(activity);
    }

    @Override
    public void onActivityPaused(@NonNull Activity activity) {
    }

    @Override
    public void onActivityStopped(@NonNull Activity activity) {
    }

    @Override
    public void onActivitySaveInstanceState(@NonNull Activity activity, @NonNull Bundle outState) {
    }

    @Override
    public void onActivityDestroyed(@NonNull Activity activity) {
        mActivitySet.remove(getObjectTag(activity));
        if (getObjectTag(activity).equals(mCurrentTag)) {
            // 清除当前标记
            mCurrentTag = null;
        }
    }
}
//下面是之前用的，带广告的
//////////////////////////////////////////////////////////////////

//
//    //测试后台返回前台后展示广告
//
//    // 正常状态
//    public static final int STATE_NORMAL = 0;
//    // 从后台回到前台
//    public static final int STATE_BACK_TO_FRONT = 1;
//    // 从前台进入后台
//    public static final int STATE_FRONT_TO_BACK = 2;
//
//
//    // APP状态
//    private static int sAppState = STATE_NORMAL;
//    // 标记程序是否已进入后台(依据onStop回调)
//    private boolean flag;
//    // 标记程序是否已进入后台(依据onTrimMemory回调)
//    private boolean background;
//    // 从前台进入后台的时间
//    private static long frontToBackTime;
//    // 从后台返回前台的时间
//    private static long backToFrontTime;
//
//    private void initActivityLife() {
//        registerActivityLifecycleCallbacks(new Application.ActivityLifecycleCallbacks() {
//            @Override
//            public void onActivityCreated(Activity activity, Bundle savedInstanceState) {
//            }
//
//            @Override
//            public void onActivityStarted(Activity activity) {
//            }
//
//            @Override
//            public void onActivityResumed(Activity activity) {
//                if (background || flag) {
//                    background = false;
//                    flag = false;
//                    sAppState = STATE_BACK_TO_FRONT;
//                    backToFrontTime = System.currentTimeMillis();
//                    if (canShowAd()) {
////                        ShowADActivity.show(activity);
////                        L.e("展示广告页面");
//                    }
//                } else {
//                    sAppState = STATE_NORMAL;
//                }
//            }
//
//            @Override
//            public void onActivityPaused(Activity activity) {
//            }
//
//            @Override
//            public void onActivityStopped(Activity activity) {
//
//                //判断当前activity是否处于前台
//                if (!isCurAppTop(activity)) {
//                    // 从前台进入后台
//                    sAppState = STATE_FRONT_TO_BACK;
//                    frontToBackTime = System.currentTimeMillis();
//                    flag = true;
//                } else {
//                    // 否则是正常状态
//                    sAppState = STATE_NORMAL;
//                }
//
//            }
//
//            @Override
//            public void onActivitySaveInstanceState(Activity activity, Bundle outState) {
//
//            }
//
//            @Override
//            public void onActivityDestroyed(Activity activity) {
//            }
//
//        });
//
//    }
//
//
//    @Override
//    public void onTrimMemory(int level) {
//        super.onTrimMemory(level);
//        // TRIM_MEMORY_UI_HIDDEN是UI不可见的回调, 通常程序进入后台后都会触发此回调,大部分手机多是回调这个参数
//        // TRIM_MEMORY_BACKGROUND也是程序进入后台的回调, 不同厂商不太一样, 魅族手机就是回调这个参数
//        if (level == Application.TRIM_MEMORY_UI_HIDDEN || level == TRIM_MEMORY_BACKGROUND) {
//            background = true;
//        } else if (level == Application.TRIM_MEMORY_COMPLETE) {
//            background = !isCurAppTop(this);
//        }
//        if (background) {
//            frontToBackTime = System.currentTimeMillis();
//            sAppState = STATE_FRONT_TO_BACK;
//            L.e("");
//        } else {
//            sAppState = STATE_NORMAL;
//        }
//
//    }
//
//    /**
//     * 判断当前程序是否前台进程
//     *
//     * @param context
//     * @return
//     */
//    public static boolean isCurAppTop(Context context) {
//        if (context == null) {
//            return false;
//        }
//        String curPackageName = context.getPackageName();
//        ActivityManager am = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
//        List<ActivityManager.RunningTaskInfo> list = am.getRunningTasks(1);
//        if (list != null && list.size() > 0) {
//            ActivityManager.RunningTaskInfo info = list.get(0);
//            String topPackageName = info.topActivity.getPackageName();
//            String basePackageName = info.baseActivity.getPackageName();
//            if (topPackageName.equals(curPackageName) && basePackageName.equals(curPackageName)) {
//                return true;
//            }
//        }
//        return false;
//    }
//
//
//    /**
//     * 进入后台间隔10分钟以后可以再次显示广告
//     *
//     * @return 是否能显示广告
//     */
//    public static boolean canShowAd() {
//        return sAppState == STATE_BACK_TO_FRONT &&
////                (backToFrontTime - frontToBackTime) > 10 * 60 * 1000;
//                (backToFrontTime - frontToBackTime) > 10 * 1000;
//    }
