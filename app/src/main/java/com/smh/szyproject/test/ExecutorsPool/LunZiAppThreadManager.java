package com.smh.szyproject.test.ExecutorsPool;

import android.app.ActivityManager;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 线程池管理类
 */
public class LunZiAppThreadManager extends ThreadPoolExecutor {

    private static volatile LunZiAppThreadManager sInstance;
    /**
     *
     *  corePoolSize 一个核心线程  ,核心线程数，何为核心线程数？源码注释已经写得很明白了，也就是最小线程数，规定线程池里面最少必须有几个线程在工作，这些核心线程在没有任务可以执行的时候还必须存活着，除非我们设定了核心线程的存活时间，否则这些核心线程永远不会停止工作。 如果核心线程池为0，就代表空闲一段时间之后，就把里面所有的线程销毁
     *  maximumPoolSize  线程池中所能容纳最大的线程 最大线程数，这里面不仅包含了核心线程数，还包含了非核心线程数，那么问题来了，何为非核心线程？
     *  keepAliveTime 非核心线程闲置是的超时时长，超过这个时长，非核心线程就会被回收    非核心线程的存活时间，当线程池中的非核心线程没有任务执行的时候，如果超过了指定的时间还是没有执行任何任务的时候，那么这个非核心线程会在超时后被回收掉，如果我们不指定这个时间，那么这些非核心线程将永远不会被回收。
     *  unit  用于指定keepAliveTime的时间单位
     *  workQueue  线程池中的任务队列，通过线程池的execute方法提交的runable对象会存储在这个参数中
     */
    public LunZiAppThreadManager() {
//        super(1, Integer.MAX_VALUE, 30, TimeUnit.SECONDS, new LinkedBlockingQ
//
//        ueue<Runnable>());
        super(1, Integer.MAX_VALUE, 30, TimeUnit.SECONDS,  new SynchronousQueue<Runnable>());
    }

    public static LunZiAppThreadManager getInstance(){
        if(sInstance==null){
            synchronized (ActivityManager.class){
                if(sInstance==null){
                    sInstance=new LunZiAppThreadManager();
                }
            }
        }
        return sInstance;
    }
}
