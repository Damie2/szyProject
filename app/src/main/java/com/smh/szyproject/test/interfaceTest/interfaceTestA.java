package com.smh.szyproject.test.interfaceTest;

import android.os.Bundle;

import com.smh.szyproject.R;
import com.smh.szyproject.base.BaseActivity;

/**
 * author : smh
 * date   : 2020/3/9 14:56
 * desc   :
 */
public class interfaceTestA extends BaseActivity {
    @Override
    public int getLayoutId() {
        return R.layout.activity_test;
    }

    @Override
    public void init(Bundle savedInstanceState) {
        InterfaceA inter = new AA();
        inter.test();
    }
}
