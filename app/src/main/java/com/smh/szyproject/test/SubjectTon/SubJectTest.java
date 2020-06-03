package com.smh.szyproject.test.SubjectTon;

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
        //首先初始化supermen
        /**
         * 静态代理，就是A和B两个类都实现一个接口
         * 在B里传入A的构造方法
         * 接着创建代理类
         * 调用代理类的接口的方法
         * 代理类接口的方法又实现了A类的接口的方法
         * 这样，调用B类的方法会执行A类的方法，这就是静态代理
         */
        SuperMan superMan = new SuperMan();
        //然后接口 实现代理  ，代理superMen
        //调用的话用supermen的东西
        Subject subject = new Proxy(superMan);
        subject.shopping();
//        test();
    }

    private void test() {


    }
}
