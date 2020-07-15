package com.smh.szyproject.test.proxy.dynamicProxy;

import android.os.Bundle;

import com.smh.szyproject.R;
import com.smh.szyproject.common.base.BaseActivity;

import java.lang.reflect.Proxy;

public class SubJectTest extends BaseActivity {
    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    public void init(Bundle savedInstanceState) {
        //接口能直接new一个实现这个接口的类
        Subject realSubject = new RealSubject();
        //我们要代理哪个类，就把那个对象传进去，最后是通过该被代理对象来调用其方法的
        SubjectInvocationHandler handler = new SubjectInvocationHandler(realSubject);

        Subject subject = (Subject) Proxy.newProxyInstance(handler.getClass().getClassLoader(),realSubject.getClass().getInterfaces(),handler);

        subject.sayGoodBye();
    }
}
