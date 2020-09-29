package com.smh.szyproject.test.jetpack.bilibiliJetPack.viewmodel1;

import androidx.lifecycle.ViewModel;

/**
 * author : smh
 * date   : 2020/9/27 11:01
 * desc   :1、首先创建一个viewModel的类，继承自ViewModel
 * viewModel是个数据持有类
 */
public class CustomViewModel extends ViewModel {
    private int tv1,tv2;

    public int getTv2() {
        return tv2;
    }

    public void setTv2(int tv2) {
        this.tv2 = tv2;
    }

    public int getTv1() {
        return tv1;
    }

    public void setTv1(int tv1) {
        this.tv1 = tv1;
    }
}
