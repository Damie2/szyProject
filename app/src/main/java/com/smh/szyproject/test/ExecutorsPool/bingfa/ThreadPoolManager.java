package com.smh.szyproject.test.ExecutorsPool.bingfa;

import android.util.Log;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.logging.Handler;
import java.util.logging.LogRecord;

/**
 * author : smh
 * date   : 2020/12/3 18:03
 * desc   :https://blog.csdn.net/kerryqpw/article/details/64129583?utm_medium=distribute.pc_relevant.none-task-blog-BlogCommendFromBaidu-3.not_use_machine_learn_pai&depth_1-utm_source=distribute.pc_relevant.none-task-blog-BlogCommendFromBaidu-3.not_use_machine_learn_pai
 */
public class ThreadPoolManager {

    //拒绝机制，这个再说啊啊啊
    private RejectedExecutionHandler handler = new RejectedExecutionHandler(){

        @Override
        public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
            try {
                service.put(new FutureTask<Object>(runnable,null));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    };


    private static ThreadPoolManager instance = new ThreadPoolManager();

    public static ThreadPoolManager getInstance() {
        return instance;
    }

    //线程池
    private ThreadPoolExecutor threadPoolExecutor;
    //请求队列
    private LinkedBlockingQueue<Future<?>> service = new LinkedBlockingQueue<>();

    //初始化
    private ThreadPoolManager() {

        threadPoolExecutor = new ThreadPoolExecutor(4, 10, 10, TimeUnit.SECONDS,
                new ArrayBlockingQueue<Runnable>(4), handler);

        threadPoolExecutor.execute(runnable);
    }

    //请求队列里的线程任务排队到线程池执行
    private Runnable runnable = new Runnable() {
        @Override
        public void run() {

            while (true) {
                FutureTask futureTask = null;

                try {
                    Log.e("myThreadPook", "service size " + service.size());
                    futureTask = (FutureTask) service.take();
                    Log.e("myThreadPook", "池  " + threadPoolExecutor.getPoolSize());

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                if (futureTask != null) {
                    threadPoolExecutor.execute(futureTask);
                }
            }
        }
    };

    //执行的线程任务,延时多少秒执行
    public <T> void execute(final FutureTask<T> futureTask, Object delayed) {


        if (futureTask != null) {
            try {
                //延时执行
                if (delayed != null) {
                    Timer timer = new Timer();
                    timer.schedule(new TimerTask() {
                        public void run() {
                            try {
                                service.put(futureTask);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }

                        }
                    }, (long) delayed);
                } else {
                    service.put(futureTask);

                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }

    }
}