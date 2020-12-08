package com.smh.szyproject.test.proxy.dynamicProxy.two;


import com.smh.szyproject.other.utils.L;

/**
 * author : smh
 * date   : 2020/11/27 14:28
 * desc   :第二步
 */
public  class Man implements Subject{
    @Override
    public void shopping() {
        L.e("开始买东西");
    }
}
