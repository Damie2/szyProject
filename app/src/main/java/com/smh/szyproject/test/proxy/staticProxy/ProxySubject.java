package com.smh.szyproject.test.proxy.staticProxy;

import com.smh.szyproject.other.utils.L;

/**
 * author : smh
 * date   : 2019/12/31 14:12
 * desc   : 第三步，定义静态代理类，也就是功能增强类
 */
public class ProxySubject implements Subject{
    private Subject subject;

    public ProxySubject(Subject subject){
        this.subject = subject;
    }

    @Override
    public void sayGoodBye() {

        L.e("开始 sayGoodBye");
        subject.sayGoodBye();
        L.e("结束");
    }

    @Override
    public void sayHello(String msg) {
        L.e("开始sayHello");
        subject.sayGoodBye();
        L.e("结束");
    }
}
