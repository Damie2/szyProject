package com.smh.szyproject.test;

import android.os.Bundle;

import com.smh.szyproject.R;
import com.smh.szyproject.common.base.BaseActivity;

public class HashMapTest extends BaseActivity {
    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    public void init(Bundle savedInstanceState) {

    }

    public HashMapTest(){
        //无参的构造方法
        //构造一个空的构造函数，初始容量是16，负载银子是 0.75
    }

}
