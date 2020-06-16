package com.smh.szyproject.test.interfaceAdapter;

import android.os.Bundle;

import com.smh.szyproject.R;
import com.smh.szyproject.common.base.BaseActivity;

public class interfaceTest extends BaseActivity {
    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    public void init(Bundle savedInstanceState) {
        A a = new Ashili();
        a.a();
        a.b();
        a.c();
        a.d();
    }
}
