package com.smh.szyproject.test.observer.listener;

import java.util.ArrayList;
import java.util.List;

/**
 * author : smh
 * date   : 2020/6/10 17:59
 * desc   : 第三步，非常重要的管理类
 */
public class ObserverManager implements SubjectListener {

    private static ObserverManager observerManager;

    /**
     * 观察者模式接口
     */
    private List<ObserverListener> list = new ArrayList<>();

    /**
     * 单例模式
     *
     * @return
     */
    public static ObserverManager getInstance() {
        if (null == observerManager) {
            synchronized (ObserverManager.class) {
                if (null == observerManager) {
                    observerManager = new ObserverManager();
                }
            }
        }
        return observerManager;
    }

    @Override
    public void add(ObserverListener listener) {
        list.add(listener);
    }

    @Override
    public void notifyObserver(String content) {
        for (ObserverListener observerListener : list) {
            //循环list，给每个list里添加内容
            observerListener.observerUpDate(content);
        }
    }

    /**
     * 监听队列里删除
     * @param listener
     */
    @Override
    public void remove(ObserverListener listener) {
        if (list.contains(listener)) {
            list.remove(listener);
        }
    }
}
