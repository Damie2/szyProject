package com.smh.szyproject.test.jetpack.lifecycle;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.smh.szyproject.MyApplication;
import com.smh.szyproject.R;
import com.smh.szyproject.common.base.BaseActivity;

/**
 * Lifecycle目的是安卓组件activity和fragment在发生状态(onStart，onResume)变化的时候
 * 其他不发能够相应的组件变化
 */
//监听application生命周期
public class LifecycleActivityTwo extends BaseActivity  implements Application.ActivityLifecycleCallbacks {

    @Override
    public int getLayoutId() {
        return R.layout.test_activity_test;
    }

    @Override
    public void init(Bundle savedInstanceState) {

    }

    @Override
    public void onActivityCreated(@NonNull Activity activity, @Nullable Bundle savedInstanceState) {

    }

    @Override
    public void onActivityStarted(@NonNull Activity activity) {

    }

    @Override
    public void onActivityResumed(@NonNull Activity activity) {

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

    }
}
