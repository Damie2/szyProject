package com.smh.szyproject.test.getScreen;

import android.accessibilityservice.AccessibilityService;
import android.view.accessibility.AccessibilityEvent;

/**
 * @Author smh
 * @Date 2021/11/12 11:30
 */
public class TaskService extends AccessibilityService {
    @Override
    public void onAccessibilityEvent(AccessibilityEvent event) {
        //注意这个方法回调，是在主线程，不要在这里执行耗时操作
    }
    @Override
    public void onInterrupt() {

    }

}
