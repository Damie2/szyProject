package com.smh.szyproject.test.enentBus;

import android.content.Context;

import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.OnLifecycleEvent;

import com.smh.szyproject.other.utils.L;
import com.smh.szyproject.test.ble.LifeCircle;

import org.greenrobot.eventbus.EventBus;

/**
 * author : smh
 * date   : 2020/11/24 14:47
 * desc   :
 */
public class MyLifeCircle extends LifeCircle {

    public MyLifeCircle(Context context) {
        super(context);
    }
    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    public void onStart() {
        EventBus.getDefault().register(this);
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    public void onStop() {
        EventBus.getDefault().unregister(this);
    }

}
