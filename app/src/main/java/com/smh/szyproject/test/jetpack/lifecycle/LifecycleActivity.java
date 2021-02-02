package com.smh.szyproject.test.jetpack.lifecycle;

import android.os.Bundle;

import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LifecycleRegistry;

import com.smh.szyproject.MyApplication;
import com.smh.szyproject.R;
import com.smh.szyproject.common.base.BaseActivity;

/**
 * Lifecycle目的是安卓组件activity和fragment在发生状态(onStart，onResume)变化的时候
 * 其他不发能够相应的组件变化
 */

public class LifecycleActivity extends BaseActivity  implements MyLifecycle{

    @Override
    public int getLayoutId() {
        return R.layout.test_activity_test;
    }

    @Override
    public void init(Bundle savedInstanceState) {
//        getLifecycle().addObserver(new ActivityLifecycleObserver());
        getLifecycle().addObserver(this);
    }
}
