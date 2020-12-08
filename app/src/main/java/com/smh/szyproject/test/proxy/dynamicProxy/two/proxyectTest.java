package com.smh.szyproject.test.proxy.dynamicProxy.two;

import android.os.Bundle;

import com.smh.szyproject.R;
import com.smh.szyproject.common.base.BaseActivity;

import java.lang.reflect.Proxy;

/**
 * 动态代理模式
 */
public class proxyectTest extends BaseActivity {
    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    public void init(Bundle savedInstanceState) {
        //接口能直接new一个实现这个接口的类
        Subject realSubject = new Man();

        Proxyx p  = new Proxyx(realSubject);

        Subject subject =  (Subject) Proxy.newProxyInstance(realSubject.getClass().getClassLoader(),realSubject.getClass().getInterfaces(),p);

        subject.shopping();

    }
}
