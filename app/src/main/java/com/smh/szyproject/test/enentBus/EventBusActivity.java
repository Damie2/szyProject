package com.smh.szyproject.test.enentBus;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.OnLifecycleEvent;

import com.smh.szyproject.R;
import com.smh.szyproject.common.base.BaseActivity;
import com.smh.szyproject.other.utils.L;
import com.smh.szyproject.test.ble.LifeCircle;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

/**
 * author : smh
 * date   : 2020/11/24 14:32
 * desc   :
 */
public class EventBusActivity extends BaseActivity {

    @Override
    public int getLayoutId() {
        return R.layout.test_activity_test;
    }

    @Override
    public void init(Bundle savedInstanceState) {
//        getLifecycle().addObserver(new MyLifeCircle(this));

        findViewById(R.id.tv_next).setOnClickListener(v->{
            EventBus.getDefault().post(new MyBusEvent("嘿嘿嘿"));
        });
    }


//    hreadMode.MAIN：表示这个方法在主线程中执行
//
//    ThreadMode.BACKGROUND：表示该方法在后台执行，不能并发处理
//
//    ThreadMode.ASYNC：也表示在后台执行，可以异步并发处理
//
//    ThreadMode.POSTING：表示该方法和消息发送方在同一个线程中执行


    //订阅事件，并指定该事件在什么线程中执行
    @Subscribe(threadMode = ThreadMode.MAIN)//在主线程的订阅了这个事件
    public void  onMessageEvent (MyBusEvent event){
        L.e("消息是"+event.message);
    }


    @Subscribe(threadMode = ThreadMode.POSTING)//在主线程的订阅了这个事件
    public void  onMessageEvent1 (MyBusEvent event){
        L.e("消息是"+event.message);
    }


    @Override
    protected void onStart() {
        EventBus.getDefault().register(this);
        super.onStart();
    }

    @Override
    protected void onStop() {
        EventBus.getDefault().unregister(this);
        super.onStop();
    }
    //    @Override
//    protected void onStart() {
//        EventBus.getDefault().register(this);
//    }
//    @Override
//    protected void onStop() {
//        EventBus.getDefault().unregister(this);
//    }

}
