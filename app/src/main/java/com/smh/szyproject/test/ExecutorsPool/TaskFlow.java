package com.smh.szyproject.test.ExecutorsPool;

import com.smh.szyproject.utils.L;

/**
 * author : smh
 * date   : 2020/5/6 11:32
 * desc   :
 */
public class TaskFlow implements Runnable {
    @Override
    public void run() {
        L.e("当前线程名：" + Thread.currentThread().getName());
    }
}
