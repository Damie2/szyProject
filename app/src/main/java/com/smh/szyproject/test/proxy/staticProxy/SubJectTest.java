package com.smh.szyproject.test.proxy.staticProxy;

import android.os.Bundle;

import com.smh.szyproject.R;
import com.smh.szyproject.base.BaseActivity;

public class SubJectTest extends BaseActivity {
    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    public void init(Bundle savedInstanceState) {
        //首先初始化RealSubject
        RealSubject realSubject = new RealSubject();
        //然后再初始化增强的ProxySubject，这俩都实现了subject
        ProxySubject proxySubject = new ProxySubject(realSubject);
        //然后执行
        proxySubject.sayGoodBye();
        proxySubject.sayHello("11111");
    }

    private void test() {


    }
}
