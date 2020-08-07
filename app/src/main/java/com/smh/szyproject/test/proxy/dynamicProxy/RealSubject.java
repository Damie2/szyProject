package com.smh.szyproject.test.proxy.dynamicProxy;

import com.smh.szyproject.other.utils.L;

/**
 * author : smh
 * date   : 2019/12/31 14:09
 * desc   : 第二步 定义被代理的类(原来功能类),并实现被代理类的功能逻辑
 */
public class RealSubject implements Subject {
    @Override
    public void sayGoodBye() {
        L.e("呃呃呃呃呃呃呃");
    }

    @Override
    public void sayGoodBy11111e() {
        L.e("三生三世");
    }

}
