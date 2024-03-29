package com.smh.szyproject.test.proxy.staticProxy;

import com.smh.szyproject.other.utils.L;

/**
 * author : smh
 * date   : 2019/12/31 14:09
 * desc   : 第二步 定义被代理的类(原来功能类),并实现被代理类的功能逻辑
 */
public class RealSubject implements Subject{
    @Override
    public void sayGoodBye() {
        L.e("sayGoodBye");
    }

    @Override
    public void sayHello(String msg) {
        L.e("sayHello"+msg);
    }
}
