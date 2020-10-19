package com.smh.szyproject.test.ExecutorsPool;

import android.os.Bundle;
import android.os.CountDownTimer;

import com.smh.szyproject.R;
import com.smh.szyproject.common.base.BaseActivity;
import com.smh.szyproject.other.utils.L;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 线程池测试
 */
public class ThreadTest extends BaseActivity {

    CountDownTimer timer;
    ExecutorService oneThreadExecutor;

    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    public void init(Bundle savedInstanceState) {
        //每次只允许有一个线程运行
        //这是常用的单个线程池
//        oneThread();
        //        initTimer();
        //可缓存的线程池，如果线程池长度超过处理需要,如果没有回收，就新建线程
//        CachedThreadPool();
        //创建一个定长线程池，可控制线程最大并发数，超出的线程会在队列中等待
//        fixedThreadPool();
        //创建一个定长线程池，支持定时及周期性任务执行
//        ScheduledThreadPool();
//        一个单线程化的线程池
        singleThreadExecutor();
    }

    private void singleThreadExecutor() {
        ExecutorService singleThreadExecutor = Executors.newSingleThreadExecutor();
        for (int i = 0; i < 10; i++) {
            final int index = i;
            singleThreadExecutor.execute(new Runnable() {

                @Override
                public void run() {
                    try {
                        L.e("index是：" + index);
                        L.e("当前线程名：" + Thread.currentThread().getName());
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });
        }
    }

    private void ScheduledThreadPool() {
        //这是个定时器的线程池，延迟3秒执行任务
        ScheduledExecutorService  scheduledExecutorService =Executors.newScheduledThreadPool(5);
        //延时三秒执行,定时器
//        scheduledExecutorService.schedule(new Runnable() {
//            @Override
//            public void run() {
//                L.e("当前线程名：" + IOThread.currentThread().getName());
//            }
//        },3,TimeUnit.SECONDS);

        //表示延迟1秒后每3秒执行一次。
        scheduledExecutorService.scheduleAtFixedRate(new Runnable() {

            @Override
            public void run() {
                L.e("当前线程名：" + Thread.currentThread().getName());
            }
        }, 1, 3, TimeUnit.SECONDS);

    }

    private void fixedThreadPool() {
        //线程池大小是3，每个任务输出index后睡两秒，所以每两秒打印三个数字
        //会重复使用那仨线程，超出的线程会在队列中等待
        ExecutorService fixedThreadPool = Executors.newFixedThreadPool(3);
        for (int i = 0; i < 10; i++) {
            final int index = i;
            fixedThreadPool.execute(new Runnable() {
                @Override
                public void run() {
                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    L.e("index是：" + index);
                    L.e("当前线程名：" + Thread.currentThread().getName());
                }
            });
        }


    }

    private void CachedThreadPool() {
        //线程池无限大，当执第二个个任务时第一个任务已经完成，会重复使用第一个任务的线程，而不用创建新线程
        ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
        for (int i = 0; i < 10; i++) {
            final int index = i;
            try {
                Thread.sleep(index * 1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            cachedThreadPool.execute(new Runnable() {
                @Override
                public void run() {
                    L.e("index是：" + index);
                    L.e("当前线程名：" + Thread.currentThread().getName());
                }
            });
        }
    }

    private void initTimer() {
        timer = new CountDownTimer(Long.MAX_VALUE, 100) {
            @Override
            public void onTick(long millisUntilFinished) {
                oneThreadExecutor.execute(new TaskFlow());
            }

            @Override
            public void onFinish() {

            }
        };
        timer.start();
    }

    private void oneThread() {
        oneThreadExecutor = new ThreadPoolExecutor(
                1,//一个核心线程  ,核心线程数，何为核心线程数？源码注释已经写得很明白了，也就是最小线程数，规定线程池里面最少必须有几个线程在工作，这些核心线程在没有任务可以执行的时候还必须存活着，除非我们设定了核心线程的存活时间，否则这些核心线程永远不会停止工作。
                1,//线程池中所能容纳最大的线程 最大线程数，这里面不仅包含了核心线程数，还包含了非核心线程数，那么问题来了，何为非核心线程？
                60,//非核心线程闲置是的超市时长，超过这个时长，非核心线程就会被回收    非核心线程的存活时间，当线程池中的非核心线程没有任务执行的时候，如果超过了指定的时间还是没有执行任何任务的时候，那么这个非核心线程会在超时后被回收掉，如果我们不指定这个时间，那么这些非核心线程将永远不会被回收。

                TimeUnit.SECONDS,//用于指定keepAliveTime的时间单位
                new SynchronousQueue<Runnable>(),//线程池中的人物队列，通过线程池的execute方法提交的runable对象会存储在这个参数中
                new ThreadPoolExecutor.DiscardPolicy()
        );

    }
}
