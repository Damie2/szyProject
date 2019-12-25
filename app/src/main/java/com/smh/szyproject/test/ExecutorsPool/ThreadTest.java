package com.smh.szyproject.test.ExecutorsPool;

import android.os.Bundle;

import com.smh.szyproject.base.BaseActivity;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 线程池测试
 */
public class ThreadTest extends BaseActivity {


    @Override
    public int getLayoutId() {
        return 0;
    }

    @Override
    public void init(Bundle savedInstanceState) {
        ExecutorService threadExecutor = new ThreadPoolExecutor(
                1,//一个核心线程
                1,//线程池中所能容纳最大的线程
                60,//非核心线程闲置是的超市时长，超过这个时长，非核心线程就会被回收
                TimeUnit.SECONDS,//用于指定keepAliveTime的时间单位
                new SynchronousQueue<Runnable>(),//线程池中的人物队列，通过线程池的execute方法提交的runable对象会存储在这个参数中
                new ThreadPoolExecutor.DiscardPolicy()
        );
    }
}
