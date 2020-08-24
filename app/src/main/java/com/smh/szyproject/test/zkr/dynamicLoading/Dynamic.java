package com.smh.szyproject.test.zkr.dynamicLoading;

import android.app.Activity;

import com.smh.szyproject.other.utils.ToastUtils;

/**
 * author : smh
 * date   : 2020/8/20 10:39
 * desc   : 动态加载类的实现
 */
public class Dynamic implements  IDynamic{
    private Activity mactivity;

    @Override
    public void init(Activity activity) {
        mactivity = activity;
    }

    @Override
    public void showBanner() {
        ToastUtils.showToastForText(mactivity,"showBanner");
    }

    @Override
    public void showDialog() {
        ToastUtils.showToastForText(mactivity,"showDialog");
    }

    @Override
    public void showFullScreen() {
        ToastUtils.showToastForText(mactivity,"showFullScreen");
    }

    @Override
    public void showAppWall() {
        ToastUtils.showToastForText(mactivity,"showAppWall");
    }

    @Override
    public void destory() {
        ToastUtils.showToastForText(mactivity,"destory");
    }
}
