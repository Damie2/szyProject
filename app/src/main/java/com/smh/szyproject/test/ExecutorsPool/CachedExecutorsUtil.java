package com.smh.szyproject.test.ExecutorsPool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 单例模式  可缓存式线程池
 */
public class CachedExecutorsUtil {

    ExecutorService cachedThreadPool;

    private volatile static CachedExecutorsUtil executors = null;

    public static CachedExecutorsUtil getInstance() {
        if (executors == null) {
            synchronized (CachedExecutorsUtil.class) {
                if (executors == null) {
                    executors = new CachedExecutorsUtil();
                }
            }
        }
        return executors;
    }

    public void init() {
        cachedThreadPool = Executors.newCachedThreadPool();
    }


    public void executors(Runnable runnable) {
        cachedThreadPool.execute(runnable);
    }
}
