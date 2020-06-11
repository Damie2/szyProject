package com.smh.szyproject.test.observer.listener;

/**
 * author : smh
 * date   : 2020/6/10 17:52
 * desc   : 第二步，创建一个被观察者接口
 */
 interface SubjectListener {
    /**
     * 被观察者接口中有仨方法
     */
    /**
     * 这个接口是观察者接口
     * @param listener
     */
    void add(ObserverListener listener);

    /**
     * 通知观察者刷新接口
     * @param content
     */
    void notifyObserver(String content);

    /**
     * 删除接口
     * @param listener
     */
    void remove(ObserverListener listener);

}
