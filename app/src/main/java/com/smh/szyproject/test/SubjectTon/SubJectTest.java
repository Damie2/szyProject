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
//        //首先初始化supermen
//        SuperMan superMan = new SuperMan();
//        //然后接口 实现代理  ，代理superMen
//        //调用的话用supermen的东西
//        Subject subject = new Proxy(superMan);
//        subject.shopping();
        test();
    }

    private void test() {


    }
}
