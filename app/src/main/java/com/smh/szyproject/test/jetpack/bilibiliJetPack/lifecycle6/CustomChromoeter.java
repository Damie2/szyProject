package com.smh.szyproject.test.jetpack.bilibiliJetPack.lifecycle6;

import android.content.Context;
import android.os.SystemClock;
import android.util.AttributeSet;
import android.widget.Chronometer;

import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.OnLifecycleEvent;

/**
 * author : smh
 * date   : 2020/9/28 15:20
 * desc   : 倒计时 计时器
 * 继承的是计时器
 */
public class CustomChromoeter extends Chronometer implements LifecycleObserver {

    private long watchedTime;

    public CustomChromoeter(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    public void onStart(){
        setBase(SystemClock.elapsedRealtime()-watchedTime);
        start();
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    public  void onStop(){
        watchedTime=SystemClock.elapsedRealtime()-getBase();
        stop();
    }

}
