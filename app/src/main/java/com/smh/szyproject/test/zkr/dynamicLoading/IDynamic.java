package com.smh.szyproject.test.zkr.dynamicLoading;

import android.app.Activity;

/**
 * author : smh
 * date   : 2020/8/20 10:35
 * desc   : 动态加载类的接口
 */
public interface IDynamic {
    //初始化方法
    public void init(Activity activity);

    /**
     * 自定义方法
     */
    public void showBanner();

    public void showDialog();

    public void showFullScreen();

    public void showAppWall();

    /**
     * 销毁方法
     */
    public void destory();

}
